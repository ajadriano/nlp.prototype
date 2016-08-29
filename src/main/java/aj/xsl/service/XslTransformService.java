/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.xsl.service;

import aj.xsl.model.Expression;
import java.util.List;
import org.w3c.dom.Document;

/**
 *
 * @author ajadriano
 */
public interface XslTransformService {
    String transform(Document doc);
    String transform(Document doc, String xslt); 
    List<Expression> transformToExpressions(Document doc, String xslt); 
}
