package com.example.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class BookList {
  // TODO javadoc comments
  private List<Book> bookList;

  public BookList() {}

  // User has the option to create a booklist by passing in a list or a json file
  public BookList(List<Book> setBookList) {
    if (setBookList == null) {
      throw new IllegalArgumentException();
    }
    bookList = setBookList;
  }

  public void loadBooksFromJsonFile(String filePath) throws FileNotFoundException {
    if (filePath == null) {
      throw new IllegalArgumentException();
    }
    Gson gson = new Gson();
    File file = new File(filePath);
    JsonReader reader = new JsonReader(new FileReader(file));
    Type booksType = new TypeToken<List<Book>>() {}.getType();

    bookList = gson.fromJson(reader, booksType);
  }

  public List<Book> getBookList() {
    return bookList;
  }
}
