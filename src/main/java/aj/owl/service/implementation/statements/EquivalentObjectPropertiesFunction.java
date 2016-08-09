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
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 *
 * @author ajadriano
 */
public class EquivalentObjectPropertiesFunction implements OWLAxiomExpression {
    private static EquivalentObjectPropertiesFunction instance = null;
    
    protected EquivalentObjectPropertiesFunction() {
    }
    
    public static EquivalentObjectPropertiesFunction getInstance() {
        if(instance == null) {
           instance = new EquivalentObjectPropertiesFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLObjectPropertyExpression.class;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLEquivalentObjectPropertiesAxiom(Arrays.copyOf(args, args.length, OWLObjectPropertyExpression[].class));
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }       
}
