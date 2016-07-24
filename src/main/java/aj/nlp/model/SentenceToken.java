/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.model;

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
}
