/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shell;

import common.DefaultServices;
import common.Services;
import kb.service.KnowledgeBaseException;
import nlp.model.TextCorpus;
import test.TestInterpreter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import kb.service.KnowledgeBase;
import kb.service.KnowledgeBaseManager;
import kb.service.implementation.DefaultKnowledgeBaseManager;
import test.KnowledgeBaseTestExecutor;

/**
 *
 * @author ajadriano
 */
public class AdminShell {
    private Services services;
    private KnowledgeBaseManager knowledgeBaseManager;

    
    public void initialize() {
        services = new DefaultServices();
        services.initialize();
        knowledgeBaseManager = new DefaultKnowledgeBaseManager(services, "../domains/");
    }
    
    public String createKnowledgeBase(String name) {
        try {
            knowledgeBaseManager.create(name);
            return "Knowledge base " + name + " created";
        } catch (KnowledgeBaseException ex) {
            return ex.getMessage();
        }
    }
    
    public String importKnowledgeBase(String name, String file) {
        try {
            KnowledgeBase kb = knowledgeBaseManager.get(name);
            kb.initialize();
            kb.loadFile(file);
            kb.save();
            return "Successfully imported";
        } catch (IOException | KnowledgeBaseException ex) {
            return ex.getMessage();
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
            return loadKnowledgeBase(knowledgeBaseManager.getDebugKb(file), true);
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
                        Document xmlDocument = services.getLanguageProcessor().parseCorpus(contents);
                        System.out.println(services.getXslTransformService().transform(xmlDocument));
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                else {
                    System.out.println("Invalid operation.");
                }
            }
            if (!"quit".equals(input)) {  
                Document xmlDocument = services.getLanguageProcessor().parseCorpus(input);
                System.out.println(services.getXslTransformService().transform(xmlDocument));
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
    
    public String loadKnowledgeBase(KnowledgeBase kb, boolean isDebug) {        
        try {
            kb.initialize();
        } catch (KnowledgeBaseException | IOException ex) {
            Logger.getLogger(AdminShell.class.getName()).log(Level.SEVERE, null, ex);
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
            else if (isDebug && input.startsWith("test")) {
                String[] commandArr = input.split("\\s+");
                if (commandArr.length  == 2) {
                    KnowledgeBaseTestExecutor executor = new KnowledgeBaseTestExecutor(kb);
                    executor.execute(commandArr[1]);
                }
                else {
                    System.out.println("Invalid operation.");
                }
            }
            else if (isDebug && input.equals("clear")) {
               kb.clear();
            }
        } while (!"quit".equals(input));     
        
        kb.save();
        
        return "";
    }
    
    private static String getContents(String file) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(file));        
        return new String(encoded, Charset.defaultCharset());
    }
    
    public String execute(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {         
                case "create" : 
                    if (args.length == 2) {
                        return createKnowledgeBase(args[1]); 
                    }
                case "load" : 
                    if (args.length == 2) {
                        return loadKnowledgeBase(knowledgeBaseManager.get(args[1]), false);
                    }
                    else if (args.length == 3) {
                        return loadKnowledgeBase(knowledgeBaseManager.get(args[1], args[2]), false);
                    }
                case "import" : 
                    if (args.length == 3) {
                        return importKnowledgeBase(args[1], args[2]);
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
