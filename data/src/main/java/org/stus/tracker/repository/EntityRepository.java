package org.stus.tracker.repository;

public interface EntityRepository<T, ID extends Number> {

    T get(ID id);

    ID add(T entity);

    T update(T entity);

    ID remove(T entity);

}
