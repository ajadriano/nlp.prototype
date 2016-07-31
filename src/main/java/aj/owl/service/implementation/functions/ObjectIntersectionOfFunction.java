/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.functions;

import aj.owl.model.Function;
import java.util.Arrays;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;

/**
 *
 * @author ajadriano
 */
public class ObjectIntersectionOfFunction implements Function {
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
    public Object execute(OWLDataFactory factory, Object... args) {          
        return factory.getOWLObjectIntersectionOf(Arrays.copyOf(args, args.length, OWLClassExpression[].class)); 
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }   
}
