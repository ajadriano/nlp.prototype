/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.queries;

import owl.model.OWLQueryExpression;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.Answers;
import owl.model.QueryResult;

/**
 *
 * @author ajadriano
 */
public class GetObjectPropertyValuesQuery implements OWLQueryExpression {
    private static GetObjectPropertyValuesQuery instance = null;
    
    protected GetObjectPropertyValuesQuery() {
    }
    
    public static GetObjectPropertyValuesQuery getInstance() {
        if(instance == null) {
           instance = new GetObjectPropertyValuesQuery();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLNamedIndividual.class;
        }
        else if (argumentIndex == 1) {
            return OWLObjectPropertyExpression.class;
        }
        
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        Answers answers = new Answers();

        NodeSet<OWLNamedIndividual> set = reasoner.getObjectPropertyValues((OWLNamedIndividual)args[0], (OWLObjectPropertyExpression)args[1]);
        set.entities().forEach(namedIndividual -> {
            answers.getIndividuals().add(namedIndividual);
        });
        
        return new QueryResult(answers);
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }
}
