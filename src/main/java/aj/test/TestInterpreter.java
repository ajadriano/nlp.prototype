/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.test;

import aj.common.Interpreter;
import aj.common.Services;
import aj.nlp.model.TextCorpus;
import aj.nlp.service.LanguageProcessor;
import aj.xsl.model.Expression;
import aj.xsl.service.FunctionParser;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import aj.xsl.service.XslTransformService;

/**
 *
 * @author ajadriano
 */
public class TestInterpreter implements Interpreter {
    private final FunctionParser functionParser;
    private final LanguageProcessor processor;
    private final XslTransformService serializer;
    
    private String xslFile;
    private String xsl;
    
    public TestInterpreter(Services services, String xslFile) {
        this.xslFile = xslFile;
        this.processor = services.getLanguageProcessor();
        this.serializer = services.getXslTransformService();
        this.functionParser = services.getFunctionParser();
    }
    
    public void initialize() throws IOException {
        xsl = getContents(xslFile);
    }
    
    public void replaceXsl(String xslFile) throws IOException {
        this.xslFile = xslFile;
        xsl = getContents(this.xslFile);
    }
    
    public String interpretFile(String file) throws IOException {
        return interpret(getContents(file));
    }
    
    public String interpret(String input) {
        StringBuilder sb = new StringBuilder();
        List<String> expressions = interpretByLine(input);
                           
        expressions.stream().forEach((expression) -> {
            sb.append(expression);
            sb.append("\n");
         }); 
        
        return sb.toString();
    }
    
    @Override
    public List<String> interpretByLine(String input) {
        List<String> output = new ArrayList();
        Document xmlDocument = processor.parseCorpus(input);
        List<Expression> expressions = functionParser.parse(serializer.transform(xmlDocument, xsl));
                           
        expressions.stream().forEach((expression) -> {
            output.add(expression.toString());
         }); 
        
        return output;
    }
    
    public void test() {
        String fileNameWithOutExt = FilenameUtils.removeExtension(xslFile);
        TestExecutor testExecutor = new TestExecutor(this, fileNameWithOutExt + ".test.xml");
        testExecutor.execute();
    }
    
    protected static String getContents(String file) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(file));        
        return new String(encoded, Charset.defaultCharset());
    }
}
