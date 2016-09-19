/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kb.service.implementation;

import common.Interpreter;
import kb.service.KnowledgeBaseException;
import common.Services;
import nlp.model.TextCorpus;
import nlp.service.LanguageProcessor;
import owl.service.ExecutionService;
import owl.service.implementation.OwlExecutionService;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.w3c.dom.Document;
import kb.service.KnowledgeBase;
import xsl.service.XslTransformService;

/**
 *
 * @author ajadriano
 */
public class DefaultKnowledgeBase implements KnowledgeBase, Interpreter {
    protected final String name;
    protected final String xslFile;
    
    protected final ExecutionService executionService;
    protected final LanguageProcessor processor;
    protected final XslTransformService serializer;
    
    protected String xsl;
    
    public DefaultKnowledgeBase(Services services, String directory, String name, String xslFile) {
        this.name = name;
        this.executionService = new OwlExecutionService(directory);
        this.xslFile = xslFile;
        this.processor = services.getLanguageProcessor();
        this.serializer = services.getXslTransformService();
    }
    
    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }
            
    @Override
    public String tell(String message) {
        StringBuilder sb = new StringBuilder();
        List<String> expressions = interpretByLine(message); 
        
        for (int i = 0; i < expressions.size(); i++) {
            sb.append(expressions.get(i));
            if (i < expressions.size() - 1) {
               sb.append(", "); 
            } 
        }
        
        return sb.toString();
    }
    
    @Override
    public List<String> interpretByLine(String input) {
        Document xmlDocument = processor.parseCorpus(input);
        return executionService.execute(serializer.transformToExpressions(xmlDocument, xsl)); 
    }

    @Override
    public void initialize() throws KnowledgeBaseException, IOException  {
        File file = new File("domains/" + name);
        if (!file.exists()) {
            throw new KnowledgeBaseException("Domain does not exist.");
        }
        
        xsl = getContents(xslFile);
        executionService.initialize(name);
    }
    
    @Override
    public void clear() {
        System.out.println("Not supported.");
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
