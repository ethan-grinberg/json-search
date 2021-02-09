package com.example;

import com.example.models.Book;
import com.example.models.BookList;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BooksFromJsonFilterTest {
  private BookList fullBookList;

  // TODO figure out style for long strings
  @Before
  public void setUp() throws FileNotFoundException {
    BookList bookList = new BookList();
    bookList.loadBooksFromJsonFile(
      "C:\\Users\\ethan\\Documents\\School\\CS126"
        + "\\Json\\src\\main\\resources\\classics.json");
    fullBookList = bookList;
  }
  //Sanity check for loading books from Json file
  @Test
  public void sanityCheck() {
    assertEquals(1006, fullBookList.getBookList().size());
  }
  // Books filtered by subjects tests.
  @Test
  public void testFilterBySubjectFound() {
    BookList filteredBooks = new BookList(
      BookFilters.filterBySubjects(fullBookList.getBookList(), " FICtion   ")
    );
    for (Book book : filteredBooks.getBookList()) {
      String subjects = book.getBibliography().getSubjects().toLowerCase();
      assertTrue(subjects.contains("fiction"));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterBySubjectNotFound() {
    BookList filteredBooks = new BookList(
      BookFilters.filterBySubjects(fullBookList.getBookList(), "asdfasdflkjiie12")
    );
  }

  // Books filtered by readability tests.
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByReadabilityIllegalNumber() {
    BookList filteredBooks = new BookList(
      BookFilters.filterByReadability(fullBookList.getBookList(), (float) -5.6)
    );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterByReadabilityNotFound() {
    BookList filteredBooks = new BookList(
      BookFilters.filterByReadability(fullBookList.getBookList(), (float) 2)
    );
  }

  @Test
  public void testFilterByReadabilityValid() {
    BookList filteredBooks = new BookList(
      BookFilters.filterByReadability(fullBookList.getBookList(), (float) 6.3)
    );
    for (Book book : filteredBooks.getBookList()) {
      float readabilityIndex = book.getMetrics().getDifficulty().getReadabilityIndex();
      assertTrue(readabilityIndex <= (float) 6.3);
    }
  }
  // Books filtered by author's birth year tests.
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByYearInvalidYear() {
    BookList filteredBooks = new BookList(
      BookFilters.filterByAuthorBirthYear(
        fullBookList.getBookList(), -7000, false)
    );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterByYearNoneExistent() {
    BookList filteredBooks = new BookList(
      BookFilters.filterByAuthorBirthYear(
        fullBookList.getBookList(), 2015, false)
    );
  }

  @Test
  public void testFilterByYearBeforeBirth() {
    BookList filteredBooks = new BookList(
      BookFilters.filterByAuthorBirthYear(
        fullBookList.getBookList(), 1981, true)
    );
    for (Book book : filteredBooks.getBookList()) {
      int birthYear = book.getBibliography().getAuthor().getBirthYear();
      assertTrue(birthYear <= 1981);
    }
  }

  @Test
  public void testFilterByYearAfterBirth() {
    BookList filteredBooks = new BookList(
      BookFilters.filterByAuthorBirthYear(
        fullBookList.getBookList(), 1900, false)
    );
    for (Book book : filteredBooks.getBookList()) {
      int birthYear = book.getBibliography().getAuthor().getBirthYear();
      assertTrue(birthYear >= 1900);
    }
  }
  // Books filtered by author's name.
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByAuthorNotFound() {
    BookList filteredBooks = new BookList(
      BookFilters.filterByAuthorName(fullBookList.getBookList(), "John Green")
    );
  }

  @Test
  public void testFilterByAuthorUpperCase() {
    BookList filteredBooks = new BookList(BookFilters.filterByAuthorName(
      fullBookList.getBookList(), "TWAINMARK")
    );
    for (Book book : filteredBooks.getBookList()) {
      String authorName = book.getBibliography().getAuthor().getName().toLowerCase();
      assertEquals("twain, mark", authorName);
    }
  }

  @Test
  public void testFilterByAuthorCommas() {
    BookList filteredBooks = new BookList(
      BookFilters.filterByAuthorName(fullBookList.getBookList(), "twain,, mark")
    );
    for (Book book : filteredBooks.getBookList()) {
      String authorName = book.getBibliography().getAuthor().getName().toLowerCase();
      assertEquals("twain, mark", authorName);
    }
  }

  @Test
  public void testFilterByAuthorSpaces() {
    BookList filteredBooks = new BookList(
        BookFilters.filterByAuthorName(fullBookList.getBookList(), "   twain      mark   ")
    );
    for (Book book : filteredBooks.getBookList()) {
      String authorName = book.getBibliography().getAuthor().getName().toLowerCase();
      assertEquals("twain, mark", authorName);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterByAuthorIncompleteName() {
    BookList filteredBooks = new BookList(
      BookFilters.filterByAuthorName(fullBookList.getBookList(), "twain")
    );
  }
}