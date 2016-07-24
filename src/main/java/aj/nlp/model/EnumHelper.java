/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.model;

/**
 *
 * @author ajadriano
 */
public final class EnumHelper {
    
    public static NamedEntityTag toNamedEntityTag(String tag) {
        NamedEntityTag namedEntityTag;
        
        try {
            namedEntityTag = NamedEntityTag.valueOf(tag);
        } catch (IllegalArgumentException e) {
            namedEntityTag = NamedEntityTag.UNKNOWN;
        }
        
        return namedEntityTag;
    }
    
    public static PartOfSpeechInfo toPartOfSpeech(String tag) {
        
        PartOfSpeechInfo info = new PartOfSpeechInfo(PartOfSpeech.X);
        
        if (tag != null) {
            
            if (!tag.contains("-") ) {
                return new PartOfSpeechInfo(getPartOfSpeech(tag));
            }
            else {
                String pos = tag.substring(0, tag.indexOf('-'));
                String function = tag.substring(tag.indexOf('-') + 1, tag.length());
                return new PartOfSpeechInfo(getPartOfSpeech(pos), getFunctionTag(function));
            } 
        }
        
        return info;
    }
    
    private static PartOfSpeech getPartOfSpeech(String partOfSpeech) {
        PartOfSpeech pos;
        
        try {
            pos = PartOfSpeech.valueOf(partOfSpeech);
        } catch (IllegalArgumentException e) {
            pos = PartOfSpeech.X;
        }
        
        return pos;
    }
    
    private static FunctionTag getFunctionTag(String functionTag) {
        FunctionTag tag;
        
        try {
            tag = FunctionTag.valueOf(functionTag);
        } catch (IllegalArgumentException e) {
            tag = FunctionTag.X;
        }
        
        return tag;
    }
}
