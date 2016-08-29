/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.AxiomResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class FunctionalDataPropertyFunction implements OWLExpression {
    private static FunctionalDataPropertyFunction instance = null;
    
    protected FunctionalDataPropertyFunction() {
    }
    
    public static FunctionalDataPropertyFunction getInstance() {
        if(instance == null) {
           instance = new FunctionalDataPropertyFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLDataPropertyExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {                
        return new AxiomResult(factory.getOWLFunctionalDataPropertyAxiom((OWLDataPropertyExpression)args[0]));
    }

    @Override
    public Integer getArgumentCount() {
        return 1;
    }     
}
