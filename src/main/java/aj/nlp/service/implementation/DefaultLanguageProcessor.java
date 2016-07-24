/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.service.implementation;

import aj.nlp.model.EnumHelper;
import aj.nlp.model.GrammaticalDependency;
import aj.nlp.model.GrammaticalRelation;
import aj.nlp.model.PartOfSpeech;
import aj.nlp.model.PartOfSpeechInfo;
import aj.nlp.model.PhraseToken;
import aj.nlp.model.SentenceToken;
import aj.nlp.model.Token;
import aj.nlp.model.WordToken;
import aj.nlp.service.GrammarService;
import edu.stanford.nlp.ling.CoreAnnotations.CategoryAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.OriginalTextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.semgraph.SemanticGraph;
import java.util.Collection;
import aj.nlp.service.LanguageProcessor;
import edu.stanford.nlp.ling.CoreAnnotations.BeginIndexAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.EndIndexAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.IndexAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public class DefaultLanguageProcessor implements LanguageProcessor {

    private final GrammarService grammarService;
    
    public DefaultLanguageProcessor(SemanticGraph graph) {
        this(new DefaultGrammarService(graph));
    }
    
    public DefaultLanguageProcessor(GrammarService grammarService) {
        this.grammarService = grammarService;
    }
    
    @Override
    public SentenceToken assembleToken(CoreMap sentence) {
        Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
        CoreLabel coreLabel = (CoreLabel) tree.label();
        SentenceToken token = new SentenceToken(coreLabel.get(BeginIndexAnnotation.class), coreLabel.get(EndIndexAnnotation.class));
        List<WordToken> wordTokens = new ArrayList<>();
        
        if (!tree.isLeaf()) {
            addChildNodes(wordTokens, token, tree.children());
        }
        
        populateGrammarRelations(token, wordTokens);
        
        return token;
    } 
    
    private void populateGrammarRelations(SentenceToken rootToken, List<WordToken> wordTokens) {
        for (WordToken wordToken : wordTokens) {
            Collection<GrammaticalRelation<Integer>> relations = grammarService.getGrammaticalRelations(wordToken.getIndex());
            List<GrammaticalRelation<Token>> list = wordToken.getGrammaticalRelations();
            for (GrammaticalRelation<Integer> relation : relations) {
                Token source = rootToken.getToken(relation.getSource());
                if (source != null) {
                    list.add(new GrammaticalRelation(relation.getDependency(), wordToken, source));
                }
            }
            
            if (wordToken.getIndex() == grammarService.getRootIndex()) {
                rootToken.setRootGrammarToken(wordToken);
                wordToken.setGrammarRoot(true);
            }
        }
    }
    
    private void addChildNodes(List<WordToken> wordTokens, Token token, Tree[] children) {
        for (Tree child : children) {     
            Tree childToAdd = child;
            Tree grandChild = getLeaf(child);
            if (grandChild != null) {
                childToAdd = grandChild;
            }
            
            if (childToAdd.isLeaf()) {
                WordToken leafToken = createWordToken(token, (CoreLabel) childToAdd.label());
                token.addToken(leafToken); 
                wordTokens.add(leafToken);
            }
            else {
  
                Token childToken = createPhraseToken(token, (CoreLabel)childToAdd.label());
                if (childToken != null) {
                    token.addToken(childToken);
                    addChildNodes(wordTokens, childToken, childToAdd.children());
                }
            } 
        }    
    }
    
    private Tree getLeaf(Tree node) {
        Tree[] children = node.children();
        if (children != null && children.length == 1) {
            if (children[0].isLeaf()) {
                return children[0];
            }
            else {
                return getLeaf(children[0]);
            }
        }
        
        return null;
    }
    
    public WordToken createWordToken(Token parentToken, CoreLabel coreLabel) {
        if (coreLabel.containsKey(PartOfSpeechAnnotation.class) && 
            coreLabel.containsKey(OriginalTextAnnotation.class) &&
            coreLabel.containsKey(LemmaAnnotation.class) &&
            coreLabel.containsKey(NamedEntityTagAnnotation.class) &&
            coreLabel.containsKey(IndexAnnotation.class)) {
            
            String text = coreLabel.get(OriginalTextAnnotation.class);
            int index = coreLabel.get(IndexAnnotation.class);
            if (isPunctuation(index)) {
                return new WordToken(index, parentToken, text, coreLabel.get(LemmaAnnotation.class), 
                       EnumHelper.toNamedEntityTag(coreLabel.get(NamedEntityTagAnnotation.class)), 
                       new PartOfSpeechInfo(PartOfSpeech.PUNCT));
            }
            else {
                PartOfSpeechInfo info = EnumHelper.toPartOfSpeech(coreLabel.get(PartOfSpeechAnnotation.class));     
                return new WordToken(index, parentToken, text, coreLabel.get(LemmaAnnotation.class), 
                        EnumHelper.toNamedEntityTag(coreLabel.get(NamedEntityTagAnnotation.class)), info);
            }
        }
        return null;
    }
    
    public PhraseToken createPhraseToken(Token parentToken, CoreLabel coreLabel) {
        if (coreLabel.containsKey(CategoryAnnotation.class) &&
            coreLabel.containsKey(BeginIndexAnnotation.class) &&
            coreLabel.containsKey(EndIndexAnnotation.class)) {
            PartOfSpeechInfo info = EnumHelper.toPartOfSpeech(coreLabel.get(CategoryAnnotation.class)); 
            return new PhraseToken(
                    coreLabel.get(BeginIndexAnnotation.class),
                    coreLabel.get(EndIndexAnnotation.class),
                    parentToken, info);
        }
        
        return null;
    }
    
    private boolean isPunctuation(int index) {
        Collection<GrammaticalRelation<Integer>> relations = grammarService.getGrammaticalRelations(index);
        return relations.stream().anyMatch((relation) -> (relation.getDependency() == GrammaticalDependency.punct));
    }
}
