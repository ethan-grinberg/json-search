package com.example;

import com.example.models.Book;
import com.example.models.BookList;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        BookList books = new BookList();
        books.loadBooksFromJsonFile("C:\\Users\\ethan\\Documents\\School\\CS126" +
                                    "\\Json\\src\\main\\resources\\classics.json");
        List<Book> bookList = books.getBookList();
        /*
        System.out.println(bookList.size());
        for (Book book : bookList) {
            System.out.println(book);
        }
         */
        List<Book> filteredBooks = BookFilters.filterByReadability(bookList, (float) 6.3);
        for (Book book : filteredBooks) {
            System.out.println(book.getMetrics().getDifficulty().getReadabilityIndex() <= 6.3);
        }
        System.out.println("-----------------");
        List<Book> subjectFilter = BookFilters.filterBySubjects(bookList, "Fiction");
        for (Book book : subjectFilter) {
            System.out.println(book);
        }
    }
}