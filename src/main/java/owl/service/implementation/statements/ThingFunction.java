/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.ClassResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class ThingFunction implements OWLExpression {
    private static ThingFunction instance = null;
    
    protected ThingFunction() {
    }
    
    public static ThingFunction getInstance() {
        if(instance == null) {
           instance = new ThingFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new ClassResult(factory.getOWLThing());
    }

    @Override
    public Integer getArgumentCount() {
        return 0;
    }  
}
