/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.ClassResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class DataHasBoolValueFunction implements OWLExpression {
        
    private static DataHasBoolValueFunction instance = null;
    
    protected DataHasBoolValueFunction() {
    }
    
    public static DataHasBoolValueFunction getInstance() {
        if(instance == null) {
           instance = new DataHasBoolValueFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLDataPropertyExpression.class;
        }
        else if (argumentIndex == 1) {
            return String.class;
        }
        
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        return new ClassResult(factory.getOWLDataHasValue((OWLDataPropertyExpression)args[0], 
                factory.getOWLLiteral(Boolean.valueOf((String)args[1]))));
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }       
}
