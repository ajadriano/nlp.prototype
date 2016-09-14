/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.model;

import java.util.ArrayList;
import java.util.List;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;

/**
 *
 * @author ajadriano
 */
public class AnnotatedResult<T extends Object> extends Result<T> {    
    private final List<OWLAnnotation> annotations;
    private OWLAnnotationSubject annotationSubject;
    
    public AnnotatedResult(T result) {
        super(result);
        annotations = new ArrayList();
    }

    /**
     * @return the annotations
     */
    public List<OWLAnnotation> getAnnotations() {
        return annotations;
    }

    /**
     * @return the annotationSubject
     */
    public OWLAnnotationSubject getAnnotationSubject() {
        return annotationSubject;
    }

    /**
     * @param annotationSubject the annotationSubject to set
     */
    public void setAnnotationSubject(OWLAnnotationSubject annotationSubject) {
        this.annotationSubject = annotationSubject;
    }
    
}
