/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kb.service.implementation;

import common.Services;
import kb.service.KnowledgeBase;
import kb.service.KnowledgeBaseException;
import kb.service.KnowledgeBaseManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdir();
        }
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
    public void remove(String name) throws KnowledgeBaseException {
        File file = new File(directory + name);
        if (file.exists()) {
            deleteDirectory(file);
        }
        else {
            throw new KnowledgeBaseException("Knowledge base does not exists.");
        }
    }
    
    @Override
    public List<String> list() {
        List<String> domains = new ArrayList();
        
        File dir = new File(directory);
        
        if(dir.exists()){
            File[] files = dir.listFiles();
            if(null!=files){
                for (File file : files) {
                    if (file.isDirectory()) {
                        domains.add(file.getName());
                    }
                }
            }
        }
        
        return domains;
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
    
    //http://stackoverflow.com/questions/3775694/deleting-folder-from-java
    public void deleteDirectory(File directory) {
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
        directory.delete();
    }
}
