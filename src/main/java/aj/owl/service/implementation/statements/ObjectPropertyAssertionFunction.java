/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.AxiomResult;
import aj.owl.model.Result;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import aj.owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectPropertyAssertionFunction implements OWLExpression {
    private static ObjectPropertyAssertionFunction instance = null;
    
    protected ObjectPropertyAssertionFunction() {
    }
    
    public static ObjectPropertyAssertionFunction getInstance() {
        if(instance == null) {
           instance = new ObjectPropertyAssertionFunction();
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
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new AxiomResult(factory.getOWLObjectPropertyAssertionAxiom((OWLObjectPropertyExpression)args[0], (OWLIndividual)args[1], (OWLIndividual)args[2]));
    }

    @Override
    public Integer getArgumentCount() {
        return 3;
    }  
}
