/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.common;

import java.util.List;

/**
 *
 * @author ajadriano
 */
public interface Interpreter {
    List<String> interpretByLine(String input);
}
