/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;

/**
 *
 * @author ajadriano
 */
public class DisjointClassesFunction implements OWLAxiomExpression {
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
    public Object execute(OWLDataFactory factory, Object... args) {                  
        return factory.getOWLDisjointClassesAxiom(Arrays.copyOf(args, args.length, OWLClassExpression[].class)); 
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }
}
