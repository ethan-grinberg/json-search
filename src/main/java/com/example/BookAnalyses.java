package com.example;

import com.example.models.Book;
import com.example.models.BookList;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public final class BookAnalyses {
  //Must be full author's name (last name, first name)
  public static double averageAuthorPolarity(final BookList bookList, String author) {
    //No need to check for null in this function because filterByAuthorName does
    BookList booksByAuthor = BookFilters.filterByAuthorName(bookList, author);
    double polaritySum = 0;
    for (Book book : booksByAuthor.getBookList()) {
      double polarity = book.getMetrics().getSentiments().getPolarity();
      polaritySum += polarity;
    }
    double averagePolarity = polaritySum / booksByAuthor.getBookList().size();
    // The following code comes from:
    // https://www.programiz.com/java-programming/examples/round-number-decimal
    DecimalFormat dFormat = new DecimalFormat("#.######");
    dFormat.setRoundingMode(RoundingMode.CEILING);
    return Double.parseDouble(dFormat.format(averagePolarity));
  }
  //readability is upper bound inclusive
  public static double averageNumberOfWordsByReadability(final BookList bookList, float readability) {
    if (bookList == null || bookList.getBookList().size() == 0 || readability <= 0) {
      throw new IllegalArgumentException();
    }
    int totalNumberOfWords = 0;
    int bookCount = 0;
    for (Book book : bookList.getBookList()) {
      float bookReadability = book.getMetrics().getDifficulty().getReadabilityIndex();
      if (bookReadability <= readability) {
        totalNumberOfWords += book.getMetrics().getStatistics().getNumOfWords();
        bookCount++;
      }
    }
    if (bookCount == 0) {
      throw new IllegalArgumentException();
    }
    return (double ) totalNumberOfWords / bookCount;
  }
  public static double averageNumOfBookDownloads(final BookList bookList) {
    if (bookList == null || bookList.getBookList().size() == 0) {
      throw new IllegalArgumentException();
    }
    int bookCount = 0;
    int totalDownloads = 0;
    for (Book book : bookList.getBookList()) {
      totalDownloads += book.getMetaData().getDownloads();
      bookCount++;
    }
    return (double) totalDownloads / bookCount;
  }
  public static int getMaxNumOfSentences(final BookList bookList) {
    if (bookList == null || bookList.getBookList().size() == 0) {
      throw new IllegalArgumentException();
    }
    int numSentences = 0;
    for (Book book : bookList.getBookList()) {
      int numOfBookSentences = book.getMetrics().getStatistics().getNumOfSentences();
      if (numOfBookSentences > numSentences) {
        numSentences = numOfBookSentences;
      }
    }
    return numSentences;
  }
}
