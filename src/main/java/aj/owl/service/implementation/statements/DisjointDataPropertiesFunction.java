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
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import aj.owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class DisjointDataPropertiesFunction implements OWLExpression {
    private static DisjointDataPropertiesFunction instance = null;
    
    protected DisjointDataPropertiesFunction() {
    }
    
    public static DisjointDataPropertiesFunction getInstance() {
        if(instance == null) {
           instance = new DisjointDataPropertiesFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLDataPropertyExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new AxiomResult(factory.getOWLDisjointDataPropertiesAxiom(Arrays.copyOf(args, args.length, OWLDataPropertyExpression[].class)));
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }  
}
