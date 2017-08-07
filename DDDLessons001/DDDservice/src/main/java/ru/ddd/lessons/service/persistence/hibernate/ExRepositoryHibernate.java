package ru.ddd.lessons.service.persistence.hibernate;

import org.springframework.transaction.annotation.Transactional;

import ru.ddd.lessons.policy.Dao;
import ru.ddd.lessons.policy.domain.Repository;
import ru.ddd.lessons.service.domain.model.Ex;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.ExRepository;

@Transactional(value = "transactionManager")
public class ExRepositoryHibernate extends Repository<Ex> implements ExRepository {
    public ExRepositoryHibernate(Dao dao) {
        super(dao);
    }

    @Override
    public void save(Ex example) {
        defaultDao.save(example);
    }
}
