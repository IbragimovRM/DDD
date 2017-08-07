package ru.ddd.lessons.service.domain.model;

import ru.ddd.lessons.service.domain.common.AbstractId;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Stock extends AbstractId  {

    private String stockCode;
    private String stockName;
    private Set<StockDailyRecord> stockDailyRecords;

    public Stock() {
        stockDailyRecords = new HashSet<>(0);
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Set<StockDailyRecord> getStockDailyRecords() {
        return stockDailyRecords;
    }

    public void setStockDailyRecords(Set<StockDailyRecord> stockDailyRecords) {
        this.stockDailyRecords = stockDailyRecords;
    }
}
