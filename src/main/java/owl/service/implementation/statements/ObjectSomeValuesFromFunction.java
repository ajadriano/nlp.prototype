/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.ClassExpressionResult;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import owl.model.Result;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectSomeValuesFromFunction implements OWLExpression {
        
    private static ObjectSomeValuesFromFunction instance = null;
    
    protected ObjectSomeValuesFromFunction() {
    }
    
    public static ObjectSomeValuesFromFunction getInstance() {
        if(instance == null) {
           instance = new ObjectSomeValuesFromFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLObjectPropertyExpression.class;
        }
        else if (argumentIndex == 1) {
            return OWLClassExpression.class;
        }
        
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new ClassExpressionResult(factory.getOWLObjectSomeValuesFrom((OWLObjectPropertyExpression)args[0], (OWLClassExpression)args[1])); 
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }
}
