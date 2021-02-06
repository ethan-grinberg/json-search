package com.example;


import com.example.models.Book;
import com.example.models.Books;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

//It may be a good idea to rename/refactor depending on the focus of your assignment.
public class BooksFromJsonTest {
    private List<Book> books;
    @Before
    public void setUp() throws FileNotFoundException {
        books = Books.loadBooksFromJsonFile(
                "C:\\Users\\ethan\\Documents\\School\\CS126" +
                        "\\Json\\src\\main\\resources\\classics.json");
    }

    @Test
    public void sanityCheck() {
    }
}