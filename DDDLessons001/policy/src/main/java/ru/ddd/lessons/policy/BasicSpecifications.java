package ru.ddd.lessons.policy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public final class BasicSpecifications {

    public static <E> Specification<E> any(final Class<E> clazz) {
        return new Specification<E>() {
            @Override
            public boolean isSatisfied(E obj) {
                return true;
            }

            @Override
            public Predicate toPredicate(Root<E> root, CriteriaBuilder builder) {
                return builder.conjunction();
            }

            @Override
            public Class<E> getType() {
                return clazz;
            }
        };
    }

    public static <E> Specification<E> and(final Class<E> clazz, final Specification<E> a, final Specification<E> b) {
        return new Specification<E>() {

            @Override
            public boolean isSatisfied(E obj) {
                return a.isSatisfied(obj) && b.isSatisfied(obj);
            }

            @Override
            public Predicate toPredicate(Root<E> root, CriteriaBuilder builder) {
                return builder.and(
                        a.toPredicate(root, builder),
                        b.toPredicate(root, builder));
            }

            @Override
            public Class<E> getType() {
                return clazz;
            }
        };
    }

    private BasicSpecifications() {
    }
}
