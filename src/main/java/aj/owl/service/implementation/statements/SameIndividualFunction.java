/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import java.util.Arrays;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;

/**
 *
 * @author ajadriano
 */
public class SameIndividualFunction implements OWLAxiomExpression {
    private static SameIndividualFunction instance = null;
    
    protected SameIndividualFunction() {
    }
    
    public static SameIndividualFunction getInstance() {
        if(instance == null) {
           instance = new SameIndividualFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLIndividual.class;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {  
        return factory.getOWLSameIndividualAxiom(Arrays.copyOf(args, args.length, OWLIndividual[].class));
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }  
}
