/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.service.implementation;

import aj.nlp.model.GrammaticalDependency;
import aj.nlp.model.GrammaticalRelation;
import aj.nlp.service.GrammarService;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import java.util.Collection;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

/**
 *
 * @author ajadriano
 */
public class DefaultGrammarService implements GrammarService {

    private final MultiValuedMap<Integer, GrammaticalRelation<Integer>> targetMap;
    
    private final int rootIndex;
    
    public DefaultGrammarService(SemanticGraph graph) {
        targetMap = new HashSetValuedHashMap<>();
        
        rootIndex = graph.getFirstRoot().index();
        
        for (SemanticGraphEdge edge : graph.edgeIterable()) {
            
            GrammaticalDependency dependency;
            
            try {
                String relation = edge.getRelation().toString();
                if (relation.contains(":")) {
                    relation = relation.substring(relation.indexOf(':') + 1, relation.length());
                }
                
                if (relation.equals("case")) {
                    dependency = GrammaticalDependency.casemarker;
                }
                else {
                    dependency = GrammaticalDependency.valueOf(relation);
                } 
            } catch (IllegalArgumentException e) {
                dependency = GrammaticalDependency.unknown;
            }
            
            GrammaticalRelation<Integer> relation = new GrammaticalRelation<>(
                        dependency,
                        edge.getTarget().index(),
                        edge.getSource().index());
            targetMap.put(relation.getTarget(), relation);
        }
    }
    
    @Override
    public Collection<GrammaticalRelation<Integer>> getTargetGrammaticalRelations(int token) {
        return targetMap.get(token);
    }

    /**
     * @return the rootIndex
     */
    public int getRootIndex() {
        return rootIndex;
    }
    
}
