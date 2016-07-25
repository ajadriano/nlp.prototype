/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.model;

/**
 *
 * @author ajadriano
 * http://web.mit.edu/6.863/www/PennTreebankTags.html
 */
public enum PartOfSpeech {
    ROOT ("ROOT"),
    CC ("Coordinating conjunction"),
    CD ("Cardinal number"),
    DT ("Determiner"),
    EX ("Existential there"),
    FW ("Foreign word"),
    IN ("Preposition or subordinating conjunction"),
    JJ ("Adjective"),
    JJR ("Adjective, comparative"),
    JJS ("Adjective, superlative"),
    LS ("List item marker"),
    MD ("Modal"),
    NN ("Noun, singular or mass"),
    NNS ("Noun, plural"),
    NNP ("Proper noun, singular"),
    NNPS ("Proper noun, plural"),
    PDT ("Predeterminer"),
    POS ("Possessive ending"),
    PRP ("Personal pronoun"),
    PRPS ("Possessive pronoun"),
    RB ("Adverb"),
    RBR ("Adverb, comparative"),
    RBS ("Adverb, superlative"),
    RP ("Particle"),
    SYM ("Symbol"),
    TO ("to"),
    UH ("Interjection"),
    VB ("Verb, base form"),
    VBD ("Verb, past tense"),
    VBG ("Verb, gerund or present participle"),
    VBN ("Verb, past participle"),
    VBP ("Verb, non-3rd person singular present"),
    VBZ ("Verb, 3rd person singular present"),
    WDT ("Wh-determiner"),
    WP ("Wh-pronoun"),
    WP$ ("Possessive wh-pronoun"),
    WRB ("Wh-adverb"),
    NP ("Noun phrase"),
    PP ("Prepositional phrase"),
    VP ("Verb phrase"),
    ADVP ("Adverb phrase"),
    ADJP ("Adjective phrase"),
    CONJP ("Conjunction Phrase"),
    SBAR ("Subordinating conjunction"),
    PRT ("Particle"),
    INTJ ("Interjection"),
    PNP ("Prepositional noun phrase"),
    S ("Simple declarative clause"),
    SBARQ ("Direct question introduced by wh-element"),
    SINV ("Declarative sentence with subject-aux inversion"),
    SQ ("Yes/no question"),
    LST ("List marker"),
    NAC ("Not a constituent"),
    NX ("Complex noun phrase"),
    PRN ("Parenthetical"),
    QP ("Quantifier phrase"),
    RRC ("Reduced relative clause"),
    UCP ("Unlike coordinated phrase"),
    WHADJP ("Wh-adjective phrase"),
    WHAVP ("Wh-adverb phrase"),
    WHNP ("Wh-noun phrase"),
    WHPP ("Wh-prepositional phrase"),
    PUNCT ("Punctuation"),
    X ("Unknown");
    
    private final String description;
    
    private PartOfSpeech(String description){
        this.description = description;
    }

    public String getDescription(){return description;}
}
