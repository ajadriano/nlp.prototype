/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;

/**
 *
 * @author ajadriano
 */
public class DisjointUnionFunction implements OWLAxiomExpression {
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
    public Object execute(OWLDataFactory factory, Object... args) {        
        Set<OWLClassExpression> classExpressions = new HashSet<>();       
        for (int i = 1; i < args.length; i++) {
            classExpressions.add((OWLClassExpression)args[i]);
        }
        return factory.getOWLDisjointUnionAxiom((OWLClass)args[0], classExpressions);
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }   
}