/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.service;

import aj.nlp.model.SentenceToken;
import edu.stanford.nlp.util.CoreMap;

/**
 *
 * @author ajadriano
 */
public interface LanguageProcessor {
    SentenceToken assembleToken(CoreMap sentence);
}
