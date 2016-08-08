/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import java.util.Arrays;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;

/**
 *
 * @author ajadriano
 */
public class ObjectUnionOfFunction implements OWLAxiomExpression {
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
    public Object execute(OWLDataFactory factory, Object... args) {          
        return factory.getOWLObjectUnionOf(Arrays.copyOf(args, args.length, OWLClassExpression[].class)); 
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }      
}
