/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation;

import aj.nlp.prototype.NewJFrame;
import aj.owl.model.AnnotatedResult;
import aj.owl.model.AxiomResult;
import aj.owl.model.ErrorResult;
import aj.owl.model.Expression;
import aj.owl.model.FunctionExpression;
import aj.owl.model.IRIListResult;
import aj.owl.model.IndividualResult;
import aj.owl.model.OWLExpression;
import aj.owl.model.OWLQueryExpression;
import aj.owl.model.ObjectPropertyResult;
import aj.owl.model.VariableExpression;
import aj.owl.service.ExecutionService;
import aj.owl.service.FunctionParser;
import aj.owl.service.VariableExpressionConverter;
import aj.owl.service.implementation.statements.ClassExpressions;
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
import aj.owl.model.QueryResult;
import aj.owl.model.Result;
import java.util.Optional;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

/**
 *
 * @author ajadriano
 */
public class OwlExecutionService implements ExecutionService {

    private final FunctionParser functionParser;
    private VariableExpressionConverter variableExpressionConverter;
    
    private OWLOntologyManager owlManager;
    private OWLOntology ontology;
    private OWLDataFactory factory;
    private OWLReasoner reasoner;
    private IRI ontologyIRI;
    private String ontologyName;
    
    public OwlExecutionService() {
        this(new DefaultFunctionParser());
    }
    
    public OwlExecutionService(FunctionParser functionParser) {
        this.functionParser = functionParser;
    }
    
    @Override
    public void initialize(String ontologyName) {
        this.ontologyName = ontologyName;
        owlManager = OWLManager.createOWLOntologyManager();
        ontologyIRI = IRI.create("http://www.semanticweb.org/ontologies/" + this.ontologyName);
        
        try {
            File f = new File("../domains/" + this.ontologyName + "/ontology.owl");
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
    public String execute(String statement) {
        List<Expression> expressions = functionParser.parse(statement);
        StringBuilder sb = new StringBuilder();
        
        for (Expression expression : expressions) {
            if (expression instanceof FunctionExpression) {
                Result<?> result = execute((FunctionExpression)expression);
                if (result != null) {
                    if (result instanceof AxiomResult) {
                        owlManager.addAxiom(ontology, ((AxiomResult)result).getResult());
                        reasoner.flush();
                    }
                    else if (result instanceof ErrorResult) {
                        sb.append(((ErrorResult)result).getResult());
                        sb.append("\n");
                    }
                    else if (result instanceof QueryResult) {
                        sb.append(((QueryResult)result).getResult());
                        sb.append("\n");
                    }
                    else if (result instanceof IRIListResult) {
                        for (IRI iri : ((IRIListResult)result).getResult()) {
                            Optional<OWLAnnotationAssertionAxiom> axiom = ontology.annotationAssertionAxioms(iri).findFirst();
                            if (axiom != null && axiom.isPresent()) {
                                if (axiom.get().getProperty() == factory.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI())) {
                                    sb.append(axiom.get().getValue().asLiteral().get().getLiteral());
                                    sb.append("\n");
                                }
                            }
                            else {
                                sb.append(iri.getShortForm());
                                sb.append("\n");
                            }
                        }
                    }
                }
            }
        }
        
        if (sb.toString().isEmpty())
        {
            return "OK";
        }
        
        return sb.toString();
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
        if (!(isQuery)) {
            if (result instanceof ObjectPropertyResult) {
                owlManager.addAxiom(ontology, factory.getOWLSubObjectPropertyOfAxiom(
                        ((ObjectPropertyResult)result).getResult(), factory.getOWLTopObjectProperty()));
            }
            else if (result instanceof IndividualResult) {
                owlManager.addAxiom(ontology, factory.getOWLClassAssertionAxiom(factory.getOWLThing(),
                    ((IndividualResult)result).getResult()));
            }

            if (result instanceof AnnotatedResult<?>) {
                AnnotatedResult<?> annotatedResult = (AnnotatedResult<?>)result;
                if (annotatedResult.getAnnotation() != null) {
                    owlManager.addAxiom(ontology, annotatedResult.getAnnotation());
                }
            } 
        }
        
        return result;
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

    @Override
    public void commit() {
        try {
            File file = new File("../domains/" + this.ontologyName + "/ontology.owl");
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
