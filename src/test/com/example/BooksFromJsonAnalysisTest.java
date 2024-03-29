package com.example;

import com.example.models.BookList;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for the functions in the BookAnalyses utility class.
 */

public class BooksFromJsonAnalysisTest {
  private BookList fullBookList;

  @Before
  public void setUp() throws FileNotFoundException {
    fullBookList = new BookList();
    fullBookList.loadBooksFromJsonFile(
      "C:\\Users\\ethan\\Documents\\School\\CS126"
        + "\\Json\\src\\main\\resources\\classics.json");
  }
  //Sanity check for loading the books from Json file
  @Test
  public void sanityCheck() {
    assertEquals(1006, fullBookList.getBookList().size());
  }

  //Average author polarity tests.
  //Filter by author name was thoroughly tested in BooksFromJsonFilterTest.java.
  @Test
  public void testAverageAuthorPolarityValid() {
    double averagePolarity = BookAnalyses.averageAuthorPolarity(fullBookList, "vonnegut,kurt");
    assertEquals(.136139, averagePolarity, .000001);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageAuthorPolarityAuthorNotFound() {
    double averagePolarity = BookAnalyses.averageAuthorPolarity(fullBookList, "");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageAuthorPolarityNullAuthor() {
    double averagePolarity = BookAnalyses.averageAuthorPolarity(fullBookList, null);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageAuthorPolarityNullList() {
    double averagePolarity = BookAnalyses.averageAuthorPolarity(null, "twain,mark");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageAuthorPolarityEmptyList() {
    double averagePolarity = BookAnalyses.averageAuthorPolarity(new BookList(new ArrayList<>()), "twain, mark");
  }

  //Average number of words by readability tests.
  @Test
  public void testAverageNumberOfWordsReadabilityValid() {
    BookList bookSubset = new BookList(fullBookList.getBookList().subList(0, 5));
    double averageNumOfWords = BookAnalyses.averageNumberOfWordsByReadability(bookSubset, (float) 10);
    assertEquals(16228.0, averageNumOfWords, .001);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageNumbWordsReadabilityInvalid() {
    double averageNumOfWords = BookAnalyses.averageNumberOfWordsByReadability(fullBookList, (float) -5);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageNumWordsReadabilityNotFound() {
    BookList bookSubset = new BookList(fullBookList.getBookList().subList(0, 5));
    double averageNumOfWords = BookAnalyses.averageNumberOfWordsByReadability(bookSubset, (float) 6);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageNumWordsReadabilityNullList() {
    double averageNumOfWords = BookAnalyses.averageNumberOfWordsByReadability(null, (float) 10);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageNumWordsReadabilityEmptyList() {
    double averageNumOfWords = BookAnalyses.averageNumberOfWordsByReadability(
      new BookList(new ArrayList<>()),
      (float) 10);
  }

  //Average number of downloads tests.
  @Test
  public void testAverageNumDownloadsValid() {
    BookList bookSubset = new BookList(fullBookList.getBookList().subList(5, 10));
    double averageNumDownloads = BookAnalyses.averageNumOfBookDownloads(bookSubset);
    assertEquals(13328.2, averageNumDownloads, .001);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageNumDownloadsNullList() {
    double averageNumDownloads = BookAnalyses.averageNumOfBookDownloads(null);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAverageNumDownloadsEmptyList() {
    double averageNumDownloads = BookAnalyses.averageNumOfBookDownloads(new BookList(new ArrayList<>()));
  }

  //Max number of sentences tests.
  @Test
  public void testMaxNumSentencesValid() {
    BookList bookSubset = new BookList(fullBookList.getBookList().subList(85, 92));
    int numSentences = BookAnalyses.getMaxNumOfSentences(bookSubset);
    assertEquals(9144, numSentences, .00001);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testMaxNumSentencesEmptyList() {
    int numSentences = BookAnalyses.getMaxNumOfSentences(new BookList(new ArrayList<>()));
  }
  @Test(expected = IllegalArgumentException.class)
  public void testMaxNumSentencesNullList() {
    int numSentences = BookAnalyses.getMaxNumOfSentences(null);
  }
}