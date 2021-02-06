package com.example;


import com.example.models.Book;
import com.example.models.BookList;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class BooksFromJsonFilterTest {
    private List<Book> books;

    //TODO figure out style for long strings
    @Before
    public void setUp() throws FileNotFoundException {
        BookList bookList = new BookList();
        bookList.loadBooksFromJsonFile(
    "C:\\Users\\ethan\\Documents\\School\\CS126" +
            "\\Json\\src\\main\\resources\\classics.json");
        books = bookList.getBookList();
    }
    @Test
    public void sanityCheck() {
    }
}