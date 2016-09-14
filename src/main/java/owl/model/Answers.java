/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.model;

import java.util.ArrayList;
import java.util.List;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

/**
 *
 * @author ajadriano
 */
public class Answers {
    private final List<OWLNamedIndividual> individuals;
    private final List<OWLClass> classes;
    
    public Answers() {
        individuals = new ArrayList();
        classes = new ArrayList();
    }

    /**
     * @return the individuals
     */
    public List<OWLNamedIndividual> getIndividuals() {
        return individuals;
    }

    /**
     * @return the classes
     */
    public List<OWLClass> getClasses() {
        return classes;
    }
}
