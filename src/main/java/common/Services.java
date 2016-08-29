/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import nlp.service.LanguageProcessor;
import xsl.service.FunctionParser;
import xsl.service.XslTransformService;

/**
 *
 * @author ajadriano
 */
public interface Services {
    void initialize();
    LanguageProcessor getLanguageProcessor();
    XslTransformService getXslTransformService();
    FunctionParser getFunctionParser();
}
