package ru.ddd.lessons.service.domain.common;

import java.io.Serializable;

public abstract class AbstractGenId<T extends Serializable> {

    private T id;

    protected AbstractGenId(T id) {
        this.id = id;
    }

    private T getId() {
        return id;
    }

    private void setId(T id) {
        this.id = id;
        initIdentity(id);
    }

    abstract protected void initIdentity(T id);

    public boolean identityPresent() {
        return id != null;
    }
}
