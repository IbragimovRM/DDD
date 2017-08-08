package ru.ddd.lessons.service.persistence.hibernate.interfaces;

import ru.ddd.lessons.service.domain.model.Book;

public interface BookRepository {
    void save(Book book);
}
