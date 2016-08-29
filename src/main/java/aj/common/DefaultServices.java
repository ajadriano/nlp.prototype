/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.common;

import aj.nlp.service.LanguageProcessor;
import aj.nlp.service.implementation.DefaultLanguageProcessor;
import aj.xsl.service.implementation.DefaultXslTransformService;
import aj.xsl.service.FunctionParser;
import aj.xsl.service.implementation.DefaultFunctionParser;
import aj.xsl.service.XslTransformService;

/**
 *
 * @author ajadriano
 */
public class DefaultServices implements Services {
    private LanguageProcessor languageProcessor;
    private XslTransformService xslTransformService;
    private FunctionParser functionParser;
    
    @Override
    public void initialize() {
        languageProcessor = new DefaultLanguageProcessor();
        languageProcessor.initialize();
        
        functionParser = new DefaultFunctionParser();
        xslTransformService = new DefaultXslTransformService(functionParser);
    }

    /**
     * @return the languageProcessor
     */
    @Override
    public LanguageProcessor getLanguageProcessor() {
        return languageProcessor;
    }

    /**
     * @return the tokenSerializer
     */
    @Override
    public XslTransformService getXslTransformService() {
        return xslTransformService;
    }

    /**
     * @return the functionParser
     */
    @Override
    public FunctionParser getFunctionParser() {
        return functionParser;
    }
}
