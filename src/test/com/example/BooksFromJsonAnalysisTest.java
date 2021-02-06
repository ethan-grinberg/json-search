package com.example;

import com.example.models.Book;
import com.example.models.BookList;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class BooksFromJsonAnalysisTest {
    private List<Book> bookList;

    @Before
    public void setUp() throws FileNotFoundException {
        BookList books = new BookList();
        books.loadBooksFromJsonFile(
    "C:\\Users\\ethan\\Documents\\School\\CS126" +
            "\\Json\\src\\main\\resources\\classics.json");
        bookList = books.getBookList();
    }
}
