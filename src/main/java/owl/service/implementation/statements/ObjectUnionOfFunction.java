/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.ClassExpressionResult;
import owl.model.Result;
import java.util.Arrays;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectUnionOfFunction implements OWLExpression {
     private static ObjectUnionOfFunction instance = null;
    
    protected ObjectUnionOfFunction() {
    }
    
    public static ObjectUnionOfFunction getInstance() {
        if(instance == null) {
           instance = new ObjectUnionOfFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLClassExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {          
        return new ClassExpressionResult(factory.getOWLObjectUnionOf(Arrays.copyOf(args, args.length, OWLClassExpression[].class))); 
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }      
}
