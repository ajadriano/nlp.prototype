/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.functions;

import aj.owl.model.Function;

/**
 *
 * @author ajadriano
 */
public enum OWLFunction {
    EquivalentClasses (EquivalentClassesFunction.getInstance()),
    ObjectIntersectionOf (ObjectIntersectionOfFunction.getInstance()),
    ObjectSomeValuesFrom (ObjectSomeValuesFromFunction.getInstance()),
    SubClassOf (SubClassOfFunction.getInstance());
    
    private final Function function;
    
    private OWLFunction(Function function){
        this.function = function;
    }

    public Function getFunction(){return function;}
}
