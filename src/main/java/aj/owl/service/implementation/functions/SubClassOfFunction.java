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
public class SubClassOfFunction implements Function {
    private static SubClassOfFunction instance = null;
    
    protected SubClassOfFunction() {
    }
    
    public static SubClassOfFunction getInstance() {
        if(instance == null) {
           instance = new SubClassOfFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLClassExpression.class;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLSubClassOfAxiom((OWLClassExpression)args[0], (OWLClassExpression)args[1]); 
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }
}
