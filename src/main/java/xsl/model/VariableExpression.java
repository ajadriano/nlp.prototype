/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xsl.model;

import xsl.model.Expression;

/**
 *
 * @author ajadriano
 */
public class VariableExpression extends Expression {
    private final String argument;
    
    public VariableExpression(String argument) {
        this.argument = argument;
    }
    
    @Override
    public String toString() {
        return argument;
    }

    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
