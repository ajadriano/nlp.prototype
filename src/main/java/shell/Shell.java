/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shell;

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
        AdminShell adminShell = new AdminShell();
        adminShell.initialize();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        
        do {
            System.out.print("admin>");
            input = br.readLine();
            String[] commandArr = input.split("\\s+");
            
            if (!"quit".equals(input)) {
                System.out.println(adminShell.execute(commandArr));
            }
        } while (!"quit".equals(input));  
    }
}
