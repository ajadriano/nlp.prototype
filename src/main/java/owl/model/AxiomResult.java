/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.model;

import org.semanticweb.owlapi.model.OWLAxiom;

/**
 *
 * @author ajadriano
 */
public class AxiomResult extends Result<OWLAxiom> {
    public AxiomResult(OWLAxiom result) {
        super(result);
    }
}
