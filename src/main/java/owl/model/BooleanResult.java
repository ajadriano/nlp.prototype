/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.model;

/**
 *
 * @author ajadriano
 */
public class BooleanResult<T> extends Result<T> {
    
    public BooleanResult(T result) {
        super(result);
    }
    
    public String getFormattedResult() {
        
        if (getResult() == null) {
            return "Unknown";
        }
        
        if (getResult() instanceof Boolean) {
            if (getResult() == Boolean.TRUE) {
                return "Yes";
            }
            if (getResult() == Boolean.FALSE) {
                return "No";
            }
        }
        
        return super.getResult().toString();
    }
}
