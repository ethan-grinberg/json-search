package com.example;

import com.example.models.Book;
import com.example.models.BookList;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class BooksFromJsonAnalysisTest {
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
  // Sanity check for loading the books from Json file
  @Test
  public void sanityCheck() {
    assertEquals(1006, fullBookList.getBookList().size());
  }
  //Average Author polarity tests.
  @Test
  public void testAverageAuthorPolarityValid() {
    double averagePolarity = BookAnalyses.averageAuthorPolarity(fullBookList.getBookList(), "vonnegut kurt");
    assertEquals(.136139, averagePolarity, .000001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAverageAuthorPolarityAuthorNotFound() {
    double averagePolarity = BookAnalyses.averageAuthorPolarity(fullBookList.getBookList(), "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAverageAuthorPolarityNullAuthor() {
    double averagePolarity = BookAnalyses.averageAuthorPolarity(fullBookList.getBookList(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAverageAuthorPolarityNullBooks() {
    double averagePolarity = BookAnalyses.averageAuthorPolarity(null, "mark,twain");
  }

  //Average number of words by readability tests.
  @Test
  public void testAverageNumberOfWordsReadabilityValid() {
    BookList bookSubset = new BookList(fullBookList.getBookList().subList(0, 5));
    double averageNumOfWords = BookAnalyses.averageNumberOfWordsByReadability(bookSubset.getBookList(), (float) 10);
    assertEquals(16228.0, averageNumOfWords, .001);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageNumberOfWordsReadabilityInvalid() {
    double averageNumOfWords = BookAnalyses.averageNumberOfWordsByReadability(fullBookList.getBookList(), (float) -5);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageNumberOfWordsReadabilityNotFound() {
    BookList bookSubset = new BookList(fullBookList.getBookList().subList(0, 5));
    double averageNumOfWords = BookAnalyses.averageNumberOfWordsByReadability(bookSubset.getBookList(), (float) 6);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageNumberOfWordsReadabilityNullList() {
    double averageNumOfWords = BookAnalyses.averageNumberOfWordsByReadability(null, (float) 6);
  }

}
