/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.ClassResult;
import java.util.Arrays;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import aj.owl.model.Result;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import aj.owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectIntersectionOfFunction implements OWLExpression {
    private static ObjectIntersectionOfFunction instance = null;
    
    protected ObjectIntersectionOfFunction() {
    }
    
    public static ObjectIntersectionOfFunction getInstance() {
        if(instance == null) {
           instance = new ObjectIntersectionOfFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLClassExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {          
        return new ClassResult(factory.getOWLObjectIntersectionOf(Arrays.copyOf(args, args.length, OWLClassExpression[].class))); 
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }   
}
