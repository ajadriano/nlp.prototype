/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.AxiomResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class NegativeDataPropertyAssertionFunction implements OWLExpression {
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
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new AxiomResult(factory.getOWLNegativeDataPropertyAssertionAxiom((OWLDataPropertyExpression)args[0], (OWLIndividual)args[1], (OWLLiteral)args[2]));
    }

    @Override
    public Integer getArgumentCount() {
        return 3;
    }  
}
