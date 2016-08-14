/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.AxiomResult;
import aj.owl.model.Result;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import aj.owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectPropertyDomainFunction implements OWLExpression {
    private static ObjectPropertyDomainFunction instance = null;
    
    protected ObjectPropertyDomainFunction() {
    }
    
    public static ObjectPropertyDomainFunction getInstance() {
        if(instance == null) {
           instance = new ObjectPropertyDomainFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLObjectPropertyExpression.class;
        }
        else if (argumentIndex == 1) {
            return OWLClassExpression.class;
        }
        
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {                
        return new AxiomResult(factory.getOWLObjectPropertyDomainAxiom((OWLObjectPropertyExpression)args[0], (OWLClassExpression)args[1]));
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    } 
}
