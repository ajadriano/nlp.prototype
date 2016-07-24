/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.prototype;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

/**
 *
 * @author ajadriano
 */
public class Token {
    private final CoreLabel token;
    
    public Token(CoreLabel token) {
        this.token = token;
    }
    
    public String getPos() {
        return PartOfSpeech.toPartOfSpeech(token.get(CoreAnnotations.PartOfSpeechAnnotation.class));
    }
    
    public String getNamedEntity() {
        return token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
    }
    
    public String getClasses() {
        StringBuilder sb = new StringBuilder();
        
        for(Class key : token.keySet()) {
            Object value = token.get(key);
            
            if (value != null && value.toString() != null && !value.toString().isEmpty()) {
                sb.append(key.getSimpleName()).append(" - [").append(value.toString()).append("]\n");
            }
            
        }
        
        return sb.toString();
    }

    @Override
    public String toString() {
        if (token.containsKey(CoreAnnotations.TextAnnotation.class)) {
            return token.get(CoreAnnotations.TextAnnotation.class);
        }
        return token.toString();
    }
    
    
    
    
}
