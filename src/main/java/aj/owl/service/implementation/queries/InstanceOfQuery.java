/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.queries;

import aj.owl.model.OWLQueryExpression;
import aj.owl.model.QueryResult;
import aj.owl.model.Result;
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
            return OWLClassExpression.class;
        }
        else if (argumentIndex == 1) {
            return OWLNamedIndividual.class;
        }
        
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        NodeSet<OWLNamedIndividual> set = reasoner.getInstances((OWLClassExpression)args[0], false);
        if (set.containsEntity((OWLNamedIndividual)args[0])) {
            return new QueryResult(true);
        }
        else {
            return new QueryResult(false);
        }
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }
}
