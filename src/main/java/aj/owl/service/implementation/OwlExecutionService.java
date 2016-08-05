/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation;

import aj.nlp.prototype.NewJFrame;
import aj.owl.model.Expression;
import aj.owl.model.FunctionExpression;
import aj.owl.model.VariableExpression;
import aj.owl.service.ExecutionService;
import aj.owl.service.FunctionParser;
import aj.owl.service.VariableExpressionConverter;
import aj.owl.service.implementation.statements.OWLExpressions;
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
import org.semanticweb.owlapi.model.OWLAxiom;
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
import aj.owl.model.OWLExpression;
import aj.owl.model.OWLAxiomExpression;
import aj.owl.model.OWLQueryExpression;
import org.semanticweb.owlapi.reasoner.InferenceType;

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
    
    public OwlExecutionService() {
        this(new DefaultFunctionParser());
    }
    
    public OwlExecutionService(FunctionParser functionParser) {
        this.functionParser = functionParser;
    }
    
    @Override
    public void initialize() {
        owlManager = OWLManager.createOWLOntologyManager();
        ontologyIRI = IRI.create("http://www.semanticweb.org/ontologies/myontology");
        
        try {
            File f = new File("example.owl");
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
                Object result = execute((FunctionExpression)expression);
                if (result != null) {
                    if (OWLAxiom.class.isAssignableFrom(result.getClass())) {
                        owlManager.addAxiom(ontology, (OWLAxiom)result);
                    }
                    else if (result instanceof String) {
                        sb.append(result);
                        sb.append("\n");
                    }
                }
            }
        }
        
        return sb.toString();
    } 
    
    private Object execute(FunctionExpression expression) {
        Object result = null;
        try {            
            OWLExpression function = OWLExpressions.valueOf(expression.getFunction()).getFunction();
            Integer argumentCount = function.getArgumentCount();
            List<Expression> expressionArgs = expression.getArguments();
            if (argumentCount != null && expressionArgs.size() != argumentCount) {
                return result;
            }           
            
            List<Object> arguments = GetExpressionArguments(expressionArgs, function);
            if (function instanceof OWLAxiomExpression) {
                result = ((OWLAxiomExpression)function).execute(factory, arguments.stream().toArray());
            }
            else if (function instanceof OWLQueryExpression) {
                result = ((OWLQueryExpression)function).query(reasoner, arguments.stream().toArray());
            }
            reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
        } catch (IllegalArgumentException e) {
            return null;
        }
        
        return result;
    }

    private List<Object> GetExpressionArguments(List<Expression> expressionArgs, OWLExpression function) {
        List<Object> arguments = new ArrayList();
        int argumentIndex = 0;
        for (Expression expressionArg : expressionArgs) {
            if (expressionArg instanceof FunctionExpression) {
                Object expressionResult = execute((FunctionExpression)expressionArg);
                if (expressionResult != null && function.getExpectedClass(argumentIndex).isAssignableFrom(expressionResult.getClass())) {
                    arguments.add(expressionResult);
                }
            }
            else if (expressionArg instanceof VariableExpression) {
                Object expressionResult = variableExpressionConverter.getOWLData(function.getExpectedClass(argumentIndex),
                        (VariableExpression)expressionArg);
                if (expressionResult != null) {
                    arguments.add(expressionResult);
                }
            }
            
            argumentIndex++;
        }
        return arguments;
    }

    @Override
    public void commit() {
        try {
            File file = new File("example.owl");
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
