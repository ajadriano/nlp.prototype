/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.ClassExpressionResult;
import owl.model.Result;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectExactCardinalityFunction implements OWLExpression{
    private static ObjectExactCardinalityFunction instance = null;
    
    protected ObjectExactCardinalityFunction() {
    }
    
    public static ObjectExactCardinalityFunction getInstance() {
        if(instance == null) {
           instance = new ObjectExactCardinalityFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        switch (argumentIndex) {
            case 0:
                return Integer.class;
            case 1:
                return OWLObjectPropertyExpression.class;
            case 2:
                return OWLClassExpression.class;
            default:
                break;
        }
        
        return null;
    }
    
    @Override
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {   
        if (args.length == 2) {
            return new ClassExpressionResult(factory.getOWLObjectExactCardinality((Integer)args[0], (OWLObjectPropertyExpression)args[1]));
        }
        else if (args.length == 3) {
            return new ClassExpressionResult(factory.getOWLObjectExactCardinality((Integer)args[0], (OWLObjectPropertyExpression)args[1], (OWLClassExpression)args[2]));
        }        
           
        return null;
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }  
}
