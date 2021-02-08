package com.example;

import com.example.models.Book;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public final class BookAnalyses {
  //Must be full author's name (last name, first name)
  public static double averageAuthorPolarity(final List<Book> bookList, String author) {
    //No need to check for null in this function because filterByAuthorName does
    List<Book> booksByAuthor = BookFilters.filterByAuthorName(bookList, author);
    double polaritySum = 0;
    for (Book book : booksByAuthor) {
      double polarity = book.getMetrics().getSentiments().getPolarity();
      polaritySum += polarity;
    }
    double averagePolarity = polaritySum / booksByAuthor.size();
    // The following code comes from:
    // https://www.programiz.com/java-programming/examples/round-number-decimal
    DecimalFormat dFormat = new DecimalFormat("#.######");
    dFormat.setRoundingMode(RoundingMode.CEILING);
    return Double.parseDouble(dFormat.format(averagePolarity));
  }
  //readability is upper bound inclusive
  public static double averageNumberOfWordsByReadability(final List<Book> bookList, float readability) {
    if (bookList == null || bookList.size() == 0 || readability <= 0) {
      throw new IllegalArgumentException();
    }
    int totalNumberOfWords = 0;
    int bookCount = 0;
    for (Book book : bookList) {
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
  public static double averageNumOfBookDownloads(final List<Book> bookList) {
    if (bookList == null || bookList.size() == 0) {
      throw new IllegalArgumentException();
    }
    int bookCount = 0;
    int totalDownloads = 0;
    for (Book book : bookList) {
      totalDownloads += book.getMetaData().getDownloads();
      bookCount++;
    }
    return (double) totalDownloads / bookCount;
  }
}
