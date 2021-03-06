/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ajadriano
 */
public class PhraseToken extends Token {
    
    private final int beginIndex;
    private final int endIndex;
    
    public PhraseToken(int beginIndex, int endIndex, Token parentToken, PartOfSpeechInfo partOfSpeechInfo) {
        super(parentToken, partOfSpeechInfo);
        
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }
    
    @Override
    public String toString() {
        return getPartOfSpeech().getDescription();
    }
    
    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        
        boolean flag = false;
        
        for (Token token : getTokens()) {
            if (flag && !(token.getPartOfSpeech() == PartOfSpeech.PUNCT)) {
               sb.append(" "); 
            }
            else {
                flag = true;
            }
            
            sb.append(token.getText());           
        }
        
        return sb.toString();
    }

    @Override
    public int getIndex() {
        List<Token> tokens = getTokens();
        if (!tokens.isEmpty()) {
            return getTokens().stream().mapToInt((token) -> token.getIndex()).min().getAsInt();
        }
        
        return -1;
    }

    @Override
    public int getBeginIndex() {
        return beginIndex;
    }

    @Override
    public int getEndIndex() {
        return endIndex;
    }

    @Override
    public Token getToken(int index) {
        if (index > this.beginIndex && index <= this.endIndex) {
            for (Token token : getTokens()) {
                Token result = token.getToken(index);
                if (result != null) {
                    return result;
                }
            }
        }
        
        return null;
    }

    @Override
    public List<GrammaticalRelation<Token>> getTargetGrammaticalRelations() {        
        return null;
    }

    @Override
    public boolean isGrammarRoot() {
        return false;
    }

    @Override
    public String getLemma() {        
        return null;
    }

    @Override
    public NamedEntityTag getNamedEntityTag() {
        return null;
    }

    @Override
    public String getName() {
        StringBuilder sb = new StringBuilder();
        
        boolean flag = false;
        
        for (Token token : getTokens()) {
            if (flag) {
                sb.append(capitalizeFirstLetter(token.getName())); 
            }
            else {
                sb.append(token.getName());    
                flag = true;
            } 
        }
        
        return sb.toString();
    }
    
    private static String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    @Override
    public Element writeXML(Document document) {
        Element element = document.createElement(this.getPartOfSpeech().toString());
        for (Token token : getTokens()) {
            Element childElement = token.writeXML(document);
            element.appendChild(childElement);
        }
        
        return element;
    }

    @Override
    public Integer getCoreference() {
        return null;
    }
}
