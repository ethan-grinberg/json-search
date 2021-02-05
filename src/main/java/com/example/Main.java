package com.example;

import com.example.models.Book;
import com.example.models.Books;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        File file = new File("C:\\Users\\ethan\\Documents\\School\\CS126\\Json\\src\\main\\resources\\classics.json");
        JsonReader reader = new JsonReader(new FileReader(file));
        Type booksType = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> bookArrayList = gson.fromJson(reader, booksType);
        Books books = new Books(bookArrayList);


    }
}