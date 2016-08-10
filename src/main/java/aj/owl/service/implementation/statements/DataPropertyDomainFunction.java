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
public class DataPropertyDomainFunction implements OWLAxiomExpression {
    private static DataPropertyDomainFunction instance = null;
    
    protected DataPropertyDomainFunction() {
    }
    
    public static DataPropertyDomainFunction getInstance() {
        if(instance == null) {
           instance = new DataPropertyDomainFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLDataPropertyExpression.class;
        }
        else if (argumentIndex == 1) {
            return OWLClassExpression.class;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLDataPropertyDomainAxiom((OWLDataPropertyExpression)args[0], (OWLClassExpression)args[1]);
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }    
}
