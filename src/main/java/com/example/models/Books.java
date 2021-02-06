package com.example.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class Books {
    //TODO javadoc comment

    public static List<Book> loadBooksFromJsonFile(String filePath) throws FileNotFoundException {
        if (filePath == null) {
            throw new IllegalArgumentException();
        }
        Gson gson = new Gson();
        File file = new File(filePath);
        JsonReader reader = new JsonReader(new FileReader(file));
        Type booksType = new TypeToken<List<Book>>(){}.getType();
        List<Book> bookList = gson.fromJson(reader, booksType);

        return bookList;
    }


}
