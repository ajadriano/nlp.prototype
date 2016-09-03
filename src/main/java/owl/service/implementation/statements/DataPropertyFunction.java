/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.Result;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
import owl.model.DataPropertyResult;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class DataPropertyFunction implements OWLExpression {
    private static DataPropertyFunction instance = null;
    
    protected DataPropertyFunction() {
    }
    
    public static DataPropertyFunction getInstance() {
        if(instance == null) {
           instance = new DataPropertyFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return IRI.class;
        }
        else if (argumentIndex == 1) {
            return OWL2Datatype.class;
        }
        
        return String.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        DataPropertyResult result = new DataPropertyResult(factory.getOWLDataProperty((IRI)args[0]), (OWL2Datatype)args[1]); 
        
        if (args.length > 2) {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 2; i < args.length; i++)  {
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
        return 2;
    }  
}
