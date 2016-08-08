/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import aj.owl.model.OWLAxiomExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectSomeValuesFromFunction implements OWLAxiomExpression {
        
    private static ObjectSomeValuesFromFunction instance = null;
    
    protected ObjectSomeValuesFromFunction() {
    }
    
    public static ObjectSomeValuesFromFunction getInstance() {
        if(instance == null) {
           instance = new ObjectSomeValuesFromFunction();
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
        return factory.getOWLObjectSomeValuesFrom((OWLObjectPropertyExpression)args[0], (OWLClassExpression)args[1]); 
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }
}
