/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.service.implementation.statements;

import owl.model.OWLExpression;
import owl.service.implementation.queries.DirectSubClassOfQuery;
import owl.service.implementation.queries.GetInstancesQuery;
import owl.service.implementation.queries.GetObjectPropertyValuesQuery;
import owl.service.implementation.queries.InstanceOfQuery;

/**
 *
 * @author ajadriano
 */
public enum ClassExpressions {
    Class (ClassFunction.getInstance()),
    ObjectProperty (ObjectPropertyFunction.getInstance()),
    DataProperty (DataPropertyFunction.getInstance()),
    ObjectPropertyWithInverse (ObjectPropertyWithInverseFunction.getInstance()),
    ObjectInverseOf (ObjectInverseOfFunction.getInstance()),
    NamedIndividual (NamedIndividualFunction.getInstance()),
    
    Thing (ThingFunction.getInstance()),
    AnonymousIndividual (AnonymousIndividualFunction.getInstance()),
    
    ObjectIntersectionOf (ObjectIntersectionOfFunction.getInstance()),
    ObjectUnionOf (ObjectUnionOfFunction.getInstance()),
    ObjectComplementOf (ObjectComplementOfFunction.getInstance()),
    ObjectOneOf (ObjectOneOfFunction.getInstance()),
    
    ObjectSomeValuesFrom (ObjectSomeValuesFromFunction.getInstance()),
    ObjectAllValuesFrom (ObjectAllValuesFromFunction.getInstance()),
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
    DataHasBoolValue (DataHasBoolValueFunction.getInstance()),
    
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
    ReflexiveObjectProperty (ReflexiveObjectPropertyFunction.getInstance()),
    IrreflexiveObjectProperty (IrreflexiveObjectPropertyFunction.getInstance()),
    SymmetricObjectProperty (SymmetricObjectPropertyFunction.getInstance()),
    AsymmetricObjectProperty (AsymmetricObjectPropertyFunction.getInstance()),
    TransitiveObjectProperty (TransitiveObjectPropertyFunction.getInstance()),
    
    SubDataPropertyOfProperty (SubDataPropertyOfFunction.getInstance()),
    EquivalentDataProperties (EquivalentDataPropertiesFunction.getInstance()),
    DisjointDataProperties (DisjointDataPropertiesFunction.getInstance()),
    DataPropertyDomain (DataPropertyDomainFunction.getInstance()),
    DataPropertyRange (DataPropertyDomainFunction.getInstance()),
    FunctionalDataProperty (FunctionalDataPropertyFunction.getInstance()),
    
    SameIndividual (SameIndividualFunction.getInstance()),
    DifferentIndividuals (DifferentIndividualsFunction.getInstance()),
    ClassAssertion (ClassAssertionFunction.getInstance()),
    ObjectPropertyAssertion (ObjectPropertyAssertionFunction.getInstance()),
    NegativeObjectPropertyAssertion (NegativeObjectPropertyAssertionFunction.getInstance()),
    DataBoolPropertyAssertion (DataBoolPropertyAssertionFunction.getInstance()),
    NegativeDataPropertyAssertion (DataBoolPropertyAssertionFunction.getInstance()),
    
    Unknown (UnknownFunction.getInstance()),
    
    IsDirectSubClassOf (DirectSubClassOfQuery.getInstance()),
    GetObjectPropertyValues (GetObjectPropertyValuesQuery.getInstance()),
    GetInstances (GetInstancesQuery.getInstance()),
    IsInstanceOf (InstanceOfQuery.getInstance());
    
    private final OWLExpression function;
    
    private ClassExpressions(OWLExpression function){
        this.function = function;
    }

    public OWLExpression getFunction(){return function;}
}
