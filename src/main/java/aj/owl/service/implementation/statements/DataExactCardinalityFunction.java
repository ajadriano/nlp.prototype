/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

/**
 *
 * @author ajadriano
 */
public class DataExactCardinalityFunction implements OWLAxiomExpression {
    private static DataExactCardinalityFunction instance = null;
    
    protected DataExactCardinalityFunction() {
    }
    
    public static DataExactCardinalityFunction getInstance() {
        if(instance == null) {
           instance = new DataExactCardinalityFunction();
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
                return OWL2Datatype.class;
            default:
                break;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {   
        if (args.length == 2) {
            return factory.getOWLDataExactCardinality((Integer)args[0], (OWLDataPropertyExpression)args[1]);
        }
        else if (args.length == 3) {
            return factory.getOWLDataExactCardinality((Integer)args[0], (OWLDataPropertyExpression)args[1], (OWL2Datatype)args[2]);
        }        
           
        return null;
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }    
}
