/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.service;

import aj.nlp.model.Token;
import org.w3c.dom.Document;

/**
 *
 * @author ajadriano
 */
public interface TokenSerializer {
    Document serialize(Token token);
    String transform(Document doc);
    String transform(Document doc, String xslt); 
}
