/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.service;

import aj.nlp.model.SentenceToken;
import aj.nlp.model.TextCorpus;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.util.CoreMap;

/**
 *
 * @author ajadriano
 */
public interface LanguageProcessor {
    StanfordCoreNLP getPipeline();
    
    void initialize();
    TextCorpus parseCorpus(String corpus);
    SentenceToken assembleToken(SemanticGraph semanticGraph, CoreMap sentence);
}
