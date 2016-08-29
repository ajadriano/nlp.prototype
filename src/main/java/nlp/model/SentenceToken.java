/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    public Element writeXML(Document document) {
        if (getTokens().size() > 0) {
            Element childElement = getTokens().get(0).writeXML(document);
            if (rootGrammarToken != null) {
                childElement.setAttribute("root", Integer.toString(rootGrammarToken.getIndex()));
            }

            return childElement;
        }
        
        return null;
    }
}
