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
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
import owl.model.ClassResult;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class ClassFunction implements OWLExpression {
    private static ClassFunction instance = null;
    
    protected ClassFunction() {
    }
    
    public static ClassFunction getInstance() {
        if(instance == null) {
           instance = new ClassFunction();
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
        ClassResult result = new ClassResult(factory.getOWLClass((IRI)args[0])); 
        
        if (args.length > 1) {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 1; i < args.length; i++)  {
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
        return 1;
    }  
}
