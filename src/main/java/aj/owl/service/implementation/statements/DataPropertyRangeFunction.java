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
import org.semanticweb.owlapi.vocab.OWL2Datatype;

/**
 *
 * @author ajadriano
 */
public class DataPropertyRangeFunction implements OWLAxiomExpression {
    private static DataPropertyRangeFunction instance = null;
    
    protected DataPropertyRangeFunction() {
    }
    
    public static DataPropertyRangeFunction getInstance() {
        if(instance == null) {
           instance = new DataPropertyRangeFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLDataPropertyExpression.class;
        }
        else if (argumentIndex == 1) {
            return OWL2Datatype.class;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLDataPropertyRangeAxiom((OWLDataPropertyExpression)args[0], (OWL2Datatype)args[1]);
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }    
}
