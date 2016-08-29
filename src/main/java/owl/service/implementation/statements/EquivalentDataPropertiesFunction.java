/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.AxiomResult;
import owl.model.Result;
import java.util.Arrays;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class EquivalentDataPropertiesFunction implements OWLExpression {
    private static EquivalentDataPropertiesFunction instance = null;
    
    protected EquivalentDataPropertiesFunction() {
    }
    
    public static EquivalentDataPropertiesFunction getInstance() {
        if(instance == null) {
           instance = new EquivalentDataPropertiesFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLDataPropertyExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new AxiomResult(factory.getOWLEquivalentDataPropertiesAxiom(Arrays.copyOf(args, args.length, OWLDataPropertyExpression[].class)));
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }  
}
