/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ajadriano
 */
public abstract class Token {
    
    private final PartOfSpeech partOfSpeech;
    private final Optional<FunctionTag> functionTag;
    private final Token parentToken;
    private List<Token> tokens;
    
    public Token(Token parentToken, PartOfSpeechInfo partOfSpeechInfo) {
        this.parentToken = parentToken;
        this.partOfSpeech = partOfSpeechInfo.getPartOfSpeech();
        this.functionTag = partOfSpeechInfo.getFunctionTag();
    }
    
    /**
     * @return the parentToken
     */
    public Token getParentToken() {
        return parentToken;
    }
    
    public void addToken(Token token) {
        createTokenList();
        tokens.add(token);
    }
    
    public void removeToken(Token token) {
        if (tokens != null) {
            tokens.remove(token);
        }
    }
    
    public List<Token> getTokens() {
        createTokenList();
        return tokens;
    }
    
    private void createTokenList() {
        if (tokens == null) {
           tokens = new ArrayList<>(); 
        }
    }
    
    /**
     * @return the partOfSpeech
     */
    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }
    
    /**
     * @return the functionTag
     */
    public Optional<FunctionTag> getFunctionTag() {
        return functionTag;
    }
    
    public abstract String getName();
    public abstract NamedEntityTag getNamedEntityTag();
    public abstract String getLemma(); 
    public abstract boolean isGrammarRoot();
    public abstract Token getToken(int index);
    public abstract String getText();
    public abstract int getIndex();
    public abstract int getBeginIndex();
    public abstract int getEndIndex();
    public abstract List<GrammaticalRelation<Token>> getGrammaticalRelations();
    
}
