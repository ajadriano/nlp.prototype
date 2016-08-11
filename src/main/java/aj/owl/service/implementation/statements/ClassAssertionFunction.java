/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;

/**
 *
 * @author ajadriano
 */
public class ClassAssertionFunction implements OWLAxiomExpression {
    private static ClassAssertionFunction instance = null;
    
    protected ClassAssertionFunction() {
    }
    
    public static ClassAssertionFunction getInstance() {
        if(instance == null) {
           instance = new ClassAssertionFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLClassExpression.class;
        }
        else if (argumentIndex == 1) {
            return OWLIndividual.class;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {  
        return factory.getOWLClassAssertionAxiom((OWLClassExpression)args[0], (OWLIndividual)args[1]);
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }  
}
