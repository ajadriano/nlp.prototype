/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public class Result<T extends Object> {
    private final List<String> warnings;
    private final T result;
    
    public Result(T result) {
        this.result = result;
        warnings = new ArrayList();
    }

    /**
     * @return the warnings
     */
    public List<String> getWarnings() {
        return warnings;
    }

    /**
     * @return the result
     */
    public T getResult() {
        return result;
    }
}
