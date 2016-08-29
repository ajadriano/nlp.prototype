/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xsl.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public class FunctionExpression extends Expression {
    private final String function;
    private List<Expression> arguments;
    
    public FunctionExpression(String function) {
        this.function = function;
        arguments = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(function + "(");
        
        for (int i = 0; i < arguments.size(); i++) {
            sb.append(arguments.get(i).toString());
            if (i < arguments.size() - 1) {
                sb.append(" ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public String getFunction() {
        return function;
    }
    
    /**
     * @return the arguments
     */
    public List<Expression> getArguments() {
        return arguments;
    }

    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
