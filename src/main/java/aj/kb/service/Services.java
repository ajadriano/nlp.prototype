/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.kb.service;

import aj.nlp.service.LanguageProcessor;
import aj.nlp.service.TokenSerializer;
import aj.owl.service.FunctionParser;

/**
 *
 * @author ajadriano
 */
public interface Services {
    LanguageProcessor getLanguageProcessor();
    TokenSerializer getTokenSerializer();
    FunctionParser getFunctionParser();
}
