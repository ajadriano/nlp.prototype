/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service;

import aj.xsl.model.VariableExpression;

/**
 *
 * @author ajadriano
 */
public interface VariableExpressionConverter {
    Object getOWLData(Class type, VariableExpression expression);
}
