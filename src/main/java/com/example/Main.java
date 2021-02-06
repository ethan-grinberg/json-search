package com.example;

import com.example.models.Book;
import com.example.models.Books;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        List<Book> books = Books.loadBooksFromJsonFile(
                "C:\\Users\\ethan\\Documents\\School\\CS126\\Json\\src\\main\\resources\\classics.json");

        System.out.println(books.size());
        for (Book book : books) {
            System.out.println(book);
        }
    }
}