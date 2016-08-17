/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.queries;

import aj.owl.model.IRIListResult;
import aj.owl.model.OWLQueryExpression;
import aj.owl.model.Result;
import java.util.ArrayList;
import java.util.List;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
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
        
        NodeSet<OWLNamedIndividual> set = reasoner.getInstances((OWLClassExpression)args[0], true);
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
