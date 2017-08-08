package ru.ddd.lessons.service.persistence.hibernate;

import org.springframework.transaction.annotation.Transactional;
import ru.ddd.lessons.policy.Dao;
import ru.ddd.lessons.policy.Specification;
import ru.ddd.lessons.policy.domain.Repository;
import ru.ddd.lessons.service.domain.model.StockDailyRecord;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.StockDailyRecordRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional(value = "transactionManager")
public class StockDailyRecordRepositoryHibernate extends Repository<StockDailyRecord> implements StockDailyRecordRepository {

    public StockDailyRecordRepositoryHibernate(Dao dao) {
        super(dao);
    }

    @Override
    public void save(StockDailyRecord stockDailyRecord) {
        defaultDao.save(stockDailyRecord);
    }

    @Override
    public StockDailyRecord findById(Integer Id) {
        return defaultDao.getByKey(StockDailyRecord.class, Id);
    }

    @Override
    public List<StockDailyRecord> findAll() {
        return defaultDao.findAll(new Specification<StockDailyRecord>() {
            @Override
            public boolean isSatisfied(StockDailyRecord obj) {
                return false;
            }

            @Override
            public Predicate toPredicate(Root<StockDailyRecord> root, CriteriaBuilder builder) {
                return builder.conjunction();
            }
        });
    }

}
