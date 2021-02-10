package com.example;

import com.example.models.Book;
import com.example.models.BookList;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** Unit test for the functions in the BookFilters utility class. */
public class BooksFromJsonFilterTest {
  private BookList fullBookList;

  @Before
  public void setUp() throws FileNotFoundException {
    fullBookList = new BookList();
    fullBookList.loadBooksFromJsonFile(
        "C:\\Users\\ethan\\Documents\\School\\CS126"
            + "\\Json\\src\\main\\resources\\classics.json");
  }
  // Sanity check for loading books from a Json file.
  @Test
  public void sanityCheck() {
    assertEquals(1006, fullBookList.getBookList().size());
  }

  // Books filtered by subjects tests.
  @Test
  public void testStringFormatFilterBySubject() {
    BookList filteredBookList = BookFilters.filterBySubjects(fullBookList, "  E ngLAn D,  ");
    for (Book book : filteredBookList.getBookList()) {
      String subjects = book.getBibliography().getSubjects().toLowerCase();
      assertTrue(subjects.contains("england"));
    }
  }
  @Test
  public void testFilterBySubjectFound() {
    BookList filteredBookList = BookFilters.filterBySubjects(fullBookList, "fiction");
    for (Book book : filteredBookList.getBookList()) {
      String subjects = book.getBibliography().getSubjects().toLowerCase();
      assertTrue(subjects.contains("fiction"));
    }
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterBySubjectNotFound() {
    BookList filteredBookList = BookFilters.filterBySubjects(fullBookList, "asdfasdflkjiie12");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterBySubjectNull() {
    BookList filteredBookList = BookFilters.filterBySubjects(fullBookList, null);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterBySubjectNullList() {
    BookList filteredBookList = BookFilters.filterBySubjects(null, "example");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterBySubjectEmptyList() {
    BookList filteredBookList =
        BookFilters.filterBySubjects(new BookList(new ArrayList<>()), "example");
  }

  // Books filtered by readability tests.
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
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByReadabilityNullList() {
    BookList filteredBookList = BookFilters.filterByReadability(null, (float) 10);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByReadabilityEmptyList() {
    BookList filteredBookList =
        BookFilters.filterByReadability(new BookList(new ArrayList<>()), (float) 10);
  }

  // Books filtered by author's birth year tests.
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByYearInvalidYear() {
    BookList filteredBookList = BookFilters.filterByAuthorBirthYear(fullBookList, -7000, false);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByYearNoAuthors() {
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
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByYearNullList() {
    BookList filteredBookList = BookFilters.filterByAuthorBirthYear(null, 1900, false);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByYearEmptyList() {
    BookList filteredBookList =
        BookFilters.filterByAuthorBirthYear(new BookList(new ArrayList<>()), 1900, false);
  }

  // Books filtered by author's name tests.
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByAuthorNotFound() {
    BookList filteredBookList = BookFilters.filterByAuthorName(fullBookList, "Green John");
  }
  @Test
  public void testFilterByAuthorFound() {
    BookList filteredBookList = BookFilters.filterByAuthorName(fullBookList, "vonnegut,kurt");
    for (Book book : filteredBookList.getBookList()) {
      String authorName = book.getBibliography().getAuthor().getName().toLowerCase();
      assertEquals("vonnegut, kurt", authorName);
    }
  }
  @Test
  public void testFilterByAuthorStringFormat() {
    BookList filteredBookList = BookFilters.filterByAuthorName(fullBookList, " Twai N ,, MaRK,,  ");
    for (Book book : filteredBookList.getBookList()) {
      String authorName = book.getBibliography().getAuthor().getName().toLowerCase();
      assertEquals("twain, mark", authorName);
    }
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByAuthorIncompleteName() {
    BookList filteredBookList = BookFilters.filterByAuthorName(fullBookList, "twain");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByAuthorNullName() {
    BookList filteredBookList = BookFilters.filterByAuthorName(fullBookList, null);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByAuthorNullList() {
    BookList filteredBookList = BookFilters.filterByAuthorName(null, "twain,mark");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testFilterByAuthorEmptyList() {
    BookList filteredBookList =
        BookFilters.filterByAuthorName(new BookList(new ArrayList<>()), "twain,mark");
  }
}