/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service;

import java.util.List;

/**
 *
 * @author ajadriano
 */
public interface ExecutionService {
    void initialize(String ontology);
    List<String> execute(String statement);
    void commit();
}
