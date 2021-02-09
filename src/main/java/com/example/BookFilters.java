package com.example;
import com.example.models.Book;
import com.example.models.BookList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/** Utility class for filtering Book Lists. */

public final class BookFilters {
  // Helper function that returns the string in lowercase without spaces or commas.
  // Code below delivered from:
  // https://www.geeksforgeeks.org/how-to-remove-all-white-spaces-from-a-string-in-java/
  private static String formatStringForSearch(String str) {
    str = str.toLowerCase().replaceAll("\\s", "");
    return str.replaceAll(",", "");
  }

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
    if (booksFilteredBySubject.size() == 0) {
      throw new IllegalArgumentException();
    }
    return new BookList(booksFilteredBySubject);
  }

  // TODO add javadoc, readabilityIndex is upper bound
  public static BookList filterByReadability(
      final BookList bookList, final float readabilityIndex) {

    if (bookList == null || readabilityIndex <= 0) {
      throw new IllegalArgumentException();
    }
    List<Book> booksFilteredByReadability = new ArrayList<>();
    for (Book book : bookList.getBookList()) {
      if (book.getMetrics().getDifficulty().getReadabilityIndex() <= readabilityIndex) {
        booksFilteredByReadability.add(book);
      }
    }
    if (booksFilteredByReadability.size() == 0) {
      throw new IllegalArgumentException();
    }
    return new BookList(booksFilteredByReadability);
  }
  // TODO books can be before or after author death year Inclusive
  // TODO how to format big function argument?
  public static BookList filterByAuthorBirthYear(
      final BookList bookList, final int birthYear, boolean beforeBirthYear) {

    if (bookList == null) {
      throw new IllegalArgumentException();
    }
    // Code below derived from:
    // https://stackoverflow.com/questions/136419/get-integer-value-of-the-current-year-in-java
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    // BC years are formatted as negative ints.
    int beginningOfTime = -4000;
    if (birthYear >= currentYear || birthYear < beginningOfTime) {
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
    if (booksFilteredByAuthorYear.size() == 0) {
      throw new IllegalArgumentException();
    }
    return new BookList(booksFilteredByAuthorYear);
  }
  // TODO should be formatted as last name, first name, has to be full name
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
    if (filteredByAuthor.size() == 0) {
      throw new IllegalArgumentException();
    }
    return new BookList(filteredByAuthor);
  }
}
