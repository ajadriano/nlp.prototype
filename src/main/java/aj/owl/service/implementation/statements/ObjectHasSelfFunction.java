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
public class ObjectHasSelfFunction implements OWLAxiomExpression {
    private static ObjectHasSelfFunction instance = null;
    
    protected ObjectHasSelfFunction() {
    }
    
    public static ObjectHasSelfFunction getInstance() {
        if(instance == null) {
           instance = new ObjectHasSelfFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLObjectPropertyExpression.class;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLObjectHasSelf((OWLObjectPropertyExpression)args[0]);
    }

    @Override
    public Integer getArgumentCount() {
        return 1;
    }   
}
