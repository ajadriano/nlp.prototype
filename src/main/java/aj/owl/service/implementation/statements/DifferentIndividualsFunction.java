/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.AxiomResult;
import aj.owl.model.Result;
import java.util.Arrays;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import aj.owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class DifferentIndividualsFunction implements OWLExpression {
    private static DifferentIndividualsFunction instance = null;
    
    protected DifferentIndividualsFunction() {
    }
    
    public static DifferentIndividualsFunction getInstance() {
        if(instance == null) {
           instance = new DifferentIndividualsFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLIndividual.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new AxiomResult(factory.getOWLDifferentIndividualsAxiom(Arrays.copyOf(args, args.length, OWLIndividual[].class)));
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }  
}
