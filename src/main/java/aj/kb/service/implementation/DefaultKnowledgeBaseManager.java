/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.kb.service.implementation;

import aj.common.Services;
import aj.kb.service.KnowledgeBase;
import aj.kb.service.KnowledgeBaseException;
import aj.kb.service.KnowledgeBaseManager;
import java.io.File;

/**
 *
 * @author ajadriano
 */
public class DefaultKnowledgeBaseManager implements KnowledgeBaseManager {
    private String directory;
    private final Services services;
            
    public DefaultKnowledgeBaseManager(Services services, String directory) {
        this.services = services;
        this.directory = directory;
    }

    @Override
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    @Override
    public String getDirectory() {
        return this.directory;
    }
            
    @Override
    public void create(String name) throws KnowledgeBaseException {
        File file = new File(directory + name);
        if (!file.exists()) {
            file.mkdir();
        }
        else {
            throw new KnowledgeBaseException("Knowledge base exists.");
        }
    }

    @Override
    public KnowledgeBase get(String name) {
        return new DefaultKnowledgeBase(services, directory, name, "default.xslt");
    }
    
    @Override
    public KnowledgeBase get(String name, String xsltFile) {
        return new DefaultKnowledgeBase(services, directory, name, xsltFile);
    }

    @Override
    public KnowledgeBase getDebugKb() {
        return new DebugKnowledgeBase(services, "default.xslt");
    }

    @Override
    public KnowledgeBase getDebugKb(String xsltFile) {
        return new DebugKnowledgeBase(services, xsltFile);
    }
}
