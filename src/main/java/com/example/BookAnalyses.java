package com.example;
import com.example.models.Book;
import com.example.models.BookList;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/** Utility class for analyzing BookLists. */

public final class BookAnalyses {
  /**
   * Finds the author's average polarity from all of their books.
   * @param bookList A BookList object
   * @param author The author's full name formatted as "lastname, firstname"
   * @return The polarity average as a double
   */
  public static double averageAuthorPolarity(final BookList bookList, String author) {
    //No need to check for null in this function because filterByAuthorName does
    BookList booksByAuthor = BookFilters.filterByAuthorName(bookList, author);

    double polaritySum = 0;
    for (Book book : booksByAuthor.getBookList()) {
      double polarity = book.getMetrics().getSentiments().getPolarity();
      polaritySum += polarity;
    }
    double averagePolarity = polaritySum / booksByAuthor.getBookList().size();

    //The following code derived from:
    //https://www.programiz.com/java-programming/examples/round-number-decimal
    DecimalFormat dFormat = new DecimalFormat("#.######");
    dFormat.setRoundingMode(RoundingMode.CEILING);

    return Double.parseDouble(dFormat.format(averagePolarity));
  }

  /**
   * Finds the average number of words in books less then or equal to a Readability Index.
   * @param bookList A BookList object
   * @param readability The Readability Index upper bound (inclusive) as a float
   * @return The average number of words as a double
   */
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
    //Checks if passed readability is too high.
    if (bookCount == 0) {
      throw new IllegalArgumentException();
    }
    return (double) totalNumberOfWords / bookCount;
  }

  /**
   * Finds the average number of book downloads in a BookList.
   * @param bookList A BookList object
   * @return The average number of downloads as a double
   */
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

  /**
   * Finds the maximum number of sentences in a BookList.
   * @param bookList A BookList object
   * @return The maximum number of sentences as an int
   */
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