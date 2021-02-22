package org.stus.tracker.repository;

import java.util.Collection;

public interface EntityRepository<T, ID extends Number> {

    T get(ID id);

    Collection<T> getAll();

    ID add(T entity);

    T update(T entity);

    ID remove(T entity);

}
