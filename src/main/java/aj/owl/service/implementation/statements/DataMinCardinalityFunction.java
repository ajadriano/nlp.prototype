/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.ClassResult;
import aj.owl.model.Result;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import aj.owl.model.OWLExpression;

/**
 *
 * @author ajadriano
 */
public class DataMinCardinalityFunction implements OWLExpression {
    private static DataMinCardinalityFunction instance = null;
    
    protected DataMinCardinalityFunction() {
    }
    
    public static DataMinCardinalityFunction getInstance() {
        if(instance == null) {
           instance = new DataMinCardinalityFunction();
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
    public Result<?> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args) {   
        if (args.length == 2) {
            return new ClassResult( factory.getOWLDataMinCardinality((Integer)args[0], (OWLDataPropertyExpression)args[1]));
        }
        else if (args.length == 3) {
            return new ClassResult( factory.getOWLDataMinCardinality((Integer)args[0], (OWLDataPropertyExpression)args[1], (OWL2Datatype)args[2]));
        }        
           
        return null;
    }

    @Override
    public Integer getArgumentCount() {
        return 2;
    }     
}
