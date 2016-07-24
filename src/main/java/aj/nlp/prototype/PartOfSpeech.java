/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ajadriano
 */
public final class PartOfSpeech {
    private static final Map<String, String> dictionary = new HashMap<String, String>(){{
        put("CC","Coordinating conjunction");
        put("CD","Cardinal number");
        put("DT","Determiner");
        put("EX","Existential there");
        put("FW","Foreign word");
        put("IN","Preposition or subordinating conjunction");
        put("JJ","Adjective");
        put("JJR","Adjective, comparative");
        put("JJS","Adjective, superlative");
        put("LS","List item marker");
        put("MD","Modal");
        put("NN","Noun, singular or mass");
        put("NNS","Noun, plural");
        put("NNP","Proper noun, singular");
        put("NNPS","Proper noun, plural");
        put("PDT","Predeterminer");
        put("POS","Possessive ending");
        put("PRP","Personal pronoun");
        put("PRP$","Possessive pronoun");
        put("RB","Adverb");
        put("RBR","Adverb, comparative");
        put("RBS","Adverb, superlative");
        put("RP","Particle");
        put("SYM","Symbol");
        put("TO","to");
        put("UH","Interjection");
        put("VB","Verb, base form");
        put("VBD","Verb, past tense");
        put("VBG","Verb, gerund or present participle");
        put("VBN","Verb, past participle");
        put("VBP","Verb, non-3rd person singular present");
        put("VBZ","Verb, 3rd person singular present");
        put("WDT","Wh-determiner");
        put("WP","Wh-pronoun");
        put("WP$","Possessive wh-pronoun");
        put("WRB","Wh-adverb");
        put("NP","Noun phrase");
        put("PP","Prepositional phrase");
        put("VP","Verb phrase");
        put("ADVP","Adverb phrase");
        put("ADJP","Adjective phrase");
        put("CONJP","Conjunction Phrase");
        put("SBAR","Subordinating conjunction");
        put("PRT","Particle");
        put("INTJ","Interjection");
        put("PNP","Prepositional noun phrase");
        put("S", "Simple declarative clause");
        put("SBARQ", "Direct question introduced by wh-element");
        put("SINV", "Declarative sentence with subject-aux inversion");
        put("SQ", "Yes/no question");
        put("LST", "List marker");
        put("NAC", "Not a constituent");
        put("NX", "Complex noun phrase");
        put("PRN", "Parenthetical");
        put("QP", "Quantifier phrase");
        put("RRC", "Reduced relative clause");
        put("UCP", "Unlike coordinated phrase");
        put("WHADJP", "Wh-adjective phrase");
        put("WHAVP", "Wh-adverb phrase");
        put("WHNP", "Wh-noun phrase");
        put("WHPP", "Wh-prepositional phrase");
        put("X", "Unknown");
    }};
    
    private static final Map<String, String> functionTags = new HashMap<String, String>(){{
        put("ADV", "adverbial");
        put("NOM", "nominal");
        put("DTV", "dative");
        put("LGS", "logical subject");
        put("PRD", "predicate");
        put("PUT", "locative complement of put");
        put("SBJ", "surface subject");
        put("TPC", "topicalized");
        put("VOC", "vocative");
        put("BNF", "benefactive");
        put("DIR", "direction");
        put("EXT", "extent");
        put("LOC", "locative");
        put("MNR", "manner");
        put("PRP", "purpose or reason");
        put("TMP", "temporal");
        put("CLR", "closely related");
        put("CLF", "cleft");
        put("HLN", "headline");
        put("TTL", "title");
    }};
    
    public static String toPartOfSpeech(String tag) {
        
        
        if (tag != null) {
            
            if (!tag.contains("-") ) {
                if (dictionary.containsKey(tag)) {
                    return dictionary.get(tag);
                }
            }
            else {
                String clause = tag.substring(0, tag.indexOf('-'));
                String function = tag.substring(tag.indexOf('-') + 1, tag.length());
                
                StringBuilder sb = new StringBuilder();
                
                if (dictionary.containsKey(clause)) {
                    sb.append(dictionary.get(clause));
                }
                else {
                    sb.append(clause);
                }
                 
                sb.append("-");
                
                if (functionTags.containsKey(function)) {
                    sb.append(functionTags.get(function));
                }
                else {
                    sb.append(function);
                }
                return sb.toString();
            } 
        }
        
        return tag;
    }
}
