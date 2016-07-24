/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj.nlp.model;

/**
 *
 * @author ajadriano
 * @param <T>
 */
public class GrammaticalRelation<T> {
    private final GrammaticalDependency dependency;
    private final T target;
    private final T source;
    
    public GrammaticalRelation(GrammaticalDependency dependency,
            T target, T source) {
        this.dependency = dependency;
        this.target = target;
        this.source = source;
    }

    /**
     * @return the dependency
     */
    public GrammaticalDependency getDependency() {
        return dependency;
    }

    /**
     * @return the target
     */
    public T getTarget() {
        return target;
    }
    
    /**
     * @return the source
     */
    public T getSource() {
        return source;
    }
}
