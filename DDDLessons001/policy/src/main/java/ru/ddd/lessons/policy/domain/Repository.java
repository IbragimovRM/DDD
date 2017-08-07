package ru.ddd.lessons.policy.domain;

import ru.ddd.lessons.policy.Dao;

public abstract class Repository<E> {

    protected Repository(Dao dao) {
        if (dao == null) {
            throw new IllegalArgumentException("Dao must not be null");
        }
        defaultDao = dao;
    }

    protected final Dao defaultDao;
}
