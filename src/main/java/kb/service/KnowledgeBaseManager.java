/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kb.service;

/**
 *
 * @author ajadriano
 */
public interface KnowledgeBaseManager {
    void setDirectory(String directory);
    String getDirectory();
    
    void create(String name) throws KnowledgeBaseException; 
    KnowledgeBase get(String name);
    KnowledgeBase get(String name, String xsltFile);
    
    KnowledgeBase getDebugKb();
    KnowledgeBase getDebugKb(String xsltFile);
}
