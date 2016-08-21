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
                if (commandArr.length  >= 2) {
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
                               String expressionString = expression.toString();
                               System.out.println(expressionString);
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
            else if (input.startsWith("test")) {
                String[] commandArr = input.split("\\s+");
                if (commandArr.length  > 1) {
                    testXsl(commandArr[1]);
                }
                else {
                    testXsl("test.xml");
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
    
    private void testXsl(String file) {
        StringBuilder sb = new StringBuilder();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(new FileInputStream(file));
            XPath xPath = XPathFactory.newInstance().newXPath();
            
            NodeList testNodes = (NodeList)xPath.evaluate("//test", doc.getDocumentElement(), XPathConstants.NODESET);
            for (int i = 0; i < testNodes.getLength(); ++i) {
                Element test = (Element) testNodes.item(i);  
                if (test.hasAttribute("src")) {
                    loadTest(docBuilder, xPath, test.getAttribute("src"), sb);
                }   
                else {
                    NodeList nodeInputs = (NodeList)xPath.evaluate("input", test, XPathConstants.NODESET);             
                    for (int j = 0; j < nodeInputs.getLength(); ++j) {
                        Element input = (Element) nodeInputs.item(j); 
                        sb.append(input.getTextContent());
                        sb.append(" ");
                    }
                }                
            }
            
            TextCorpus textCorpus = processor.parseCorpus(sb.toString());
            Document xmlDocument = serializer.serialize(textCorpus);
            List<Expression> expressions = functionParser.parse(serializer.transform(xmlDocument, getContents("default.xslt")));
            int currentExpression = 0;
            
            for (int i = 0; i < testNodes.getLength(); ++i) {
                Element test = (Element) testNodes.item(i);  
                if (test.hasAttribute("src")) {
                    currentExpression = verifyResult(docBuilder, xPath, test.getAttribute("src"), expressions, currentExpression);
                }
                else {
                    NodeList nodeInputs = (NodeList)xPath.evaluate("input", test, XPathConstants.NODESET);
                    Element input = (Element) nodeInputs.item(0); 
                    NodeList nodeOutputs = (NodeList)xPath.evaluate("output", test, XPathConstants.NODESET);             
                    for (int j = 0; j < nodeOutputs.getLength(); ++j) {
                        Element output = (Element) nodeOutputs.item(j);   
                        if ((output.getTextContent().trim().equals(expressions.get(currentExpression).toString().trim())) == false) {
                            System.out.println("Error parsing statement - '" + input.getTextContent().trim() + "'");
                            System.out.println("Expected - " + output.getTextContent());
                            System.out.println("Actual - " + expressions.get(currentExpression).toString());
                        }
                        currentExpression++;
                    }
                }
            }
            
            System.out.println("Test complete");
            
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | DOMException ex) {
            Logger.getLogger(DefaultTokenSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTest(DocumentBuilder docBuilder, XPath xPath, String file, StringBuilder sb) throws SAXException, IOException, XPathExpressionException {
        Document doc = docBuilder.parse(new FileInputStream(file));
        NodeList inputNodes = (NodeList)xPath.evaluate("//input", doc.getDocumentElement(), XPathConstants.NODESET);
        for (int i = 0; i < inputNodes.getLength(); ++i) {
            Element input = (Element) inputNodes.item(i); 
            sb.append(input.getTextContent());
            sb.append(" ");
        }
    }
    
    private int verifyResult(DocumentBuilder docBuilder,  XPath xPath, String file, List<Expression> expressions, int currentExpression) throws SAXException, IOException, XPathExpressionException {
        Document doc = docBuilder.parse(new FileInputStream(file));
        
        NodeList testNodes = (NodeList)xPath.evaluate("//test", doc.getDocumentElement(), XPathConstants.NODESET);
        for (int i = 0; i < testNodes.getLength(); ++i) {
            Element test = (Element) testNodes.item(i);  
            NodeList nodeInputs = (NodeList)xPath.evaluate("input", test, XPathConstants.NODESET);
            Element input = (Element) nodeInputs.item(0); 
            NodeList nodeOutputs = (NodeList)xPath.evaluate("output", test, XPathConstants.NODESET);

            for (int j = 0; j < nodeOutputs.getLength(); ++j) {
                Element output = (Element) nodeOutputs.item(j); 
                if ((output.getTextContent().trim().equals(expressions.get(currentExpression).toString().trim())) == false) {
                    System.out.println("Error parsing statement - '" + input.getTextContent().trim() + "'");
                    System.out.println("Expected - " + output.getTextContent());
                    System.out.println("Actual - " + expressions.get(currentExpression).toString());
                }
                currentExpression++;
            }                
        }
        
        return currentExpression;
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
