/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.queries;

import owl.model.OWLQueryExpression;
import owl.model.BooleanResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

/**
 *
 * @author ajadriano
 */
public class InstanceOfQuery implements OWLQueryExpression {
    
    private static InstanceOfQuery instance = null;
    
    protected InstanceOfQuery() {
    }
    
    public static InstanceOfQuery getInstance() {
        if(instance == null) {
           instance = new InstanceOfQuery();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLNamedIndividual.class;
        }
        else if (argumentIndex == 1) {
            return OWLClassExpression.class;
        }
        
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        try {
            NodeSet<OWLNamedIndividual> set = reasoner.getInstances((OWLClassExpression)args[1], false);
            if (set.containsEntity((OWLNamedIndividual)args[0])) {
                return new BooleanResult(Boolean.TRUE);
            }
            else {
                return new BooleanResult(Boolean.FALSE);
            }
        } catch (Exception ex) {
            return new BooleanResult(Boolean.FALSE);
        }
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }
}
