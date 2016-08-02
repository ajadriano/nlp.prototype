/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import java.util.Arrays;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import aj.owl.model.OWLAxiomExpression;

/**
 *
 * @author ajadriano
 */
public class EquivalentClassesFunction implements OWLAxiomExpression {
    
    private static EquivalentClassesFunction instance = null;
    
    protected EquivalentClassesFunction() {
    }
    
    public static EquivalentClassesFunction getInstance() {
        if(instance == null) {
           instance = new EquivalentClassesFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLClassExpression.class;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLEquivalentClassesAxiom(Arrays.copyOf(args, args.length, OWLClassExpression[].class)); 
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }
}
