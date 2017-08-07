package ru.ddd.lessons.service.persistence.hibernate;

import org.springframework.transaction.annotation.Transactional;
import ru.ddd.lessons.policy.Dao;
import ru.ddd.lessons.policy.domain.Repository;
import ru.ddd.lessons.service.domain.model.Stock;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.StockRepository;
import ru.ddd.lessons.service.persistence.hibernate.specifications.StockSpecification;

@Transactional(value = "transactionManager")
public class StockRepositoryHibernate extends Repository<Stock> implements StockRepository {
    public StockRepositoryHibernate(Dao dao) {
        super(dao);
    }

    @Override
    public void save(Stock stock) {
        defaultDao.save(stock);
    }

    @Override
    public Stock findByStockCode(Integer stockCode) {
        return defaultDao.findOne(StockSpecification.findByStockCode(stockCode));
    }
}
