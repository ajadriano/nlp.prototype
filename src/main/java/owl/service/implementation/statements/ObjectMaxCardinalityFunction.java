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
public class ObjectMaxCardinalityFunction implements OWLExpression {
    private static ObjectMaxCardinalityFunction instance = null;
    
    protected ObjectMaxCardinalityFunction() {
    }
    
    public static ObjectMaxCardinalityFunction getInstance() {
        if(instance == null) {
           instance = new ObjectMaxCardinalityFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        switch (argumentIndex) {
            case 0:
                return String.class;
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
            return new ClassExpressionResult(factory.getOWLObjectMaxCardinality(getInteger((String)args[0]), (OWLObjectPropertyExpression)args[1]));
        }
        else if (args.length == 3) {
            return new ClassExpressionResult(factory.getOWLObjectMaxCardinality(getInteger((String)args[0]), (OWLObjectPropertyExpression)args[1], (OWLClassExpression)args[2]));
        }        
           
        return null;
    }
    
    private Integer getInteger(String value) {        
        if (value.startsWith("<=") ||
            value.startsWith(">=") ||
            value.startsWith("==") ||
            value.startsWith("<>") ||
            value.startsWith("!=")) {
            Double doubleValue = Double.parseDouble(value.substring(2));
            return doubleValue.intValue();
        }
        if (value.startsWith("=") ||
            value.startsWith("<") ||
            value.startsWith(">")) {
            Double doubleValue = Double.parseDouble(value.substring(1));
            return doubleValue.intValue();
        }
        
        Double doubleValue = Double.parseDouble(value);
        return doubleValue.intValue();
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }       
}
