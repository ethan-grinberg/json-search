package com.example;

import com.example.models.Book;
import com.example.models.BookList;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        BookList bookList = new BookList();
        bookList.loadBooksFromJsonFile("C:\\Users\\ethan\\Documents\\School\\CS126" +
                                    "\\Json\\src\\main\\resources\\classics.json");
        /*
        System.out.println(bookList.size());
        for (Book book : bookList) {
            System.out.println(book);
        }
         */
        /*
        System.out.println(BookAnalyses.averageAuthorPolarity(bookList, "vonnegut kurt"));
        List<Book> filteredBooks = BookFilters.filterByAuthorBirthYear(bookList,1000, false);
        for (Book book : filteredBooks) {
            System.out.println(book);
        }
         */
        System.out.println("-----------------");
        /*
        List<Book> subjectFilter = BookFilters.filterBySubjects(bookList, "Fiction");
        for (Book book : subjectFilter) {
            System.out.println(book);
        }
         */
    }
}