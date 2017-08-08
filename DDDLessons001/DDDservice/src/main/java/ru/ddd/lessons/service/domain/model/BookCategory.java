package ru.ddd.lessons.service.domain.model;

import ru.ddd.lessons.service.domain.common.AbstractId;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BookCategory extends AbstractId implements Serializable {

    private String nameCategory;
    private String codeCategory;
    private Set<Book> books;

    public BookCategory(){
        books = new HashSet<>();
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getCodeCategory() {
        return codeCategory;
    }

    public void setCodeCategory(String codeCategory) {
        this.codeCategory = codeCategory;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
