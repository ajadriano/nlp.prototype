/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.service;

import nlp.model.SentenceToken;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.util.CoreMap;
import org.w3c.dom.Document;

/**
 *
 * @author ajadriano
 */
public interface LanguageProcessor {
    StanfordCoreNLP getPipeline();
    
    void initialize();
    Document parseCorpus(String corpus);
    SentenceToken assembleToken(SemanticGraph semanticGraph, CoreMap sentence);
}
