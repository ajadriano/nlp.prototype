/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.model;

/**
 *
 * @author ajadriano
 */
public enum FunctionTag {
    ADV ("adverbial"),
    NOM ("nominal"),
    DTV ("dative"),
    LGS ("logical subject"),
    PRD ("predicate"),
    PUT ("locative complement of put"),
    SBJ ("surface subject"),
    TPC ("topicalized"),
    VOC ("vocative"),
    BNF ("benefactive"),
    DIR ("direction"),
    EXT ("extent"),
    LOC ("locative"),
    MNR ("manner"),
    PRP ("purpose or reason"),
    TMP ("temporal"),
    CLR ("closely related"),
    CLF ("cleft"),
    HLN ("headline"),
    TTL ("title"),
    X ("unknown");
    
    private final String description;
    
    private FunctionTag(String description){
        this.description = description;
    }

    public String getDescription(){return description;}
}
