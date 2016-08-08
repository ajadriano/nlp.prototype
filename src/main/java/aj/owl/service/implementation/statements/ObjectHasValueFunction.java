/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectHasValueFunction implements OWLAxiomExpression {
    private static ObjectHasValueFunction instance = null;
    
    protected ObjectHasValueFunction() {
    }
    
    public static ObjectHasValueFunction getInstance() {
        if(instance == null) {
           instance = new ObjectHasValueFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLObjectPropertyExpression.class;
        }
        else if (argumentIndex == 1) {
            return OWLIndividual.class;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLObjectHasValue((OWLObjectPropertyExpression)args[0], (OWLIndividual)args[1]);
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }   
}
