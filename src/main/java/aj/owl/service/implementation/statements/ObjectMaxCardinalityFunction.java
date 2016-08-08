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
public class ObjectMaxCardinalityFunction implements OWLAxiomExpression {
    private static ObjectMaxCardinalityFunction instance = null;
    
    protected ObjectMaxCardinalityFunction() {
    }
    
    public static ObjectMaxCardinalityFunction getInstance() {
        if(instance == null) {
           instance = new ObjectMaxCardinalityFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        switch (argumentIndex) {
            case 0:
                return Integer.class;
            case 1:
                return OWLObjectPropertyExpression.class;
            case 2:
                return OWLClassExpression.class;
            default:
                break;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {   
        if (args.length == 2) {
            return factory.getOWLObjectMaxCardinality((Integer)args[0], (OWLObjectPropertyExpression)args[1]);
        }
        else if (args.length == 3) {
            return factory.getOWLObjectMaxCardinality((Integer)args[0], (OWLObjectPropertyExpression)args[1], (OWLClassExpression)args[2]);
        }        
           
        return null;
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }       
}
