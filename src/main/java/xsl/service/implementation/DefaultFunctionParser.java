/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xsl.service.implementation;

import xsl.model.Expression;
import xsl.model.FunctionExpression;
import xsl.model.VariableExpression;
import xsl.service.FunctionParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author ajadriano
 */
public class DefaultFunctionParser implements FunctionParser {

    @Override
    public List<Expression> parse(String statement) {
        String[] tokens = Arrays.stream(statement.trim().split("(?<=[()])|(?=[()])|\\s+")).filter((str) -> str.trim().length() > 0).
                toArray(size -> new String[size]);
        
        Stack<List<Expression>> argumentStack = new Stack<>();
        List<Expression> arguments = new ArrayList<>();
        argumentStack.push(arguments);
        retrieveArguments(tokens, argumentStack); 

        return arguments;
    }
    
    private void retrieveArguments(String[] tokens, Stack<List<Expression>> argumentStack) {
        if (tokens.length > 0 && ")".equals(tokens[0])) {
            if (!argumentStack.empty()) {
               argumentStack.pop(); 
            }
            
            if (tokens.length > 1) {
                retrieveArguments(Arrays.copyOfRange(tokens, 1, tokens.length), argumentStack);
            }
        }
        else if (tokens.length >= 2) {
            if ("(".equals(tokens[1])) {
                FunctionExpression argument = new FunctionExpression(tokens[0].trim());
                argumentStack.peek().add(argument);
                if (tokens.length > 2 ) {
                    argumentStack.push(argument.getArguments());
                    retrieveArguments(Arrays.copyOfRange(tokens, 2,  tokens.length), argumentStack); 
                }
            }
            else {
                argumentStack.peek().add(new VariableExpression(tokens[0].trim()));
                retrieveArguments(Arrays.copyOfRange(tokens, 1, tokens.length), argumentStack);
            }
        }
    }
}
