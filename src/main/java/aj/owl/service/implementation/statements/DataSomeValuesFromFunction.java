/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

/**
 *
 * @author ajadriano
 */
public class DataSomeValuesFromFunction implements OWLAxiomExpression {
        
    private static DataSomeValuesFromFunction instance = null;
    
    protected DataSomeValuesFromFunction() {
    }
    
    public static DataSomeValuesFromFunction getInstance() {
        if(instance == null) {
           instance = new DataSomeValuesFromFunction();
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
    public Object execute(OWLDataFactory factory, Object... args) {                
        return factory.getOWLDataSomeValuesFrom((OWLDataPropertyExpression)args[0], (OWL2Datatype)args[1]);
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }   
}
