/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public class WordToken extends Token {
    private final int index;
    private final String text;
    private final String lemma;
    private final NamedEntityTag namedEntityTag;
    private final List<GrammaticalRelation<Token>> grammaticalRelations;
    
    private boolean grammarRoot;
    
    public WordToken(int index, Token parentToken, String text, String lemma, NamedEntityTag namedEntityTag,
            PartOfSpeechInfo partOfSpeechInfo) {
        super(parentToken, partOfSpeechInfo);
        this.index = index;
        this.text = text;
        this.lemma = lemma;
        this.namedEntityTag = namedEntityTag;
        this.grammaticalRelations = new ArrayList<>();
    }

    @Override
    public String getText() {
        return text;
    }

    /**
     * @return the grammaticalRelations
     */
    public List<GrammaticalRelation<Token>> getGrammaticalRelations() {
        return grammaticalRelations;
    }
    
    @Override
    public String toString() {
        return getText();
    }

    /**
     * @return the index
     */
    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getBeginIndex() {
        return index - 1;
    }

    @Override
    public int getEndIndex() {
        return index;
    }

    @Override
    public Token getToken(int index) {
        if (this.index == index) {
            return this;
        }
        
        return null;
    }

    /**
     * @return the grammarRoot
     */
    public boolean isGrammarRoot() {
        return grammarRoot;
    }

    /**
     * @param grammarRoot the grammarRoot to set
     */
    public void setGrammarRoot(boolean grammarRoot) {
        this.grammarRoot = grammarRoot;
    }

    /**
     * @return the lemma
     */
    public String getLemma() {
        return lemma;
    }

    /**
     * @return the namedEntityTag
     */
    public NamedEntityTag getNamedEntityTag() {
        return namedEntityTag;
    }

    @Override
    public String getName() {
        return lemma;
    }
}
