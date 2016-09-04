/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation;

import nlp.prototype.NewJFrame;
import owl.model.AnnotatedResult;
import owl.model.AxiomResult;
import owl.model.ErrorResult;
import xsl.model.Expression;
import xsl.model.FunctionExpression;
import owl.model.IRIListResult;
import owl.model.IndividualResult;
import owl.model.OWLExpression;
import owl.model.OWLQueryExpression;
import owl.model.ObjectPropertyResult;
import xsl.model.VariableExpression;
import owl.service.ExecutionService;
import owl.service.VariableExpressionConverter;
import owl.service.implementation.statements.ClassExpressions;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import uk.ac.manchester.cs.jfact.JFactFactory;
import owl.model.QueryResult;
import owl.model.Result;
import java.util.Optional;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
import owl.model.DataPropertyResult;

/**
 *
 * @author ajadriano
 */
public class OwlExecutionService implements ExecutionService {

    private final String directory;
    private final boolean isReadOnly;
    private VariableExpressionConverter variableExpressionConverter;
    
    private OWLOntologyManager owlManager;
    private OWLOntology ontology;
    private OWLDataFactory factory;
    private OWLReasoner reasoner;
    private IRI ontologyIRI;
    private String ontologyName;
    
    public OwlExecutionService(String directory) {
        this.directory = directory;
        this.isReadOnly = false;
    }
    
    public OwlExecutionService(String directory, boolean isReadOnly) {
        this.directory = directory;
        this.isReadOnly = isReadOnly;
    }
    
    @Override
    public void initialize(String ontologyName) {
        this.ontologyName = ontologyName;
        owlManager = OWLManager.createOWLOntologyManager();
        ontologyIRI = IRI.create("http://www.semanticweb.org/ontologies/" + this.ontologyName);
        
        try {
            File f = new File(directory + this.ontologyName + "/ontology.owl");
            if(f.exists()) { 
                ontology = owlManager.loadOntologyFromOntologyDocument(f);
            }
            else {
                ontology = owlManager.createOntology(ontologyIRI);
            }
                
            
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        OWLReasonerFactory reasonerFactory = new JFactFactory();
        
        OWLReasonerConfiguration config = new SimpleConfiguration(50000);        
        reasoner = reasonerFactory.createReasoner(ontology, config);
        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
        factory = owlManager.getOWLDataFactory();
        variableExpressionConverter = new DefaultVariableExpressionConverter(factory, ontologyIRI);
    }
    
    @Override
    public List<String> execute(List<Expression> expressions) {
        List<String> results = new ArrayList();
        
        for (Expression expression : expressions) {
            if (expression instanceof FunctionExpression) {
                Result<?> result = execute((FunctionExpression)expression);
                if (result != null) {
                    if (result instanceof AxiomResult) {
                        boolean boolRes = reasoner.isEntailed(((AxiomResult)result).getResult());
                        results.add(Boolean.toString(boolRes));
                        //evaluateAxiom(((AxiomResult)result).getResult());
                        //reasoner.flush();
                    }
                    else if (result instanceof ErrorResult) {
                        results.add(((ErrorResult)result).getResult());
                    }
                    else if (result instanceof QueryResult) {
                        results.add(((QueryResult)result).getFormattedResult());
                    }
                    else if (result instanceof IRIListResult) {
                        for (IRI iri : ((IRIListResult)result).getResult()) {
                            Optional<OWLAnnotationAssertionAxiom> axiom = ontology.annotationAssertionAxioms(iri).findFirst();
                            if (axiom != null && axiom.isPresent()) {
                                if (axiom.get().getProperty() == factory.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI())) {
                                    results.add(axiom.get().getValue().asLiteral().get().getLiteral());
                                }
                            }
                            else {
                                results.add(iri.getShortForm());
                            }
                        }
                    }
                }
            }
        }
        
        if (results.isEmpty())
        {
            results.add("OK");
        }
        
        return results;
    } 
    
    private Result<?> execute(FunctionExpression expression) {
        try { 
            OWLExpression function = ClassExpressions.valueOf(expression.getFunction()).getFunction();
            return execute(function, expression.getArguments(), function instanceof OWLQueryExpression);
        } catch (Exception e) {
            return new ErrorResult(e.getMessage());
        }
    }
    
    private Result<?> execute(OWLExpression function, List<Expression> expressionArgs, boolean isQuery) {
        Result<?> result = null;
        
        Integer argumentCount = function.getArgumentCount();
        if (argumentCount != null && expressionArgs.size() < argumentCount) {
            return new ErrorResult("Invalid argument");
        }           

        List<Object> arguments = new ArrayList();
        result = GetExpressionArguments(expressionArgs, function, arguments, isQuery);
        if (result != null) {
            return result;
        }

        result = function.execute(factory, reasoner, arguments.stream().toArray());         
        if ((!(isQuery)) && (!(isReadOnly))) {
            addExtraAxiom(result);
        }
        
        return result;
    } 

    private void addExtraAxiom(Result<?> result) {
        if (result instanceof ObjectPropertyResult) {
            ObjectPropertyResult objectPropertyResult = (ObjectPropertyResult)result;
            
            if (!owlManager.contains(objectPropertyResult.getResult().getIRI())) {
                evaluateAxiom(factory.getOWLSubObjectPropertyOfAxiom(
                        objectPropertyResult.getResult(), factory.getOWLTopObjectProperty()));
                
                if (objectPropertyResult.getInverseProperty() != null) {
                    evaluateAxiom(factory.getOWLInverseObjectPropertiesAxiom(objectPropertyResult.getResult(),
                            objectPropertyResult.getInverseProperty()));
                }
            }
        }
        if (result instanceof DataPropertyResult) {
            DataPropertyResult dataPropertyResult = (DataPropertyResult)result;
            
            if (!owlManager.contains(dataPropertyResult.getResult().getIRI())) {
                evaluateAxiom(factory.getOWLFunctionalDataPropertyAxiom(dataPropertyResult.getResult()));
                evaluateAxiom(factory.getOWLSubDataPropertyOfAxiom(
                        dataPropertyResult.getResult(), factory.getOWLTopDataProperty()));
                evaluateAxiom(factory.getOWLDataPropertyRangeAxiom(dataPropertyResult.getResult(),
                        dataPropertyResult.getDatatype())); 
            }
        }
        else if (result instanceof IndividualResult) {
            evaluateAxiom(factory.getOWLClassAssertionAxiom(factory.getOWLThing(),
                    ((IndividualResult)result).getResult()));
        }
        
        if (result instanceof AnnotatedResult<?>) {
            AnnotatedResult<?> annotatedResult = (AnnotatedResult<?>)result;
            if (annotatedResult.getAnnotation() != null) {
                evaluateAxiom(annotatedResult.getAnnotation());
            }
        }
    }

    private ErrorResult GetExpressionArguments(List<Expression> expressionArgs, OWLExpression callingFunction, List<Object> arguments, boolean isQuery) {
        int argumentIndex = 0;
        for (Expression expressionArg : expressionArgs) {
            if (expressionArg instanceof FunctionExpression) {   
                FunctionExpression functionExpression = (FunctionExpression)expressionArg;
                OWLExpression function = ClassExpressions.valueOf(functionExpression.getFunction()).getFunction();
                Result<?> expressionResult = execute(function, functionExpression.getArguments(), isQuery);
                if (expressionResult != null && callingFunction.getExpectedClass(argumentIndex).isAssignableFrom(expressionResult.getResult().getClass())) {
                    if (expressionResult instanceof ErrorResult) {
                        return (ErrorResult)expressionResult;
                    }
                    else if (callingFunction.getExpectedClass(argumentIndex).isAssignableFrom(expressionResult.getResult().getClass())) {
                        arguments.add(expressionResult.getResult());
                    }  
                }
            }
            else if (expressionArg instanceof VariableExpression) {
                Object expressionResult = variableExpressionConverter.getOWLData(callingFunction.getExpectedClass(argumentIndex),
                        (VariableExpression)expressionArg);
                if (expressionResult != null) {
                    arguments.add(expressionResult);
                }
            }
            
            argumentIndex++;
        }
        
        return null;
    }
    
    private void evaluateAxiom(OWLAxiom axiom) {
        owlManager.addAxiom(ontology, axiom);
    }

    @Override
    public void commit() {
        try {
            File file = new File(directory + this.ontologyName + "/ontology.owl");
            file.createNewFile();
            try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(file))) {
                owlManager.saveOntology(ontology, stream);
            }
        } catch (OWLOntologyStorageException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
