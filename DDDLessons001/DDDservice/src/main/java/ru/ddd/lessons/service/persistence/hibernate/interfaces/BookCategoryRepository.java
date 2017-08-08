package ru.ddd.lessons.service.persistence.hibernate.interfaces;

import ru.ddd.lessons.service.domain.model.BookCategory;

public interface BookCategoryRepository {
    void save(BookCategory bookCategory);
}
