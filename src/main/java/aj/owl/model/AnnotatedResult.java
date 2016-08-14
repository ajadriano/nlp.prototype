/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.model;

import org.semanticweb.owlapi.model.OWLAxiom;

/**
 *
 * @author ajadriano
 */
public class AnnotatedResult<T extends Object> extends Result<T> {    
    private OWLAxiom annotation;
    
    public AnnotatedResult(T result) {
        super(result);
    }

    /**
     * @return the annotation
     */
    public OWLAxiom getAnnotation() {
        return annotation;
    }

    /**
     * @param annotation the annotation to set
     */
    public void setAnnotation(OWLAxiom annotation) {
        this.annotation = annotation;
    }
    
}
