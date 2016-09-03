/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.model;

import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

/**
 *
 * @author ajadriano
 */
public class DataPropertyResult extends AnnotatedResult<OWLDataProperty> {
    
    private final OWL2Datatype datatype;
    
    public DataPropertyResult(OWLDataProperty result, OWL2Datatype datatype) {
        super(result);
        this.datatype = datatype;
    }

    /**
     * @return the datatype
     */
    public OWL2Datatype getDatatype() {
        return datatype;
    }
}
