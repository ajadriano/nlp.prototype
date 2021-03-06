/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kb.service;

import common.Interpreter;
import java.io.IOException;

/**
 *
 * @author ajadriano
 */
public interface KnowledgeBase extends Interpreter {
    String getName();
    
    void initialize() throws KnowledgeBaseException, IOException;
    void clear();
    String tell(String message);
    String loadFile(String file) throws IOException;
    void save();
}
