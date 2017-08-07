package ru.ddd.lessons.dbapi;

import ru.ddd.lessons.policy.Dao;
import ru.ddd.lessons.policy.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaDao implements Dao {

    private final EntityManager em;

    public JpaDao(EntityManager em) {
        this.em = em;
    }

    public <ENTITY> void delete(ENTITY e) {
        em.remove(e);
    }

    public void delete(String query, List<Long> ids) {
        em.createQuery(query).setParameter("ID", ids).executeUpdate();
    }


    public <ENTITY> List<ENTITY> findAll(Specification<ENTITY> spec) {
        return em.createQuery(getCQ(em, spec)).getResultList();
    }


    public <ENTITY> ENTITY findOne(Specification<ENTITY> spec) {
        return em.createQuery(getCQ(em, spec)).getSingleResult();
    }


    public <ENTITY> long count(Specification<ENTITY> spec) {
        return em.createQuery(getCountCQ(em, spec)).getSingleResult();
    }

    public <ENTITY> void save(ENTITY e) {
        em.persist(e);
    }

    /**
     * Build basic query: SELECT FROM
     *
     * @param <E>
     * @param em
     * @param spec
     * @return
     */
    private static <ENTITY> CriteriaQuery<ENTITY> getCQ(EntityManager em, Specification<ENTITY> spec) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ENTITY> cq = cb.createQuery(spec.getType());
        Root<ENTITY> root = cq.from(spec.getType());
        Predicate p = spec.toPredicate(root, cb);
        cq.where(p);
        return cq;
    }

    /**
     * Build such query: SELECT COUNT() FROM
     *
     * @param <E>
     * @param em
     * @param spec
     * @return
     */
    private static <ENTITY> CriteriaQuery<Long> getCountCQ(EntityManager em, Specification<ENTITY> spec) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ENTITY> root = cq.from(spec.getType());
        cq.select(cb.count(root));
        cq.where(spec.toPredicate(root, cb));
        return cq;
    }

    /**
     * Select entity by key/id
     *
     * @param <E>
     * @param klass
     * @param key
     * @return
     */
    public <ENTITY> ENTITY getByKey(Class<ENTITY> klass, Object key) {
        return em.find(klass, key);
    }

    public <ENTITY> void merge(ENTITY e) {
        em.merge(e);
    }
}

