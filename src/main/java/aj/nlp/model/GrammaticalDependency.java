/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.model;

/**
 *
 * @author ajadriano
 * http://nlp.stanford.edu/software/dependencies_manual.pdf
 */
public enum GrammaticalDependency {
    root ("root"),
    pred ("predicate"),
    aux ("auxiliary"),
    auxpass ("passive auxiliary"),
    cop ("copula"),
    conj ("conjunct"),
    cc ("coordination"),
    punct ("punctation"),
    arg ("argument"),
    subj ("subject"),
    nsubj ("nominal subject"),
    nsubjpass ("nominal passive subject"),
    csubj ("clausal subject"),
    csubjpass ("clausal passive subject"),
    comp ("complement"),
    obj ("object"),
    dobj ("direct object"),
    iobj ("indirect object"),
    ccomp ("clausal complement"),
    xcomp ("open clausal complement"),
    ref ("referent"),    
    expl ("expletive"),
    modifier ("mod"),
    nmod ("nominal modifier"),
    advcl ("adverbial clause modifier"),
    relcl ("relative clause modifier"),
    mark ("marker"),
    amod ("adjectival modifier"),
    nummod ("numeric modifier"),
    compound ("compound modifier"),
    appos ("appositional modifier"),
    discourse ("discourse element"),
    acl ("clausal modifier"),
    advmod ("adverbial modifier"),
    neg ("negation modifier"),
    npadvmod ("noun phrase adverbial modifier"),
    tmod ("temporal modifier"),
    mwe ("multi-word expression"),
    det ("determiner"),
    predet ("predeterminer"),
    preconj ("preconjunct"),
    poss ("possession"),
    casemarker ("prepositional modifier"),
    prt ("phrasal verb particle"),
    parataxis ("parataxis"),
    goeswith ("goes with"),
    list ("list"),
    sdep ("semantic dependent"),
    agent ("agent"),
    acomp ("adjectival complement"),
    pobj ("object of preposition"),
    rcmod ("relative clause modifier"),
    quantmod ("quantifier modifier"),
    nn ("noun compound modifier"),
    number ("element of compound marker"),
    prep ("prepositional modifier"),
    possessive ("possessive modifier (’s)"),
    xsubj ("controlling subject"),
    unknown ("unknown");
    
    private final String description;
    
    private GrammaticalDependency(String description){
        this.description = description;
    }

    public String getDescription(){return description;}
}
