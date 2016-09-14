/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.model;

import org.semanticweb.owlapi.model.OWLNamedIndividual;

/**
 *
 * @author ajadriano
 */
public class NamedIndividualResult extends AnnotatedResult<OWLNamedIndividual> {
    
    public NamedIndividualResult(OWLNamedIndividual result) {
        super(result);
    }
    
}
