package ru.ddd.lessons.service.persistence.hibernate.interfaces;

import ru.ddd.lessons.service.domain.model.StockDailyRecord;

public interface StockDailyRecordRepository {
    void save(StockDailyRecord stockDailyRecord);
}
