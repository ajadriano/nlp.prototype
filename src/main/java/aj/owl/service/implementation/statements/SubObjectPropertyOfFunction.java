/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 *
 * @author ajadriano
 */
public class SubObjectPropertyOfFunction implements OWLAxiomExpression {
    private static SubObjectPropertyOfFunction instance = null;
    
    protected SubObjectPropertyOfFunction() {
    }
    
    public static SubObjectPropertyOfFunction getInstance() {
        if(instance == null) {
           instance = new SubObjectPropertyOfFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLObjectPropertyExpression.class;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLSubObjectPropertyOfAxiom((OWLObjectPropertyExpression)args[0], (OWLObjectPropertyExpression)args[1]);
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }    
}
