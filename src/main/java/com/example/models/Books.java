package com.example.models;

import java.util.List;

public class Books {
    private List<Book> books;
    //TODO javadoc comment
    public Books(List<Book> setBooks) {
        if (setBooks == null) {
            throw new IllegalArgumentException();
        }
        books = setBooks;
    }
    //TODO javadoc comment
    public List<Book> getBooks() {
        return books;
    }

}
