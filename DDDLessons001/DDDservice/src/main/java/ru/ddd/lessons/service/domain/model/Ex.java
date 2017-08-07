package ru.ddd.lessons.service.domain.model;

import ru.ddd.lessons.service.domain.common.AbstractId;

public class Ex extends AbstractId {

    private String title;

    public Ex() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
