package ru.ddd.lessons.service.persistence.hibernate.specifications;

import ru.ddd.lessons.policy.Specification;
import ru.ddd.lessons.service.domain.model.Stock;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StockSpecification {

    public StockSpecification() {

    }

    public static final Specification<Stock> findByStockCode(Integer stockCode) {
        return new Specification<Stock>() {
            @Override
            public boolean isSatisfied(Stock re) {
                return re.getStockCode().equals(stockCode);
            }

            @Override
            public Predicate toPredicate(Root<Stock> root, CriteriaBuilder builder) {
                return builder.and(builder.equal(root.get("stockCode"), stockCode));
            }
        };
    }
}
