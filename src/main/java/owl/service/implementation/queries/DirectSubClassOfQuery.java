/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.queries;

import owl.model.OWLQueryExpression;
import owl.model.QueryResult;
import owl.model.Result;
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
            return OWLClass.class;
        }
        else if (argumentIndex == 1) {
            return OWLClassExpression.class;
        }
        
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        NodeSet<OWLClass> set = reasoner.getSubClasses((OWLClassExpression)args[1], false);
        if (set.containsEntity((OWLClass)args[0])) {
            return new QueryResult(Boolean.TRUE);
        }
        else {
            return new QueryResult(Boolean.FALSE);
        }
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }
}
