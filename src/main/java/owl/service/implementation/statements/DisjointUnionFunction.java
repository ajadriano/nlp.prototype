/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.AxiomResult;
import owl.model.Result;
import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class DisjointUnionFunction implements OWLExpression {
    private static DisjointUnionFunction instance = null;
    
    protected DisjointUnionFunction() {
    }
    
    public static DisjointUnionFunction getInstance() {
        if(instance == null) {
           instance = new DisjointUnionFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 1) {
            return OWLClass.class;
        }            
        return OWLClassExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {        
        Set<OWLClassExpression> classExpressions = new HashSet<>();       
        for (int i = 1; i < args.length; i++) {
            classExpressions.add((OWLClassExpression)args[i]);
        }
        return new AxiomResult(factory.getOWLDisjointUnionAxiom((OWLClass)args[0], classExpressions));
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }   
}
