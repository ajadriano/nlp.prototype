/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kb.service.implementation;

import kb.service.KnowledgeBaseException;
import common.Services;
import java.io.IOException;

/**
 *
 * @author ajadriano
 */
public class DebugKnowledgeBase extends DefaultKnowledgeBase {
    
    public DebugKnowledgeBase(Services services, String xslFile) {
        super(services, "", "debugkb", xslFile);
    }
    
    @Override
    public void initialize() throws KnowledgeBaseException, IOException  {
        xsl = getContents(xslFile);
        executionService.initialize(name);
    }
    
    @Override
    public void save() {
    }
    
    @Override
    public void clear() {
        executionService.clear();
    }
}
