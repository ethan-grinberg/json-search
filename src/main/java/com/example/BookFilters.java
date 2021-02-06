package com.example;
import com.example.models.Book;
import java.util.ArrayList;
import java.util.List;

//TODO add all javadocs (utility class)
public final class BookFilters {
    public static List<Book> filterBySubjects(final List<Book> bookList, final String subject) {
        if (bookList == null || subject == null) {
            throw new IllegalArgumentException();
        }
        //Code below delivered from:
        //https://www.geeksforgeeks.org/how-to-remove-all-white-spaces-from-a-string-in-java/
        //Converts string to lowercase and removes all spaces.
        String subjectFormatted = subject.toLowerCase().replaceAll("\\s", "");

        List<Book> booksFilteredBySubject = new ArrayList<>();
        for (Book book : bookList) {
            String bookSubject = book.getBibliography().getSubjects();
            String bookSubjectFormatted = bookSubject.toLowerCase().replaceAll("\\s", "");

            if (bookSubjectFormatted.contains(subjectFormatted)) {
                booksFilteredBySubject.add(book);
            }
        }
        if (booksFilteredBySubject.size() == 0) {
            throw new IllegalArgumentException();
        }
        return booksFilteredBySubject;
    }
    //TODO add javadoc, readabilityIndex is uppper bound
    public static List<Book> filterByReadability(final List<Book> bookList, final float readabilityIndex) {
        if (bookList == null || readabilityIndex < 0) {
            throw new IllegalArgumentException();
        }
        List<Book> booksFilteredByReadability = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getMetrics().getDifficulty().getReadabilityIndex() <= readabilityIndex) {
                booksFilteredByReadability.add(book);
            }
        }
        if (booksFilteredByReadability.size() == 0) {
            throw new IllegalArgumentException();
        }
        return booksFilteredByReadability;
    }
}
