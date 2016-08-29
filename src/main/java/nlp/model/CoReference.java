/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.model;

import org.apache.commons.lang3.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ajadriano
 */
public class CoReference {
    private final int id;
    private final String value;
    
    public CoReference(int id, String value) {
        this.id = id;
        this.value = value;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    
    public Element writeXML(Document document) {
        Element element = document.createElement("COREF");
        element.setTextContent(value);
        element.setAttribute("id", Integer.toString(id));
        return element;
    }
}
