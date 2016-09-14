/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.ObjectPropertyResult;
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
public class ObjectPropertyWithInverseFunction implements OWLExpression {
    private static ObjectPropertyWithInverseFunction instance = null;
    
    protected ObjectPropertyWithInverseFunction() {
    }
    
    public static ObjectPropertyWithInverseFunction getInstance() {
        if(instance == null) {
           instance = new ObjectPropertyWithInverseFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex <= 1) {
            return IRI.class;
        }
        
        return String.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        ObjectPropertyResult result = new ObjectPropertyResult(factory.getOWLObjectProperty((IRI)args[0])); 
        result.setInverseProperty(factory.getOWLObjectProperty((IRI)args[1]));
        if (args.length > 2) {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 2; i < args.length; i++)  {
                sb.append(args[i].toString());
                if (i < args.length - 1) {
                    sb.append(" ");
                }
            }
            
            result.setAnnotationSubject((IRI)args[0]);
            result.getAnnotations().add(factory.getOWLAnnotation(factory.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI()), 
                factory.getOWLLiteral(sb.toString())));
        }
        
        return result;
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }  
}
