/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.queries;

import aj.owl.model.OWLQueryExpression;
import aj.owl.model.QueryResult;
import aj.owl.model.Result;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

/**
 *
 * @author ajadriano
 */
public class DirectSubClassOfQuery implements OWLQueryExpression {
    
    private static DirectSubClassOfQuery instance = null;
    
    protected DirectSubClassOfQuery() {
    }
    
    public static DirectSubClassOfQuery getInstance() {
        if(instance == null) {
           instance = new DirectSubClassOfQuery();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLClassExpression.class;
        }
        else if (argumentIndex == 1) {
            return OWLClass.class;
        }
        
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        NodeSet<OWLClass> set = reasoner.getSubClasses((OWLClassExpression)args[1], false);
        if (set.containsEntity((OWLClass)args[0])) {
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
