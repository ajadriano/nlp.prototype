/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.model;

import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 *
 * @author ajadriano
 */
public class ObjectPropertyResult extends AnnotatedResult<OWLObjectProperty> {
    
    private OWLObjectPropertyExpression inverseProperty;
    
    public ObjectPropertyResult(OWLObjectProperty result) {
        super(result);
    }

    /**
     * @return the inverseProperty
     */
    public OWLObjectPropertyExpression getInverseProperty() {
        return inverseProperty;
    }

    /**
     * @param inverseProperty the inverseProperty to set
     */
    public void setInverseProperty(OWLObjectPropertyExpression inverseProperty) {
        this.inverseProperty = inverseProperty;
    }
    
}
