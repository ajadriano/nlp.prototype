/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.kb.service.implementation;

import aj.kb.service.KnowledgeBaseException;
import aj.kb.service.KnowledgeBaseService;
import aj.kb.service.Services;
import aj.nlp.model.TextCorpus;
import aj.nlp.service.LanguageProcessor;
import aj.nlp.service.TokenSerializer;
import aj.owl.service.ExecutionService;
import aj.owl.service.implementation.OwlExecutionService;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.w3c.dom.Document;

/**
 *
 * @author ajadriano
 */
public class DefaultKnowledgeBaseService implements KnowledgeBaseService {
    protected final String name;
    protected final String xslFile;
    
    protected final ExecutionService executionService;
    protected final LanguageProcessor processor;
    protected final TokenSerializer serializer;
    
    protected String xsl;
    
    public DefaultKnowledgeBaseService(Services services, String name, String xslFile) {
        this.name = name;
        this.executionService = new OwlExecutionService();
        this.xslFile = xslFile;
        this.processor = services.getLanguageProcessor();
        this.serializer = services.getTokenSerializer();
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
            
    @Override
    public String tell(String message) {
        TextCorpus textCorpus = processor.parseCorpus(message);
        Document xmlDocument = serializer.serialize(textCorpus);
        return executionService.execute(serializer.transform(xmlDocument, xsl)); 
    }

    @Override
    public void initialize() throws KnowledgeBaseException, IOException  {
        File file = new File("../domains/" + name);
        if (!file.exists()) {
            throw new KnowledgeBaseException("Domain does not exist.");
        }
        
        xsl = getContents(xslFile);
        executionService.initialize(name);
    }
    
    @Override
    public String loadFile(String file) throws IOException {
        return tell(getContents(file));
    }

    @Override
    public void save() {
        executionService.commit();
    }
    
    protected static String getContents(String file) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(file));        
        return new String(encoded, Charset.defaultCharset());
    }
    
}
