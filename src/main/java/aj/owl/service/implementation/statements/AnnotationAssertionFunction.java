/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLAxiomExpression;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

/**
 *
 * @author ajadriano
 */
public class AnnotationAssertionFunction implements OWLAxiomExpression {
    private static AnnotationAssertionFunction instance = null;
    
    protected AnnotationAssertionFunction() {
    }
    
    public static AnnotationAssertionFunction getInstance() {
        if(instance == null) {
           instance = new AnnotationAssertionFunction();
        }
        return instance;
    }
    
    @Override
    public Class getExpectedClass(int argumentIndex) {
        switch (argumentIndex) {
            case 0:
                return OWLAnnotationProperty.class;
            case 1:
                return OWLAnnotationSubject.class;
            case 2:
                return OWLAnnotationValue.class;
            default:
                break;
        }
        
        return null;
    }
    
    @Override
    public Object execute(OWLDataFactory factory, Object... args) {  
        
        return factory.getOWLAnnotationAssertionAxiom(
                factory.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI()), 
                (OWLAnnotationSubject)args[1], (OWLAnnotationValue)args[2]);
    }

    @Override
    public Integer getArgumentCount() {
        return 3;
    }   
}
