/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.common;

import aj.common.Services;
import aj.nlp.service.LanguageProcessor;
import aj.nlp.service.TokenSerializer;
import aj.owl.service.FunctionParser;

/**
 *
 * @author ajadriano
 */
public class DefaultServices implements Services {
    private final LanguageProcessor languageProcessor;
    private final TokenSerializer tokenSerializer;
    private final FunctionParser functionParser;
    
    public DefaultServices(LanguageProcessor languageProcessor,
            TokenSerializer tokenSerializer,
            FunctionParser functionParser){
        this.languageProcessor = languageProcessor;
        this.tokenSerializer = tokenSerializer;
        this.functionParser = functionParser;
    }

    /**
     * @return the languageProcessor
     */
    public LanguageProcessor getLanguageProcessor() {
        return languageProcessor;
    }

    /**
     * @return the tokenSerializer
     */
    public TokenSerializer getTokenSerializer() {
        return tokenSerializer;
    }

    /**
     * @return the functionParser
     */
    public FunctionParser getFunctionParser() {
        return functionParser;
    }
}
