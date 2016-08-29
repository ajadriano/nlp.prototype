/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.IndividualResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class IndividualFunction implements OWLExpression {
    private static IndividualFunction instance = null;
    
    protected IndividualFunction() {
    }
    
    public static IndividualFunction getInstance() {
        if(instance == null) {
           instance = new IndividualFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return IRI.class;
        }
        
        return String.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        IndividualResult result = new IndividualResult(factory.getOWLNamedIndividual((IRI)args[0])); 
        
        if (args.length > 1) {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 1; i < args.length; i++)  {
                sb.append(args[i].toString());
                if (i < args.length - 1) {
                    sb.append(" ");
                }
            }
           
            result.setAnnotation(factory.getOWLAnnotationAssertionAxiom(
                factory.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI()), 
                (IRI)args[0], factory.getOWLLiteral(sb.toString())));
        }
        
        return result;
    }

    @Override
    public Integer getArgumentCount() {
        return 1;
    }  
}
