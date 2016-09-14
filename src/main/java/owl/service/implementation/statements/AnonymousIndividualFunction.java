/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.IndividualResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class AnonymousIndividualFunction implements OWLExpression {
    private static AnonymousIndividualFunction instance = null;
    
    protected AnonymousIndividualFunction() {
    }
    
    public static AnonymousIndividualFunction getInstance() {
        if(instance == null) {
           instance = new AnonymousIndividualFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new IndividualResult(factory.getOWLAnonymousIndividual());
    }

    @Override
    public Integer getArgumentCount() {
        return 0;
    }  
}
