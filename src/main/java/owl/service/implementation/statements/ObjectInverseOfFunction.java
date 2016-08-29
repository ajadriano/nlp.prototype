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
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectInverseOfFunction implements OWLExpression {
    private static ObjectInverseOfFunction instance = null;
    
    protected ObjectInverseOfFunction() {
    }
    
    public static ObjectInverseOfFunction getInstance() {
        if(instance == null) {
           instance = new ObjectInverseOfFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLObjectProperty.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new ObjectPropertyResult(factory.getOWLObjectInverseOf((OWLObjectProperty)args[0]), false);
    }

    @Override
    public Integer getArgumentCount() {
        return 1;
    }  
}
