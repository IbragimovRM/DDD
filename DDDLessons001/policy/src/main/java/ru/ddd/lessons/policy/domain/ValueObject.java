package ru.ddd.lessons.policy.domain;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    boolean sameValueAs(T other);
}