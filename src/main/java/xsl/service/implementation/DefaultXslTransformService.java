/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xsl.service.implementation;

import xsl.model.Expression;
import xsl.service.FunctionParser;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import xsl.service.XslTransformService;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public class DefaultXslTransformService implements XslTransformService {
    private final FunctionParser functionParser;
    
    public DefaultXslTransformService(FunctionParser functionParser) {
        this.functionParser = functionParser;
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
            Logger.getLogger(DefaultXslTransformService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DefaultXslTransformService.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DefaultXslTransformService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DefaultXslTransformService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw.toString();
    }

    @Override
    public List<Expression> transformToExpressions(Document doc, String xslt) {
        return functionParser.parse(transform(doc, xslt));
    }

}
