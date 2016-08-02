/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.queries;

import aj.owl.model.OWLQueryExpression;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.reasoner.InferenceType;
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
    public String query(OWLReasoner reasoner, Object... args) {  
        NodeSet<OWLClass> set = reasoner.getSubClasses((OWLClassExpression)args[1], true);
        if (set.containsEntity((OWLClass)args[0])) {
            return "Yes";
        }
        else {
            return "No";
        }
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }
}
