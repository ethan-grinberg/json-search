package com.example;
import com.example.models.Book;
import com.example.models.BookList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/** Utility class for filtering BookLists. */

public final class BookFilters {
  /**
   * Helper function that returns the string in lowercase without spaces or commas.
   */
  private static String formatStringForSearch(String str) {
    //Code below delivered from:
    //https://www.geeksforgeeks.org/how-to-remove-all-white-spaces-from-a-string-in-java/
    str = str.toLowerCase().replaceAll("\\s", "");
    return str.replaceAll(",", "");
  }

  /**
   * Filters a BookList by all the books that contain the passed in subject.
   * @param bookList A BookList object
   * @param subject The desired subject as a string
   * @return A new BookList based on the condition
   */
  public static BookList filterBySubjects(final BookList bookList, String subject) {
    if (bookList == null || subject == null) {
      throw new IllegalArgumentException();
    }
    String subjectFormatted = formatStringForSearch(subject);

    List<Book> booksFilteredBySubject = new ArrayList<>();
    for (Book book : bookList.getBookList()) {
      String bookSubject = book.getBibliography().getSubjects();
      String bookSubjectFormatted = formatStringForSearch(bookSubject);

      if (bookSubjectFormatted.contains(subjectFormatted)) {
        booksFilteredBySubject.add(book);
      }
    }
    //Checks for empty BookLists or not found subject.
    if (booksFilteredBySubject.size() == 0) {
      throw new IllegalArgumentException();
    }
    return new BookList(booksFilteredBySubject);
  }

  /**
   * Filters a BookList by books that have a readability less than or equal to the passed Readability.
   * @param bookList A BookList object
   * @param readabilityIndex The desired Readability Index as a float.
   * @return A new BookList based on the condition
   */
  public static BookList filterByReadability(final BookList bookList, final float readabilityIndex) {
    if (bookList == null || readabilityIndex <= 0) {
      throw new IllegalArgumentException();
    }
    List<Book> booksFilteredByReadability = new ArrayList<>();
    for (Book book : bookList.getBookList()) {
      if (book.getMetrics().getDifficulty().getReadabilityIndex() <= readabilityIndex) {
        booksFilteredByReadability.add(book);
      }
    }
    //Checks if passed Readability Index is too high or if BookList is empty.
    if (booksFilteredByReadability.size() == 0) {
      throw new IllegalArgumentException();
    }
    return new BookList(booksFilteredByReadability);
  }

  /**
   * Filters a BookList by authors who have a birth year either before or after the passed in year.
   * @param bookList A BookList object
   * @param birthYear The desired year (inclusive) as an int (B.C is a negative int)
   * @param beforeBirthYear A boolean to filter books before or after the passed year
   * @return A new BookList based on the condition
   */
  public static BookList filterByAuthorBirthYear(
      final BookList bookList, final int birthYear, boolean beforeBirthYear) {

    if (bookList == null) {
      throw new IllegalArgumentException();
    }

    //Code below derived from:
    //https://stackoverflow.com/questions/136419/get-integer-value-of-the-current-year-in-java
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    //BC years are formatted as negative ints.
    final int START_OF_TIME = -4000;
    if (birthYear >= currentYear || birthYear < START_OF_TIME) {
      throw new IllegalArgumentException();
    }

    List<Book> booksFilteredByAuthorYear = new ArrayList<>();
    for (Book book : bookList.getBookList()) {
      int authorBirthYear = book.getBibliography().getAuthor().getBirthYear();
      if (beforeBirthYear) {
        if (authorBirthYear <= birthYear) {
          booksFilteredByAuthorYear.add(book);
        }
      } else {
        if (authorBirthYear >= birthYear) {
          booksFilteredByAuthorYear.add(book);
        }
      }
    }
    //Checks if there are no author birth years that meet the passed conditions.
    if (booksFilteredByAuthorYear.size() == 0) {
      throw new IllegalArgumentException();
    }
    return new BookList(booksFilteredByAuthorYear);
  }

  /**
   * Filters a BookList by author name.
   * @param bookList A BookList object
   * @param author The author's full name as a string formatted as "lastname, firstname"
   * @return A new BookList based on the condition
   */
  public static BookList filterByAuthorName(final BookList bookList, final String author) {
    if (bookList == null || author == null) {
      throw new IllegalArgumentException();
    }
    String authorFormatted = formatStringForSearch(author);

    List<Book> filteredByAuthor = new ArrayList<>();
    for (Book book : bookList.getBookList()) {
      String bookAuthor = book.getBibliography().getAuthor().getName();
      String formatBookAuthor = formatStringForSearch(bookAuthor);
      if (formatBookAuthor.equals(authorFormatted)) {
        filteredByAuthor.add(book);
      }
    }

    //Checks if the author isn't in the list.
    if (filteredByAuthor.size() == 0) {
      throw new IllegalArgumentException();
    }
    return new BookList(filteredByAuthor);
  }
}