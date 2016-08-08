/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLLiteral;

/**
 *
 * @author ajadriano
 */
public class DataHasValueFunction implements OWLAxiomExpression {
        
    private static DataHasValueFunction instance = null;
    
    protected DataHasValueFunction() {
    }
    
    public static DataHasValueFunction getInstance() {
        if(instance == null) {
           instance = new DataHasValueFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        if (argumentIndex == 0) {
            return OWLDataPropertyExpression.class;
        }
        else if (argumentIndex == 1) {
            return OWLLiteral.class;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLDataHasValue((OWLDataPropertyExpression)args[0], (OWLLiteral)args[1]);
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }       
}
