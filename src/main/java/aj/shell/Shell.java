/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.shell;

import aj.nlp.model.TextCorpus;
import aj.nlp.service.LanguageProcessor;
import aj.nlp.service.implementation.DefaultLanguageProcessor;
import aj.nlp.service.implementation.DefaultTokenSerializer;
import aj.owl.service.implementation.OwlExecutionService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.w3c.dom.Document;

/**
 *
 * @author ajadriano
 */
public class Shell {
    public static void main(String args[]) throws IOException {
        OwlExecutionService executionService = new OwlExecutionService();
        LanguageProcessor processor = new DefaultLanguageProcessor();
        
        processor.initialize();
        executionService.initialize();
        
        String operation = "exec";
        
        byte[] encoded = Files.readAllBytes(Paths.get("default.xslt"));
        String xsl = new String(encoded, Charset.defaultCharset());
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        do {
            System.out.print("admin>");
            input = br.readLine();
            
            if ("debugxml".equals(input)) {
                operation = "xml";
                System.out.println("Debugging nlp xml.");
            }
            else if ("debugfunc".equals(input)) {
                operation = "func";
                System.out.println("Debugging functional transformation.");
            }
            else if ("debugexec".equals(input)) {
                operation = "exec";
                System.out.println("Executing statements and queries.");
            }
            else if (!"quit".equals(input)) {
                DefaultTokenSerializer serializer = new DefaultTokenSerializer();
                TextCorpus textCorpus = processor.parseCorpus(input);
                Document xmlDocument = serializer.serialize(textCorpus);
                
                if ("exec".equals(operation)) {
                    System.out.println(executionService.execute(serializer.transform(xmlDocument, xsl)));
                }
                else if ("xml".equals(operation)) {
                    System.out.println(serializer.transform(xmlDocument));
                }
                else if ("func".equals(operation)) {
                    System.out.println(serializer.transform(xmlDocument, xsl));
                }
            }      
        } while (!"quit".equals(input));     
        
        executionService.commit();
    }
}
