package ru.ddd.lessons.policy;

import java.util.List;

/**
 * Basic data access object to entity storage
 * @param <E>
 */
public interface Dao {

    /**
     * Remove item from storage
     * @param <E>
     * @param e
     */
    <E> void delete(E e);

    /**
     * Remove item from storage by query
     * @param query
     */
    void delete(String query, List<Long> ids);

    /**
     * Save item to storage
     * @param <E>
     * @param e
     */
    <E> void save(E e);

    /**
     * Select object by object id/key
     * @param <E>
     * @param klass
     * @param key
     * @return
     */
    <E> E getByKey(Class<E> klazz, Object key);

    /**
     * Select objects, satisfied by specification
     * @param <E>
     * @param spec
     * @return
     */
    <E> List<E> findAll(Specification<E> spec);

    /**
     * Select any one object, satisfied by specification
     * @param <E>
     * @param spec
     * @return
     */
    <E> E findOne(Specification<E> spec);

    /**
     * Count objects, satisfied by specification
     * @param <E>
     * @param spec
     * @return
     */
    <E> long count(Specification<E> spec);

    <ENTITY> void merge(ENTITY e) ;
}

