/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.AxiomResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class FunctionalObjectPropertyFunction implements OWLExpression {
    private static FunctionalObjectPropertyFunction instance = null;
    
    protected FunctionalObjectPropertyFunction() {
    }
    
    public static FunctionalObjectPropertyFunction getInstance() {
        if(instance == null) {
           instance = new FunctionalObjectPropertyFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLObjectPropertyExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {                
        return new AxiomResult(factory.getOWLFunctionalObjectPropertyAxiom((OWLObjectPropertyExpression)args[0]));
    }

    @Override
    public Integer getArgumentCount() {
        return 1;
    } 
}
