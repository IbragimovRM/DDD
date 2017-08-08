package ru.ddd.lessons.service.persistence.hibernate.interfaces;

import ru.ddd.lessons.service.domain.model.StockDailyRecord;

import java.util.List;

public interface StockDailyRecordRepository {
    void save(StockDailyRecord stockDailyRecord);
    StockDailyRecord findById(Integer Id);
    List<StockDailyRecord> findAll();
}
