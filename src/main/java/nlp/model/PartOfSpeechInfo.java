/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.model;

import java.util.Optional;

/**
 *
 * @author ajadriano
 */
public class PartOfSpeechInfo {
    private final PartOfSpeech partOfSpeech;
    private final Optional<FunctionTag> functionTag;
    
    public PartOfSpeechInfo(PartOfSpeech partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
        this.functionTag = Optional.empty();
    }
    
    public PartOfSpeechInfo(PartOfSpeech partOfSpeech, FunctionTag functionTag) {
        this.partOfSpeech = partOfSpeech;
        this.functionTag = Optional.of(functionTag);
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
}
