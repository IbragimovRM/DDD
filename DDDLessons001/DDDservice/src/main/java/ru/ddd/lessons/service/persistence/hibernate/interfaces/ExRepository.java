package ru.ddd.lessons.service.persistence.hibernate.interfaces;

import ru.ddd.lessons.service.domain.model.Ex;

public interface ExRepository {
    void save(Ex example);
}
