/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.queries;

import org.semanticweb.owlapi.model.OWLClass;
import owl.model.OWLQueryExpression;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.Answers;
import owl.model.QueryResult;

/**
 *
 * @author ajadriano
 */
public class GetInstancesQuery implements OWLQueryExpression {
    private static GetInstancesQuery instance = null;
    
    protected GetInstancesQuery() {
    }
    
    public static GetInstancesQuery getInstance() {
        if(instance == null) {
           instance = new GetInstancesQuery();
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
        
        NodeSet<OWLNamedIndividual> individualSet = reasoner.getInstances((OWLClassExpression)args[0], false);
        individualSet.entities().forEach(namedIndividual -> {
            answers.getIndividuals().add(namedIndividual);
        });
        
        NodeSet<OWLClass> subClassSet = reasoner.getSubClasses((OWLClassExpression)args[0], false);
        subClassSet.entities().forEach(subclass -> {
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
