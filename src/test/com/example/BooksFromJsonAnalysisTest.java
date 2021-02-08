package com.example;

import com.example.models.Book;
import com.example.models.BookList;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BooksFromJsonAnalysisTest {
    private List<Book> books;

    @Before
    public void setUp() throws FileNotFoundException {
        BookList bookList = new BookList();
        bookList.loadBooksFromJsonFile(
    "C:\\Users\\ethan\\Documents\\School\\CS126" +
            "\\Json\\src\\main\\resources\\classics.json");
        books = bookList.getBookList();
    }
    @Test
    public void testLoadBooksFromJsonFile() {
        assertEquals(1006, books.size());
    }
    @Test
    public void testAverageAuthorPolarityValid() {
        double averagePolarity = BookAnalyses.averageAuthorPolarity(books, "vonnegut kurt");
        assertEquals(.136139, averagePolarity, .000001);
    }
}
