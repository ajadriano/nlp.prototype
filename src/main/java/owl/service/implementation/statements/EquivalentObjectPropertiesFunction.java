/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.AxiomResult;
import owl.model.Result;
import java.util.Arrays;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class EquivalentObjectPropertiesFunction implements OWLExpression {
    private static EquivalentObjectPropertiesFunction instance = null;
    
    protected EquivalentObjectPropertiesFunction() {
    }
    
    public static EquivalentObjectPropertiesFunction getInstance() {
        if(instance == null) {
           instance = new EquivalentObjectPropertiesFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return OWLObjectPropertyExpression.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {                
        return new AxiomResult(factory.getOWLEquivalentObjectPropertiesAxiom(Arrays.copyOf(args, args.length, OWLObjectPropertyExpression[].class)));
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }       
}
