package com.example;


import com.example.models.Book;
import com.example.models.BookList;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FilterBooksFromJsonTest {
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
    //Books filtered by subjects tests.
    @Test
    public void testFilterBySubjectFound() {
        List<Book> filteredBooks = BookFilters.filterBySubjects(books, " FICtion   ");
        for (Book book : filteredBooks) {
            String subjects = book.getBibliography().getSubjects().toLowerCase();
            assertTrue(subjects.contains("fiction"));
        }
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFilterBySubjectNotFound() {
        List<Book> filteredBooks = BookFilters.filterBySubjects(books, "asdfasdflkjiie12");
    }

    //Books filtered by readability tests.
    @Test(expected = IllegalArgumentException.class)
    public void testFilterByReadabilityIllegalNumber() {
        List<Book> filteredBooks = BookFilters.filterByReadability(books, (float) -5.6);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFilterByReadabilityNotFound() {
        List<Book> filteredBooks = BookFilters.filterByReadability(books, (float) 2);
    }
    @Test
    public void testFilterByReadabilityValid() {
        List<Book> filteredBooks = BookFilters.filterByReadability(books, (float) 6.3);
        for (Book book : filteredBooks) {
            float readabilityIndex = book.getMetrics().getDifficulty().getReadabilityIndex();
            assertTrue(readabilityIndex <= (float) 6.3);
        }
    }
    //Books filtered by author's birth year tests.
    @Test(expected = IllegalArgumentException.class)
    public void testFilterByYearInvalidYear() {
        List<Book> filteredBooks = BookFilters.filterByAuthorBirthYear(books, -7000, false);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFilterByYearNoneExistent() {
        List<Book> filteredBooks = BookFilters.filterByAuthorBirthYear(books,2015, false);
    }
    @Test
    public void testFilterByYearBeforeBirth() {
        List<Book> filteredBooks = BookFilters.filterByAuthorBirthYear(books,1981, true);
        for (Book book : filteredBooks) {
            int birthYear = book.getBibliography().getAuthor().getBirthYear();
            assertTrue(birthYear <= 1981);
        }
    }
    @Test
    public void testFilterByYearAfterBirth() {
        List<Book> filteredBooks = BookFilters.filterByAuthorBirthYear(books,1900, false);
        for (Book book : filteredBooks) {
            int birthYear = book.getBibliography().getAuthor().getBirthYear();
            assertTrue(birthYear >= 1900);
        }
    }
}