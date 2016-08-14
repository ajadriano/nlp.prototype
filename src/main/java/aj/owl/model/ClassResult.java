/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.model;

import org.semanticweb.owlapi.model.OWLClassExpression;

/**
 *
 * @author ajadriano
 */
public class ClassResult extends AnnotatedResult<OWLClassExpression> {
    
    public ClassResult(OWLClassExpression result) {
        super(result);
    }
    
}
