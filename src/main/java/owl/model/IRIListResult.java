/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.model;

import java.util.List;
import org.semanticweb.owlapi.model.IRI;

/**
 *
 * @author ajadriano
 */
public class IRIListResult extends Result<List<IRI>> {
    
    public IRIListResult(List<IRI> result) {
        super(result);
    }
    
}
