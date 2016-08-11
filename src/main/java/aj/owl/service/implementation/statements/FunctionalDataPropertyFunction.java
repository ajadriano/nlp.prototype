/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;

/**
 *
 * @author ajadriano
 */
public class FunctionalDataPropertyFunction implements OWLAxiomExpression {
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
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLFunctionalDataPropertyAxiom((OWLDataPropertyExpression)args[0]);
    }

    @Override
    public Integer getArgumentCount() {
        return 1;
    }     
}