/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.kb.service.implementation;

import aj.kb.service.KnowledgeBaseException;
import aj.common.Services;
import java.io.IOException;

/**
 *
 * @author ajadriano
 */
public class DebugKnowledgeBaseService extends DefaultKnowledgeBaseService {
    
    public DebugKnowledgeBaseService(Services services, String xslFile) {
        super(services, "debugkb", xslFile);
    }
    
    @Override
    public void initialize() throws KnowledgeBaseException, IOException  {
        xsl = getContents(xslFile);
        executionService.initialize(name);
    }
    
    @Override
    public void save() {
    }
}
