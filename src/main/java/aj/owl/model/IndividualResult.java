/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.model;

import org.semanticweb.owlapi.model.OWLIndividual;

/**
 *
 * @author ajadriano
 */
public class IndividualResult extends AnnotatedResult<OWLIndividual> {
    
    public IndividualResult(OWLIndividual result) {
        super(result);
    }
    
}
