/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.AxiomResult;
import owl.model.ClassExpressionResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class DataAllValuesFromFunction implements OWLExpression {
        
    private static DataAllValuesFromFunction instance = null;
    
    protected DataAllValuesFromFunction() {
    }
    
    public static DataAllValuesFromFunction getInstance() {
        if(instance == null) {
           instance = new DataAllValuesFromFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLDataPropertyExpression.class;
        }
        else if (argumentIndex == 1) {
            return OWL2Datatype.class;
        }
        
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {                
        return new ClassExpressionResult(factory.getOWLDataAllValuesFrom((OWLDataPropertyExpression)args[0], (OWL2Datatype)args[1]));
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }      
}
