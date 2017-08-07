package ru.ddd.lessons.service.domain.common;

import ru.ddd.lessons.policy.domain.ValueObject;

import java.util.Objects;

public abstract class AbstractId<T extends AbstractId, V> implements ValueObject<T> {

    protected V id;

    public V getValue() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        T that = (T) other;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean sameValueAs(T other) {
        return other != null && Objects.equals(id, other.id);
    }

}