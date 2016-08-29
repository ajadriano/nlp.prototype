/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.test;

import aj.common.Interpreter;
import aj.xsl.service.implementation.DefaultXslTransformService;
import java.io.FileInputStream;
import java.io.IOException;
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
public class TestExecutor { 
    private final Interpreter interpreter;
    private final String testFile;
    
    public TestExecutor(Interpreter interpreter, String testFile) {
        this.interpreter = interpreter;
        this.testFile = testFile;
    }
    
    public void execute() {
        StringBuilder sb = new StringBuilder();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new FileInputStream(testFile));
            XPath xPath = XPathFactory.newInstance().newXPath();
            
            System.out.println("Collating all statements...");
            NodeList testNodes = (NodeList)xPath.evaluate("//test", doc.getDocumentElement(), XPathConstants.NODESET);
            for (int i = 0; i < testNodes.getLength(); ++i) {
                Element test = (Element) testNodes.item(i);  
                if (test.hasAttribute("src")) {
                    String externalFile = test.getAttribute("src");
                    loadTest(docBuilder, xPath, externalFile, sb);
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
            
            System.out.println("Executing statements...");
            List<String> expressions = interpreter.interpretByLine(sb.toString());
            int currentExpression = 0;
            
            System.out.println("Checking results...");
            for (int i = 0; i < testNodes.getLength(); ++i) {
                Element test = (Element) testNodes.item(i);  
                if (test.hasAttribute("src")) {
                    String externalFile = test.getAttribute("src");
                    currentExpression = verifyResult(docBuilder, xPath, externalFile, expressions, currentExpression);
                }
                else {
                    NodeList nodeInputs = (NodeList)xPath.evaluate("input", test, XPathConstants.NODESET);
                    Element input = (Element) nodeInputs.item(0); 
                    NodeList nodeOutputs = (NodeList)xPath.evaluate("output", test, XPathConstants.NODESET);             
                    for (int j = 0; j < nodeOutputs.getLength(); ++j) {
                        Element output = (Element) nodeOutputs.item(j);   
                        if ((output.getTextContent().trim().equals(expressions.get(currentExpression).trim())) == false) {
                            System.out.println("Error parsing statement - '" + input.getTextContent().trim() + "'");
                            System.out.println("Expected - " + output.getTextContent());
                            System.out.println("Actual - " + expressions.get(currentExpression));
                        }
                        currentExpression++;
                    }
                }
            }
            
            System.out.println("Test complete");
            
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | DOMException ex) {
            Logger.getLogger(DefaultXslTransformService.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private int verifyResult(DocumentBuilder docBuilder,  XPath xPath, String file, List<String> expressions, int currentExpression) throws SAXException, IOException, XPathExpressionException {
        Document doc = docBuilder.parse(new FileInputStream(file));
        
        NodeList testNodes = (NodeList)xPath.evaluate("//test", doc.getDocumentElement(), XPathConstants.NODESET);
        for (int i = 0; i < testNodes.getLength(); ++i) {
            Element test = (Element) testNodes.item(i);  
            NodeList nodeInputs = (NodeList)xPath.evaluate("input", test, XPathConstants.NODESET);
            Element input = (Element) nodeInputs.item(0); 
            NodeList nodeOutputs = (NodeList)xPath.evaluate("output", test, XPathConstants.NODESET);

            for (int j = 0; j < nodeOutputs.getLength(); ++j) {
                Element output = (Element) nodeOutputs.item(j); 
                if ((output.getTextContent().trim().equals(expressions.get(currentExpression).trim())) == false) {
                    System.out.println("Error parsing statement - '" + input.getTextContent().trim() + "'");
                    System.out.println("Expected - " + output.getTextContent());
                    System.out.println("Actual - " + expressions.get(currentExpression));
                }
                currentExpression++;
            }                
        }
        
        return currentExpression;
    }
}
