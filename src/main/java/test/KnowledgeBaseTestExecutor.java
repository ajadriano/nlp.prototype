/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import common.Interpreter;
import xsl.service.implementation.DefaultXslTransformService;
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
import kb.service.KnowledgeBase;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ajadriano
 */
public class KnowledgeBaseTestExecutor { 
    private final KnowledgeBase knowledgeBase;
    
    public KnowledgeBaseTestExecutor(KnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }
    
    public void execute(String testFile) {
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
                    execute(externalFile);
                }   
                else {
                    String description = test.getAttribute("description");
                    
                    NodeList nodeInputs = (NodeList)xPath.evaluate("input", test, XPathConstants.NODESET);   
                    NodeList nodeOutputs = (NodeList)xPath.evaluate("output", test, XPathConstants.NODESET);  
                    for (int j = 0; j < nodeInputs.getLength() && j < nodeOutputs.getLength(); ++j) {
                        Element input = (Element) nodeInputs.item(j); 
                        Element output = (Element) nodeOutputs.item(j);
                        
                        String statement = input.getTextContent();
                        System.out.println("debugkb>" + statement);
                        String response = knowledgeBase.tell(statement);
                        String expectedResponse = output.getTextContent();
                        System.out.println(response);
                        if (!expectedResponse.trim().equals(response.trim())) {
                            System.out.println("Test failed, expected response - " + expectedResponse);
                            break;
                        }
                    }  
                    
                    System.out.println("Test [" + description + "] complete");
                    knowledgeBase.clear();
                }                
            }
            
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | DOMException ex) {
            Logger.getLogger(DefaultXslTransformService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
