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
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;

/**
 *
 * @author ajadriano
 */
public class ObjectOneOfFunction implements OWLAxiomExpression {
    private static ObjectOneOfFunction instance = null;
    
    protected ObjectOneOfFunction() {
    }
    
    public static ObjectOneOfFunction getInstance() {
        if(instance == null) {
           instance = new ObjectOneOfFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {        
        return OWLIndividual.class;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {        
        return factory.getOWLObjectOneOf(Arrays.copyOf(args, args.length, OWLIndividual[].class));
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }    
}
