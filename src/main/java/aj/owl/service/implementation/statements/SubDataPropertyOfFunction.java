/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

/**
 *
 * @author ajadriano
 */
public class SubDataPropertyOfFunction implements OWLAxiomExpression {
    private static SubDataPropertyOfFunction instance = null;
    
    protected SubDataPropertyOfFunction() {
    }
    
    public static SubDataPropertyOfFunction getInstance() {
        if(instance == null) {
           instance = new SubDataPropertyOfFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLDataPropertyExpression.class;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {  
        return factory.getOWLSubDataPropertyOfAxiom((OWLDataPropertyExpression)args[0], (OWLDataPropertyExpression)args[1]);
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }
}
