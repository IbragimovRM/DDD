package ru.ddd.lessons.service.domain.model;

import ru.ddd.lessons.service.domain.common.AbstractId;

import java.io.Serializable;
import java.util.Date;

public class StockDailyRecord extends AbstractId implements Serializable {

    private Float priceOpen;
    private Float priceClose;
    private Float priceChange;
    private Long volume;
    private Date date;
    private Stock stock;

    public StockDailyRecord() {

    }

    public Float getPriceOpen() {
        return priceOpen;
    }

    public void setPriceOpen(Float priceOpen) {
        this.priceOpen = priceOpen;
    }

    public Float getPriceClose() {
        return priceClose;
    }

    public void setPriceClose(Float priceClose) {
        this.priceClose = priceClose;
    }

    public Float getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(Float priceChange) {
        this.priceChange = priceChange;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
