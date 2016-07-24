/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.prototype;

import edu.stanford.nlp.hcoref.CorefCoreAnnotations.CorefClusterIdAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public class POSToken {
    private final CoreLabel token;
    private final String pos;
    
    private String grammar; 
    private Collection<GrammarToken> grammarTokens;
    private boolean root;
    private String reference;
    
    private List<POSToken> childTokens;
    private POSToken nextToken;
    private POSToken previousToken;
    
    public POSToken(CoreLabel token) {
        this.nextToken = null;
        this.token = token;
        this.pos = PartOfSpeech.toPartOfSpeech(token.value());
        this.childTokens = new ArrayList<POSToken>() {};
    }
    
    public String getClasses() {
        StringBuilder sb = new StringBuilder();
        
        for(Class key : token.keySet()) {
            Object value = token.get(key);
            
            if (value != null && value.toString() != null && !value.toString().isEmpty()) {
                
                if (key.getSimpleName().equals("PartOfSpeechAnnotation")) {
                    sb.append(key.getSimpleName()).append(" - [").
                            append(PartOfSpeech.toPartOfSpeech(value.toString())).append("]\n");
                }
                else if (key.getSimpleName().equals("CorefClusterIdAnnotation") &&
                        this.reference != null && !this.reference.isEmpty()) {
                    sb.append(key.getSimpleName()).append(" - [").append(reference).append("]\n");
                }
                else {
                    sb.append(key.getSimpleName()).append(" - [").append(value.toString()).append("]\n");
                }    
            }
        }
        
        if (grammar != null) {
            sb.append("\nGrammar\n");
            sb.append(grammar);
        }
        
        if (grammarTokens != null) {
            sb.append("\nGrammar\n");
            for (GrammarToken token : grammarTokens) {
                sb.append(token.getGrammar()).append(" - ").append(token.getSource()).append("\n");
            }
        }
        
        if (root) {
            sb.append("\nGrammar\nROOT");
        }
        
        return sb.toString();
    }
    
    public String getGrammar() {
        return grammar;
    }
    
    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }
    
    public Collection<GrammarToken> getGrammarTokens() {
        return this.grammarTokens;
    }
    
    public void setGrammarToken(Collection<GrammarToken> grammarTokens) {
        this.grammarTokens = grammarTokens;
    }
    
    public boolean isRoot() {
        return root;
    }
    
    public void setIsRoot(boolean root) {
        this.root = root;
    }
    
    public int getCoref() {
        if (token.containsKey(CorefClusterIdAnnotation.class)) {
            return token.get(CorefClusterIdAnnotation.class);
        }
        
        return -1;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public String getReference() {
        return this.reference;
    }
    
    public List<POSToken> getChildren() {
        return childTokens;
    }
    
    public POSToken getFirstChild() {
        if (!childTokens.isEmpty()) {
            return childTokens.get(0);
        }
        
        return null;
    }
    
    public void addChild(POSToken token) {
        childTokens.add(token);
    }
    
    public void removeChild(POSToken token) {
        childTokens.remove(token);
    }
    
    public POSToken getNextToken() {
        return nextToken;
    }
    
    public void setNextToken(POSToken posToken) {
        nextToken = posToken;
    }
    
    public POSToken getPreviousToken() {
        return previousToken;
    }
    
    public void setPreviousToken(POSToken posToken) {
        previousToken = posToken;
    }
    
    public boolean hasGrammarRelation(String grammar) {
        if (grammarTokens != null) {
            return grammarTokens.stream().anyMatch((grammarToken) -> (grammarToken.getGrammar().equals(grammar)));
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        return pos;
    }
}
