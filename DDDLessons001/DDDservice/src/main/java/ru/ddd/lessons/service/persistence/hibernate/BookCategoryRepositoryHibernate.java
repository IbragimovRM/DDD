package ru.ddd.lessons.service.persistence.hibernate;

import ru.ddd.lessons.policy.Dao;
import ru.ddd.lessons.policy.domain.Repository;
import ru.ddd.lessons.service.domain.model.BookCategory;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.BookCategoryRepository;

public class BookCategoryRepositoryHibernate extends Repository<BookCategory> implements BookCategoryRepository {
    public BookCategoryRepositoryHibernate(Dao dao) {
        super(dao);
    }

    @Override
    public void save(BookCategory bookCategory) {
        defaultDao.save(bookCategory);
    }
}
