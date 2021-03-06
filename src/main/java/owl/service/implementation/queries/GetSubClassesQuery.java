/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.queries;

import owl.model.OWLQueryExpression;
import owl.model.BooleanResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.Answers;
import owl.model.QueryResult;

/**
 *
 * @author ajadriano
 */
public class GetSubClassesQuery implements OWLQueryExpression {
    
    private static GetSubClassesQuery instance = null;
    
    protected GetSubClassesQuery() {
    }
    
    public static GetSubClassesQuery getInstance() {
        if(instance == null) {
           instance = new GetSubClassesQuery();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLClassExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {
        Answers answers = new Answers();
        
        NodeSet<OWLClass> set = reasoner.getSubClasses((OWLClassExpression)args[0], false);
        set.entities().forEach(subclass -> {
            if (subclass != factory.getOWLNothing() && subclass != factory.getOWLThing()) {
               answers.getClasses().add(subclass); 
            }
        });
        
        return new QueryResult(answers);
    }

    @Override
    public Integer getArgumentCount() {
        return 1;
    }
}
