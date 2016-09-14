/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.AxiomResult;
import owl.model.ClassExpressionResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class ClassAssertionFunction implements OWLExpression {
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
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new AxiomResult(factory.getOWLClassAssertionAxiom((OWLClassExpression)args[0], (OWLIndividual)args[1]));
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }  
}
