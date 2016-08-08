/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.kb.service.implementation;

import aj.kb.service.AdminService;
import aj.nlp.model.TextCorpus;
import aj.nlp.service.LanguageProcessor;
import aj.nlp.service.implementation.DefaultLanguageProcessor;
import aj.nlp.service.implementation.DefaultTokenSerializer;
import aj.owl.service.implementation.OwlExecutionService;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

/**
 *
 * @author ajadriano
 */
public class DefaultAdminService implements AdminService {
    
    private LanguageProcessor processor;
    
    @Override
    public void initialize() {
        processor = new DefaultLanguageProcessor();
        processor.initialize();
    }
    
    public String createKnowledgeBase(String name) {
        File file = new File("C:\\domains\\" + name);
        if (!file.exists()) {
            file.mkdir();
            return "Domain created.";
        }
        else {
            return "Knowledge base exists.";
        }
    }
    
    public String loadKnowledgeBase(String name) {
        File file = new File("C:\\domains\\" + name);
        if (!file.exists()) {
            return "Domain does not exist.";
        }
        
        OwlExecutionService executionService = new OwlExecutionService();
        executionService.initialize(name);
        
        String operation = "exec";
        String xsl;
        try {
            xsl = getContents("default.xslt");
        } catch (IOException ex) {
            return ex.getMessage();
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        do {
            System.out.print(name + ">");
            
            try { 
                input = br.readLine();
            } catch (IOException ex) {
                return ex.getMessage();
            }
            
            if ("debugxml".equals(input)) {
                operation = "xml";
                System.out.println("Debugging nlp xml.");
            }
            else if ("debugfunc".equals(input)) {
                operation = "func";
                System.out.println("Debugging functional transformation.");
            }
            else if ("debugexec".equals(input)) {
                operation = "exec";
                System.out.println("Executing statements and queries.");
            }
            else if (input.trim().endsWith(".") || input.trim().endsWith("?")) {
                interpret(input.trim(), operation, executionService, xsl);
            }     
            else if (input.startsWith("load")) {
                String[] commandArr = input.split("\\s+");
                if (commandArr.length  == 2) {
                    String textCorpus;
                    try {
                        textCorpus = getContents(commandArr[1]);
                        interpret(textCorpus, operation, executionService, xsl);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                else {
                    System.out.println("Invalid operation.");
                }
            }
        } while (!"quit".equals(input));     
        
        executionService.commit();
        
        return "";
    }

    private void interpret(String input, String operation, OwlExecutionService executionService, String xsl) {
        DefaultTokenSerializer serializer = new DefaultTokenSerializer();
        TextCorpus textCorpus = processor.parseCorpus(input);
        Document xmlDocument = serializer.serialize(textCorpus);
        
        if ("exec".equals(operation)) {
            System.out.println(executionService.execute(serializer.transform(xmlDocument, xsl)));
        }
        else if ("xml".equals(operation)) {
            System.out.println(serializer.transform(xmlDocument));
        }
        else if ("func".equals(operation)) {
            System.out.println(serializer.transform(xmlDocument, xsl));
        }
    }
    
    private static String getContents(String file) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(file));        
        return new String(encoded, Charset.defaultCharset());
    }

    @Override
    public String execute(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "create" : 
                    if (args.length == 2) {
                        return createKnowledgeBase(args[1]); 
                    }
                case "load" : 
                    if (args.length == 2) {
                        return loadKnowledgeBase(args[1]);
                    }
            }
        }
        
        return "Invalid operation.";
    }
    
}
