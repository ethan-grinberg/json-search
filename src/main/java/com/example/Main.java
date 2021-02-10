package com.example;
import com.example.models.BookList;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BookList bookList = new BookList();
        bookList.loadBooksFromJsonFile(
          "C:\\Users\\ethan\\Documents\\School\\CS126"
          + "\\Json\\src\\main\\resources\\classics.json");

        //Size of json data set
        System.out.println(bookList.getBookList().size());

        //BookList filters
        BookList filteredBySubjects = BookFilters.filterBySubjects(bookList, "England");
        System.out.println(filteredBySubjects);
        System.out.println("-----------------");

        BookList filteredByReadability = BookFilters.filterByReadability(bookList, (float) 10.5);
        System.out.println(filteredByReadability);
        System.out.println("-----------------");

        BookList filteredByBirthYear = BookFilters.filterByAuthorBirthYear(bookList, 1000, true);
        System.out.println(filteredByBirthYear);
        System.out.println("-----------------");

        BookList filteredByAuthorName = BookFilters.filterByAuthorName(bookList, "vonnegutkurt");
        System.out.println(filteredByAuthorName);

        //BookList Analysis
        double averageAuthorPolarity = BookAnalyses.averageAuthorPolarity(bookList, "twainmark");
        System.out.println(averageAuthorPolarity);

        double averageNumberOfWords = BookAnalyses.averageNumberOfWordsByReadability(bookList, (float) 12);
        System.out.println(averageNumberOfWords);

        double averageNumberOfDownloads = BookAnalyses.averageNumOfBookDownloads(bookList);
        System.out.println(averageNumberOfDownloads);

        int maxNumberOfSentences = BookAnalyses.getMaxNumOfSentences(bookList);
        System.out.println(maxNumberOfSentences);
    }
}