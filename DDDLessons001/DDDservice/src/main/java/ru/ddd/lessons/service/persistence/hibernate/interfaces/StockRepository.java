package ru.ddd.lessons.service.persistence.hibernate.interfaces;

import ru.ddd.lessons.service.domain.model.Stock;

public interface StockRepository {
    void save(Stock stock);
    Stock findByStockCode(Integer stockCode);
}
