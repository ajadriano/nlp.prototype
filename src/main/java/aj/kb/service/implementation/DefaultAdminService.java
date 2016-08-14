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
import aj.owl.model.Expression;
import aj.owl.service.implementation.DefaultFunctionParser;
import aj.owl.service.implementation.OwlExecutionService;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.w3c.dom.Document;

/**
 *
 * @author ajadriano
 */
public class DefaultAdminService implements AdminService {
    
    private LanguageProcessor processor;
    private DefaultTokenSerializer serializer;
    private DefaultFunctionParser functionParser;
    
    @Override
    public void initialize() {
        processor = new DefaultLanguageProcessor();
        processor.initialize();
        
        serializer = new DefaultTokenSerializer();
        functionParser = new DefaultFunctionParser();
    }
    
    public String createKnowledgeBase(String name) {
        File file = new File("../domains/" + name);
        if (!file.exists()) {
            file.mkdir();
            return "Domain created.";
        }
        else {
            return "Knowledge base exists.";
        }
    }
    
    public String debug(String mode, String file) {
        if ("nlp".equals(mode)){
            return debugNlp();
        }
        else if ("xsl".equals(mode)){
            return debugXsl(file);
        }
        
        return "";
    }
    
    public String debugNlp() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        do {
            System.out.print("debugnlp>");
            
            try { 
                input = br.readLine();
            } catch (IOException ex) {
                return ex.getMessage();
            }
            
            if (input.startsWith("load")) {
                String[] commandArr = input.split("\\s+");
                if (commandArr.length  == 2) {
                    String contents;
                    try {
                        contents = getContents(commandArr[1]);
                        TextCorpus textCorpus = processor.parseCorpus(contents);
                        Document xmlDocument = serializer.serialize(textCorpus);
                        System.out.println(serializer.transform(xmlDocument));
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                else {
                    System.out.println("Invalid operation.");
                }
            }
            if (!"quit".equals(input)) {  
                TextCorpus textCorpus = processor.parseCorpus(input);
                Document xmlDocument = serializer.serialize(textCorpus);
                System.out.println(serializer.transform(xmlDocument));
            }
        } while (!"quit".equals(input));
        
        return "";
    }
    
    public String debugXsl(String file) {
        String xsl;
        try {
            xsl = getContents(file);
        } catch (IOException ex) {
            return ex.getMessage();
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        do {
            System.out.print("debugxsl>");
            
            try { 
                input = br.readLine();
            } catch (IOException ex) {
                return ex.getMessage();
            }
            
            if (input.startsWith("load")) {
                String[] commandArr = input.split("\\s+");
                if (commandArr.length  == 2) {
                    String contents;
                    try {
                        if (commandArr[1].endsWith(".xslt")) {
                            try {
                                xsl = getContents(commandArr[1]);
                            } catch (IOException ex) {
                                return ex.getMessage();
                            }
                        }
                        else {
                           contents = getContents(commandArr[1]);
                           TextCorpus textCorpus = processor.parseCorpus(contents);
                           Document xmlDocument = serializer.serialize(textCorpus);
                           List<Expression> expressions = functionParser.parse(serializer.transform(xmlDocument, xsl));
                           
                           expressions.stream().forEach((expression) -> {
                               System.out.println(expression.toString());
                               System.out.println();
                            }); 
                        }     
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                else {
                    System.out.println("Invalid operation.");
                }
            }
            else if (!"quit".equals(input)) {  
                TextCorpus textCorpus = processor.parseCorpus(input);
                Document xmlDocument = serializer.serialize(textCorpus);
                List<Expression> expressions = functionParser.parse(serializer.transform(xmlDocument, xsl));
                           
                expressions.stream().forEach((expression) -> {
                    System.out.println(expression.toString());
                    System.out.println();
                }); 
            }
        } while (!"quit".equals(input));
        
        return "";
    }
    
    public String loadKnowledgeBase(String name) {
        File file = new File("../domains/" + name);
        if (!file.exists()) {
            return "Domain does not exist.";
        }
        
        OwlExecutionService executionService = new OwlExecutionService();
        executionService.initialize(name);
        
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
            
            if (input.trim().endsWith(".") || input.trim().endsWith("?")) {
                TextCorpus textCorpus = processor.parseCorpus(input);
                Document xmlDocument = serializer.serialize(textCorpus);
                System.out.println(executionService.execute(serializer.transform(xmlDocument, xsl)));
            }     
            else if (input.startsWith("load")) {
                String[] commandArr = input.split("\\s+");
                if (commandArr.length  == 2) {
                    String contents;
                    try {
                        contents = getContents(commandArr[1]);
                        TextCorpus textCorpus = processor.parseCorpus(contents);
                        Document xmlDocument = serializer.serialize(textCorpus);
                        System.out.println(executionService.execute(serializer.transform(xmlDocument, xsl)));
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
                case "debug" : 
                    if (args.length == 2) {
                        return debug(args[1], "default.xslt");
                    }
                    else if (args.length == 3) {
                        return debug(args[1], args[2]);
                    }
            }
        }
        
        return "Invalid operation.";
    }
    
}
