/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.model;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ajadriano
 */
public class TextCorpus {
    
    private final List<SentenceToken> sentences;
    
    public TextCorpus() {
        sentences = new ArrayList();
    }

    /**
     * @return the sentences
     */
    public List<SentenceToken> getSentences() {
        return sentences;
    }
    
    public Element writeXML(Document document) {
        Element element = document.createElement("ROOT");
        for (Token token : getSentences()) {
            Element childElement = token.writeXML(document);
            element.appendChild(childElement);
        }
        
        return element;
    }
}
