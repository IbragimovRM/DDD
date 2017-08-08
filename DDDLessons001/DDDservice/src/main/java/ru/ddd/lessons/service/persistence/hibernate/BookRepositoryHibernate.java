package ru.ddd.lessons.service.persistence.hibernate;

import ru.ddd.lessons.policy.Dao;
import ru.ddd.lessons.policy.domain.Repository;
import ru.ddd.lessons.service.domain.model.Book;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.BookRepository;

public class BookRepositoryHibernate extends Repository<Book> implements BookRepository {
    public BookRepositoryHibernate(Dao dao) {
        super(dao);
    }

    @Override
    public void save(Book book) {
        defaultDao.save(book);
    }
}
