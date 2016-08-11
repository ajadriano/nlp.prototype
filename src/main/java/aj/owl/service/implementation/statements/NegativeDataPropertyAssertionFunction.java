/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;

/**
 *
 * @author ajadriano
 */
public class NegativeDataPropertyAssertionFunction implements OWLAxiomExpression {
    private static NegativeDataPropertyAssertionFunction instance = null;
    
    protected NegativeDataPropertyAssertionFunction() {
    }
    
    public static NegativeDataPropertyAssertionFunction getInstance() {
        if(instance == null) {
           instance = new NegativeDataPropertyAssertionFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        switch (argumentIndex) {
            case 0:
                return OWLDataPropertyExpression.class;
            case 1:
                return OWLIndividual.class;
            case 2:
                return OWLLiteral.class;
            default:
                break;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {  
        return factory.getOWLNegativeDataPropertyAssertionAxiom((OWLDataPropertyExpression)args[0], (OWLIndividual)args[1], (OWLLiteral)args[2]);
    }

    @Override
    public Integer getArgumentCount() {
        return 3;
    }  
}
