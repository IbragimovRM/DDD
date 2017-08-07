package ru.ddd.lessons.service.persistence.hibernate;

import org.springframework.transaction.annotation.Transactional;
import ru.ddd.lessons.policy.Dao;
import ru.ddd.lessons.policy.domain.Repository;
import ru.ddd.lessons.service.domain.model.StockDailyRecord;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.StockDailyRecordRepository;

@Transactional(value = "transactionManager")
public class StockDailyRecordRepositoryHibernate extends Repository<StockDailyRecord> implements StockDailyRecordRepository {

    public StockDailyRecordRepositoryHibernate(Dao dao) {
        super(dao);
    }

    @Override
    public void save(StockDailyRecord stockDailyRecord) {
        defaultDao.save(stockDailyRecord);
    }
}
