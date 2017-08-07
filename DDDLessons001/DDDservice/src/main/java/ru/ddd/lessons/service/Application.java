package ru.ddd.lessons.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ddd.lessons.service.domain.model.Stock;
import ru.ddd.lessons.service.domain.model.StockDailyRecord;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.StockDailyRecordRepository;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.StockRepository;

import java.util.Date;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    StockRepository stockRepositroy;

    @Autowired
    StockDailyRecordRepository stockDailyRecordRepository;

    @Override
    public void run(String... args) {

        /*
        Stock stock = new Stock();
        stock.setStockCode("7021252");
        stock.setStockName("PADIecNI");
        stockRepositroy.save(stock);

        StockDailyRecord stockDailyRecords = new StockDailyRecord();
        stockDailyRecords.setPriceOpen(new Float("1.2"));
        stockDailyRecords.setPriceClose(new Float("1.1"));
        stockDailyRecords.setPriceChange(new Float("10.0"));
        stockDailyRecords.setVolume(3000000L);
        stockDailyRecords.setDate(new Date());

        stockDailyRecords.setStock(stock);
        stock.getStockDailyRecords().add(stockDailyRecords);

        stockDailyRecordRepository.save(stockDailyRecords);
        */

        Stock stock = stockRepositroy.findByStockCode(13);

        System.out.println(stock.toString());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}

