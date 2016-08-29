/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xsl.service;

import xsl.model.Expression;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public interface FunctionParser {
    List<Expression> parse(String statement);
}
