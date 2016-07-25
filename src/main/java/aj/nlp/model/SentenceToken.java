/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ajadriano
 */
public class SentenceToken extends PhraseToken {
    private Token rootGrammarToken;
    
    public SentenceToken(int beginIndex, int endIndex) {
        super(beginIndex, endIndex, null, new PartOfSpeechInfo(PartOfSpeech.ROOT));
    }

    /**
     * @return the rootGrammarToken
     */
    public Token getRootGrammarToken() {
        return rootGrammarToken;
    }

    /**
     * @param rootGrammarToken the rootGrammarToken to set
     */
    public void setRootGrammarToken(Token rootGrammarToken) {
        this.rootGrammarToken = rootGrammarToken;
    }
    
    @Override
    public void writeXML(Document document, Node parentNode) {
        Element element = document.createElement(this.getPartOfSpeech().toString());
        
        if (rootGrammarToken != null) {
            element.setAttribute("root", Integer.toString(rootGrammarToken.getIndex()));
        }
        
        for (Token token : getTokens()) {
            token.writeXML(document, element);
        }
        
        element.setUserData("token", this, null);
        parentNode.appendChild(element);
    }
}
