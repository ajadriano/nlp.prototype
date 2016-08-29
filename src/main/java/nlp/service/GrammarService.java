/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.service;

import nlp.model.GrammaticalRelation;
import java.util.Collection;

/**
 *
 * @author ajadriano
 */
public interface GrammarService {

    int getRootIndex();
    
    /**
     *
     * @param index
     * @return
     */
    Collection<GrammaticalRelation<Integer>> getTargetGrammaticalRelations(int index);
}
