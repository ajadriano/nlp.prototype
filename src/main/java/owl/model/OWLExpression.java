/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.model;

import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

/**
 *
 * @author ajadriano
 */
public interface OWLExpression {
    Integer getArgumentCount();
    Class getExpectedClass(int argumentIndex);
    Result<? extends Object> execute(OWLDataFactory factory, OWLReasoner reasoner, Object... args);
}
