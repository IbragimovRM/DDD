package ru.ddd.lessons.service.domain.model;

import ru.ddd.lessons.service.domain.common.AbstractId;

import java.io.Serializable;
import java.util.Date;

public class Book extends AbstractId implements Serializable {

    private String name;
    private Date timeCreate;
    private BookCategory bookCategory;

    public Book(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
}
