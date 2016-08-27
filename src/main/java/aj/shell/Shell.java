/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.shell;

import aj.kb.service.AdminService;
import aj.kb.service.implementation.DefaultAdminService;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ajadriano
 */
public class Shell {
    public static void main(String args[]) throws IOException {
        //RedwoodConfiguration.empty().capture(System.out).apply();
        AdminService adminService = new DefaultAdminService();
        adminService.initialize();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        
        do {
            System.out.print("admin>");
            input = br.readLine();
            String[] commandArr = input.split("\\s+");
            
            if (!"quit".equals(input)) {
                System.out.println(adminService.execute(commandArr));
            }
        } while (!"quit".equals(input));  
    }
}
