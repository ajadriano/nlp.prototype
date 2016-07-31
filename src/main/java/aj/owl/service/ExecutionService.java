/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service;

/**
 *
 * @author ajadriano
 */
public interface ExecutionService {
    void initialize();
    void execute(String statement);
    void commit();
}
