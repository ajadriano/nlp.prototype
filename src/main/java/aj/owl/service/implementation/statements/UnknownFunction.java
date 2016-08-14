/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

/**
 *
 * @author ajadriano
 */
public class UnknownFunction implements OWLAxiomExpression {
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
    public Object execute(OWLDataFactory factory, Object... args) {  
        StringBuilder sb = new StringBuilder("Cannot interpret '");
        for (Object arg : args) {
            sb.append(arg.toString());
            sb.append(" ");
        }
        
        return sb.toString().trim() + "'";
    }

    @Override
    public Integer getArgumentCount() {
        return null;
    }   
}
