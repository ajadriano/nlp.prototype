/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.model;

import org.semanticweb.owlapi.model.OWLDataFactory;

/**
 *
 * @author ajadriano
 */
public interface OWLAxiomExpression extends OWLExpression {
    Object execute(OWLDataFactory factory, Object... args);
}
