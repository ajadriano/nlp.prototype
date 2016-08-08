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
public enum OWLExpressions {
    EquivalentClasses (EquivalentClassesFunction.getInstance()),
    SubClassOf (SubClassOfFunction.getInstance()),
    DisjointClasses (DisjointClassesFunction.getInstance()),
    DisjointUnion (DisjointUnionFunction.getInstance()),
    ObjectComplementOf (ObjectComplementOfFunction.getInstance()),
    ObjectOneOf (ObjectOneOfFunction.getInstance()),
    ObjectIntersectionOf (ObjectIntersectionOfFunction.getInstance()),
    ObjectSomeValuesFrom (ObjectSomeValuesFromFunction.getInstance()),
    ObjectAllValuesFrom (ObjectSomeValuesFromFunction.getInstance()),
    ObjectHasValue (ObjectHasValueFunction.getInstance()),
    ObjectHasSelf (ObjectHasSelfFunction.getInstance()),
    ObjectMinCardinality (ObjectMinCardinalityFunction.getInstance()),    
    
    IsDirectSubClassOf (DirectSubClassOfQuery.getInstance());
    
    private final OWLExpression function;
    
    private OWLExpressions(OWLExpression function){
        this.function = function;
    }

    public OWLExpression getFunction(){return function;}
}
