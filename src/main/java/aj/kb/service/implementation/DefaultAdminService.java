/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.kb.service.implementation;

import aj.common.DefaultServices;
import aj.kb.service.AdminService;
import aj.kb.service.KnowledgeBaseException;
import aj.kb.service.KnowledgeBaseService;
import aj.nlp.model.TextCorpus;
import aj.nlp.service.LanguageProcessor;
import aj.nlp.service.implementation.DefaultLanguageProcessor;
import aj.nlp.service.implementation.DefaultTokenSerializer;
import aj.owl.model.Expression;
import aj.owl.service.implementation.DefaultFunctionParser;
import aj.test.TestInterpreter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ajadriano
 */
public class DefaultAdminService implements AdminService {
    
    private LanguageProcessor processor;
    private DefaultTokenSerializer serializer;
    private DefaultFunctionParser functionParser;
    private DefaultServices services;
    
    @Override
    public void initialize() {
        processor = new DefaultLanguageProcessor();
        processor.initialize();
        
        serializer = new DefaultTokenSerializer();
        functionParser = new DefaultFunctionParser();
        
        services = new DefaultServices(processor, serializer, functionParser);
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
        else if ("kb".equals(mode)){
            return loadKnowledgeBase(new DebugKnowledgeBaseService(services, file));
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
        TestInterpreter interpreter = new TestInterpreter(services, file);
        
        try {
            interpreter.initialize();
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
                if (commandArr.length  >= 2) {
                    try {
                        if (commandArr[1].endsWith(".xslt")) {
                            interpreter.replaceXsl(commandArr[1]);
                        }
                        else {
                           System.out.println(interpreter.interpretFile(commandArr[1]));
                        }     
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                else {
                    System.out.println("Invalid operation.");
                }
            }
            else if (input.startsWith("test")) {
                interpreter.test();
            }
            else if (!"quit".equals(input)) {  
                System.out.println(interpreter.interpret(input));
            }
        } while (!"quit".equals(input));
        
        return "";
    }
    
    public String loadKnowledgeBase(KnowledgeBaseService kb) {        
        try {
            kb.initialize();
        } catch (KnowledgeBaseException | IOException ex) {
            Logger.getLogger(DefaultAdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        do {
            System.out.print(kb.getName() + ">");
            
            try { 
                input = br.readLine();
            } catch (IOException ex) {
                return ex.getMessage();
            }
            
            if (input.trim().endsWith(".") || input.trim().endsWith("?")) {
                System.out.println(kb.tell(input));
            }     
            else if (input.startsWith("load")) {
                String[] commandArr = input.split("\\s+");
                if (commandArr.length  == 2) {
                    try {
                        System.out.println(kb.loadFile(commandArr[1]));
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                else {
                    System.out.println("Invalid operation.");
                }
            }
        } while (!"quit".equals(input));     
        
        kb.save();
        
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
                        return loadKnowledgeBase(new DefaultKnowledgeBaseService(services, args[1], "default.xslt"));
                    }
                    else if (args.length == 3) {
                        return loadKnowledgeBase(new DefaultKnowledgeBaseService(services, args[1], args[1]));
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
