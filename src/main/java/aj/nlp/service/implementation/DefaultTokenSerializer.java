/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.service.implementation;

import aj.nlp.model.TextCorpus;
import aj.nlp.model.Token;
import aj.nlp.service.TokenSerializer;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.TransformerFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ajadriano
 */
public class DefaultTokenSerializer implements TokenSerializer {

    @Override
    public Document serialize(TextCorpus textCorpus) {
        StringWriter sw = new StringWriter();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();        
            Element element = textCorpus.writeXML(doc);
            if (element != null) {
                doc.appendChild(element);
            }
                
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DefaultTokenSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }
    
    @Override
    public Document serialize(Token token) {
        StringWriter sw = new StringWriter();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();        
            Element element = token.writeXML(doc);
            if (element != null) {
                doc.appendChild(element);
            }
                
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DefaultTokenSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }
    
    @Override
    public String transform(Document doc) {
        StringWriter sw = new StringWriter();
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(sw);
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DefaultTokenSerializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DefaultTokenSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw.toString();
    }
    
    @Override
    public String transform(Document doc, String xslt) {
        StringWriter sw = new StringWriter();
        try {
            TransformerFactory transformerFactory = new TransformerFactoryImpl();            
            Source xsltSource = new StreamSource(new StringReader(xslt));
            Transformer transformer = transformerFactory.newTransformer(xsltSource);
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(sw);
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DefaultTokenSerializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DefaultTokenSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw.toString();
    }

}
