/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.prototype;

import edu.stanford.nlp.semgraph.SemanticGraphEdge;

/**
 *
 * @author ajadriano
 */
public class GrammarToken {
    private final SemanticGraphEdge semanticGraphEdge;
    
    
    public GrammarToken(SemanticGraphEdge semanticGraphEdge) {
        this.semanticGraphEdge = semanticGraphEdge;
    }
    
    public String getTarget() {
       return semanticGraphEdge.getTarget().originalText();
    }
    
    public String getSource() {
       return semanticGraphEdge.getSource().originalText();
    }
    
    public String getGrammar() {
       return semanticGraphEdge.getRelation().getLongName();
    }
    
    @Override
    public String toString() {
        return getGrammar() + " - " + getTarget();
    }
}
