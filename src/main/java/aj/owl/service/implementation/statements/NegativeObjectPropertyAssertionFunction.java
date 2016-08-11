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
public class NegativeObjectPropertyAssertionFunction implements OWLAxiomExpression {
    private static NegativeObjectPropertyAssertionFunction instance = null;
    
    protected NegativeObjectPropertyAssertionFunction() {
    }
    
    public static NegativeObjectPropertyAssertionFunction getInstance() {
        if(instance == null) {
           instance = new NegativeObjectPropertyAssertionFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLObjectPropertyExpression.class;
        }
        else if (argumentIndex == 1 || argumentIndex == 2) {
            return OWLIndividual.class;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {  
        return factory.getOWLNegativeObjectPropertyAssertionAxiom((OWLObjectPropertyExpression)args[0], (OWLIndividual)args[1], (OWLIndividual)args[2]);
    }

    @Override
    public Integer getArgumentCount() {
        return 3;
    }  
}
