/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectPropertyRangeFunction implements OWLAxiomExpression {
    private static ObjectPropertyRangeFunction instance = null;
    
    protected ObjectPropertyRangeFunction() {
    }
    
    public static ObjectPropertyRangeFunction getInstance() {
        if(instance == null) {
           instance = new ObjectPropertyRangeFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLObjectPropertyExpression.class;
        }
        else if (argumentIndex == 1) {
            return OWLClassExpression.class;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLObjectPropertyRangeAxiom((OWLObjectPropertyExpression)args[0], (OWLClassExpression)args[1]);
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    } 
}