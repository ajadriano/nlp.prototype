/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.model;

import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectPropertyResult extends AnnotatedResult<OWLObjectPropertyExpression> {
    
    private final boolean evaluate;
    
    public ObjectPropertyResult(OWLObjectPropertyExpression result, boolean evaluate) {
        super(result);
        this.evaluate = evaluate;
    }

    /**
     * @return the evaluate
     */
    public boolean isEvaluate() {
        return evaluate;
    }
    
}
