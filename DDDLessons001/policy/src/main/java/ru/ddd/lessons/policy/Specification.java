package ru.ddd.lessons.policy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;

public abstract class Specification<T> {
    public abstract boolean isSatisfied(T obj);

    public abstract Predicate toPredicate(Root<T> root, CriteriaBuilder builder);

    public Class<T> getType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

    /**
     * Объединяем предикаты через умножение
     *
     * @param other
     * @return
     */
    public Specification<T> and(Specification<T> other) {
        return BasicSpecifications.and(getType(), this, other);
    }

    /**
     * Предикат-отрицание
     *
     * @return
     */
    public Specification<T> not() {
        final Specification<T> self = this;
        return new Specification<T>() {

            @Override
            public boolean isSatisfied(T obj) {
                return !self.isSatisfied(obj);
            }

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaBuilder builder) {
                return self.toPredicate(root, builder).not();
            }
        };
    }
}
