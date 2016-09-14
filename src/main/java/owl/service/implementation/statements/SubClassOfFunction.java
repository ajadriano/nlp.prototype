/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.AxiomResult;
import owl.model.ClassExpressionResult;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import owl.model.Result;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class SubClassOfFunction implements OWLExpression {
    private static SubClassOfFunction instance = null;
    
    protected SubClassOfFunction() {
    }
    
    public static SubClassOfFunction getInstance() {
        if(instance == null) {
           instance = new SubClassOfFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLClassExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {                
        return new AxiomResult(factory.getOWLSubClassOfAxiom((OWLClassExpression)args[0], (OWLClassExpression)args[1])); 
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }
}
