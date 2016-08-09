/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.owl.service.implementation.statements;

import aj.owl.model.OWLExpression;
import aj.owl.service.implementation.queries.DirectSubClassOfQuery;

/**
 *
 * @author ajadriano
 */
public enum ClassExpressions {
    ObjectIntersectionOf (ObjectIntersectionOfFunction.getInstance()),
    ObjectUnionOf (ObjectUnionOfFunction.getInstance()),
    ObjectComplementOf (ObjectComplementOfFunction.getInstance()),
    ObjectOneOf (ObjectOneOfFunction.getInstance()),
    
    ObjectSomeValuesFrom (ObjectSomeValuesFromFunction.getInstance()),
    ObjectAllValuesFrom (ObjectSomeValuesFromFunction.getInstance()),
    ObjectHasValue (ObjectHasValueFunction.getInstance()),
    ObjectHasSelf (ObjectHasSelfFunction.getInstance()),
    
    ObjectMinCardinality (ObjectMinCardinalityFunction.getInstance()), 
    ObjectMaxCardinality (ObjectMaxCardinalityFunction.getInstance()), 
    ObjectExactCardinality (ObjectExactCardinalityFunction.getInstance()), 
    
    EquivalentClasses (EquivalentClassesFunction.getInstance()),
    SubClassOf (SubClassOfFunction.getInstance()),
    DisjointClasses (DisjointClassesFunction.getInstance()),
    DisjointUnion (DisjointUnionFunction.getInstance()),
    
    DataSomeValuesFrom (DataSomeValuesFromFunction.getInstance()),
    DataAllValuesFrom (DataSomeValuesFromFunction.getInstance()),
    DataHasValue (DataHasValueFunction.getInstance()),
    
    DataMinCardinality (DataMinCardinalityFunction.getInstance()), 
    DataMaxCardinality (DataMaxCardinalityFunction.getInstance()), 
    DataExactCardinality (DataMaxCardinalityFunction.getInstance()), 
    
    SubObjectPropertyOf (SubObjectPropertyOfFunction.getInstance()), 
    EquivalentObjectProperties (EquivalentObjectPropertiesFunction.getInstance()), 
    DisjointObjectProperties (DisjointObjectPropertiesFunction.getInstance()),
    ObjectPropertyDomain (ObjectPropertyDomainFunction.getInstance()), 
    ObjectPropertyRange (ObjectPropertyRangeFunction.getInstance()), 
    FunctionalObjectProperty (FunctionalObjectPropertyFunction.getInstance()), 
    InverseFunctionalObjectProperty (InverseFunctionalObjectPropertyFunction.getInstance()), 
    
    IsDirectSubClassOf (DirectSubClassOfQuery.getInstance());
    
    private final OWLExpression function;
    
    private ClassExpressions(OWLExpression function){
        this.function = function;
    }

    public OWLExpression getFunction(){return function;}
}
