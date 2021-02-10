package com.example.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

/** Wrapper class for Collection of Book objects */

public class BookList {
  private List<Book> bookList;

  public BookList() {}

  /**
   * This constructor gives the user the option to create a BookList from a list
   * instead of a json file.
   * @param setBookList The Book List as List<Book>
   */
  public BookList(List<Book> setBookList) {
    if (setBookList == null) {
      throw new IllegalArgumentException();
    }
    bookList = setBookList;
  }

  /**
   * Loads a Json file containing books into the BookList class
   * @param filePath The file path as a string
   * @throws FileNotFoundException if file location not valid
   */
  public void loadBooksFromJsonFile(String filePath) throws FileNotFoundException {
    if (filePath == null) {
      throw new IllegalArgumentException();
    }
    Gson gson = new Gson();
    File file = new File(filePath);
    JsonReader reader = new JsonReader(new FileReader(file));

    //Code derived from:
    //https://howtodoinjava.com/gson/gson-parse-json-array/
    Type booksType = new TypeToken<List<Book>>() {}.getType();

    bookList = gson.fromJson(reader, booksType);
  }

  /**
   * @return current bookList
   */
  public List<Book> getBookList() {
    return bookList;
  }

  @Override
  public String toString() {
    StringBuilder booksAsString = new StringBuilder();
    for (Book book : bookList) {
      booksAsString.append(book);
    }
    return booksAsString.toString();
  }
}