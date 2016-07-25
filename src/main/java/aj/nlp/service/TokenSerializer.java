/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.service;

import aj.nlp.model.Token;

/**
 *
 * @author ajadriano
 */
public interface TokenSerializer {
    String serialize(Token token);
}
