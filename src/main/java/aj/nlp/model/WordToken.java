/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
    private final Integer coreference;
    
    private boolean grammarRoot;
    
    public WordToken(int index, Token parentToken, String text, String lemma, 
            NamedEntityTag namedEntityTag,
            PartOfSpeechInfo partOfSpeechInfo,
            Integer coreference) {
        super(parentToken, partOfSpeechInfo);
        this.index = index;
        this.text = text;
        this.lemma = lemma;
        this.namedEntityTag = namedEntityTag;
        this.grammaticalRelations = new ArrayList<>();
        this.coreference = coreference;
    }

    @Override
    public String getText() {
        return text;
    }

    /**
     * @return the grammaticalRelations
     */
    @Override
    public List<GrammaticalRelation<Token>> getTargetGrammaticalRelations() {
        return grammaticalRelations;
    }
    
    @Override
    public String toString() {
        return text;
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
    @Override
    public NamedEntityTag getNamedEntityTag() {
        return namedEntityTag;
    }

    @Override
    public String getName() {
        return text;
    }

    @Override
    public Element writeXML(Document document)  {
        Element element = document.createElement(StringEscapeUtils.escapeXml11(this.getPartOfSpeech().toString()));
        element.setTextContent(text);
        element.setAttribute("lemma", lemma);
        element.setAttribute("id", Integer.toString(getIndex()));
        
        if (namedEntityTag != NamedEntityTag.O && namedEntityTag != NamedEntityTag.UNKNOWN) {           
            element.setAttribute("entity", namedEntityTag.toString());
        }
        
        if (coreference != null) {
            element.setAttribute("coref", coreference.toString());
        }
        
        for (GrammaticalRelation<Token> relation : getTargetGrammaticalRelations()) {
            element.setAttribute(relation.getDependency().toString(), Integer.toString(relation.getSource().getIndex()));
        }
        
        return element;
    }

    /**
     * @return the coreference
     */
    public Integer getCoreference() {        
        return coreference;
    }
}
