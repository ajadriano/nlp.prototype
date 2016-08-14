/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.ClassResult;
import aj.owl.model.Result;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import aj.owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectComplementOfFunction implements OWLExpression {
    private static ObjectComplementOfFunction instance = null;
    
    protected ObjectComplementOfFunction() {
    }
    
    public static ObjectComplementOfFunction getInstance() {
        if(instance == null) {
           instance = new ObjectComplementOfFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLClassExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {          
        return new ClassResult( factory.getOWLObjectComplementOf((OWLClassExpression)args[0]));
    }

    @Override
    public Integer getArgumentCount() {
        return 1;
    }     
}
