/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.queries;

import owl.model.IRIListResult;
import owl.model.OWLQueryExpression;
import owl.model.Result;
import java.util.ArrayList;
import java.util.List;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

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
        List<IRI> answers = new ArrayList();
        
        NodeSet<OWLNamedIndividual> set = reasoner.getInstances((OWLClassExpression)args[0], false);
        set.entities().forEach(namedIndividual -> {
            answers.add(namedIndividual.getIRI());
        });
        
        if (answers.isEmpty()) {
            answers.add(factory.getOWLNothing().getIRI());
        }
        
        return new IRIListResult(answers);
    }

    @Override
    public Integer getArgumentCount() {
        return 1;
    }
}
