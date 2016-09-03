/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation;

import xsl.model.VariableExpression;
import owl.service.VariableExpressionConverter;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

/**
 *
 * @author ajadriano
 */
public class DefaultVariableExpressionConverter implements VariableExpressionConverter {

    private final OWLDataFactory factory;
    private final IRI ontologyIRI;
    
    public DefaultVariableExpressionConverter (OWLDataFactory factory, IRI ontologyIRI) {
        this.factory = factory;
        this.ontologyIRI = ontologyIRI;
    }
    
    @Override
    public Object getOWLData(Class type, VariableExpression expression) {
        if (type == String.class) {
            return expression.toString();
        }
        else if (type == IRI.class) {
            return IRI.create(ontologyIRI + "#" + expression.toString());
        }
        else if (type == OWL2Datatype.class) {
            return OWL2Datatype.valueOf(expression.toString());
        }
        else if (type == OWLLiteral.class) {
            return OWL2Datatype.valueOf(expression.toString());
        }
        
        return null;
    }
    
}
