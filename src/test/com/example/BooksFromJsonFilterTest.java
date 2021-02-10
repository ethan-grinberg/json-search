package com.example;

import com.example.models.Book;
import com.example.models.BookList;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for the functions in the BookFilters utility class.
 */

public class BooksFromJsonFilterTest {
  private BookList fullBookList;

  @Before
  public void setUp() throws FileNotFoundException {
    fullBookList = new BookList();
    fullBookList.loadBooksFromJsonFile(
      "C:\\Users\\ethan\\Documents\\School\\CS126"
        + "\\Json\\src\\main\\resources\\classics.json");
  }
  //Sanity check for loading books from a Json file.
  @Test
  public void sanityCheck() {
    assertEquals(1006, fullBookList.getBookList().size());
  }

  //Books filtered by subjects tests.
  @Test
  public void testFilterBySubjectFound() {
    BookList filteredBookList = BookFilters.filterBySubjects(fullBookList, " FICtion   ");
    for (Book book : filteredBookList.getBookList()) {
      String subjects = book.getBibliography().getSubjects().toLowerCase();
      assertTrue(subjects.contains("fiction"));
    }
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterBySubjectNotFound() {
    BookList filteredBookList = BookFilters.filterBySubjects(fullBookList, "asdfasdflkjiie12");
  }

  //Books filtered by readability tests.
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByReadabilityIllegalNumber() {
    BookList filteredBookList = BookFilters.filterByReadability(fullBookList, (float) -5.6);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByReadabilityNotFound() {
    BookList filteredBookList = BookFilters.filterByReadability(fullBookList, (float) 2);
  }
  @Test
  public void testFilterByReadabilityValid() {
    BookList filteredBookList = BookFilters.filterByReadability(fullBookList, (float) 6.3);
    for (Book book : filteredBookList.getBookList()) {
      float readabilityIndex = book.getMetrics().getDifficulty().getReadabilityIndex();
      assertTrue(readabilityIndex <= (float) 6.3);
    }
  }

  //Books filtered by author's birth year tests.
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByYearInvalidYear() {
    BookList filteredBookList = BookFilters.filterByAuthorBirthYear(fullBookList, -7000, false);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByYearNoneExistent() {
    BookList filteredBookList = BookFilters.filterByAuthorBirthYear(fullBookList, 2015, false);
  }
  @Test
  public void testFilterByYearBeforeBirth() {
    BookList filteredBookList = BookFilters.filterByAuthorBirthYear(fullBookList, 1981, true);
    for (Book book : filteredBookList.getBookList()) {
      int birthYear = book.getBibliography().getAuthor().getBirthYear();
      assertTrue(birthYear <= 1981);
    }
  }
  @Test
  public void testFilterByYearAfterBirth() {
    BookList filteredBookList = BookFilters.filterByAuthorBirthYear(fullBookList, 1900, false);
    for (Book book : filteredBookList.getBookList()) {
      int birthYear = book.getBibliography().getAuthor().getBirthYear();
      assertTrue(birthYear >= 1900);
    }
  }

  //Books filtered by author's name tests.
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByAuthorNotFound() {
    BookList filteredBookList = BookFilters.filterByAuthorName(fullBookList, "John Green");
  }
  @Test
  public void testFilterByAuthorUpperCase() {
    BookList filteredBookList = BookFilters.filterByAuthorName(fullBookList, "TWAINMARK");
    for (Book book : filteredBookList.getBookList()) {
      String authorName = book.getBibliography().getAuthor().getName().toLowerCase();
      assertEquals("twain, mark", authorName);
    }
  }
  @Test
  public void testFilterByAuthorCommas() {
    BookList filteredBookList = BookFilters.filterByAuthorName(fullBookList, "twain,, mark");
    for (Book book : filteredBookList.getBookList()) {
      String authorName = book.getBibliography().getAuthor().getName().toLowerCase();
      assertEquals("twain, mark", authorName);
    }
  }
  @Test
  public void testFilterByAuthorSpaces() {
    BookList filteredBookList = BookFilters.filterByAuthorName(fullBookList, "   twain      mark   ");
    for (Book book : filteredBookList.getBookList()) {
      String authorName = book.getBibliography().getAuthor().getName().toLowerCase();
      assertEquals("twain, mark", authorName);
    }
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByAuthorIncompleteName() {
    BookList filteredBookList = BookFilters.filterByAuthorName(fullBookList, "twain");
  }
}