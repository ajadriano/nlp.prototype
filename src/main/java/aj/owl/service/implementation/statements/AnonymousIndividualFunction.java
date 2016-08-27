/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.ClassResult;
import aj.owl.model.IndividualResult;
import aj.owl.model.Result;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
import aj.owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class AnonymousIndividualFunction implements OWLExpression {
    private static AnonymousIndividualFunction instance = null;
    
    protected AnonymousIndividualFunction() {
    }
    
    public static AnonymousIndividualFunction getInstance() {
        if(instance == null) {
           instance = new AnonymousIndividualFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new IndividualResult(factory.getOWLAnonymousIndividual());
    }

    @Override
    public Integer getArgumentCount() {
        return 0;
    }  
}
