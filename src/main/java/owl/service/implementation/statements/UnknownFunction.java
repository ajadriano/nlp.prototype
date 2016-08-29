/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.ErrorResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class UnknownFunction implements OWLExpression {
private static UnknownFunction instance = null;
    
    protected UnknownFunction() {
    }
    
    public static UnknownFunction getInstance() {
        if(instance == null) {
           instance = new UnknownFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        return String.class;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {  
        StringBuilder sb = new StringBuilder("Cannot interpret '");
        for (Object arg : args) {
            sb.append(arg.toString());
            sb.append(" ");
        }
        
        return new ErrorResult(sb.toString().trim() + "'");
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }   
}
