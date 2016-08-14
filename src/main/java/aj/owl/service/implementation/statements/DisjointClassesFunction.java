/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.AxiomResult;
import aj.owl.model.Result;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import aj.owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class DisjointClassesFunction implements OWLExpression {
    private static DisjointClassesFunction instance = null;
    
    protected DisjointClassesFunction() {
    }
    
    public static DisjointClassesFunction getInstance() {
        if(instance == null) {
           instance = new DisjointClassesFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLClassExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {                  
        return new AxiomResult(factory.getOWLDisjointClassesAxiom(Arrays.copyOf(args, args.length, OWLClassExpression[].class))); 
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }
}
