package com.example.models.Bibliography;

public class Bibliography {
  private String languages;
  private String subjects;
  private String title;
  private String type;
  private Author author;

  public Bibliography() {}

 /**
  * @return languages as String
  */
  public String getLanguages() {
    return languages;
  }

  /**
   * @return subjects as String
   */
  public String getSubjects() {
    return subjects;
  }

  /**
   * @return title as String
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return type of medium as String
   */
  public String getType() {
    return type;
  }

  /**
   * @return author object
   */
  public Author getAuthor() {
    return author;
  }

  @Override
  public String toString() {
    return "Bibliography: "
        + languages
        + ", "
        + subjects
        + ", "
        + title
        + ", "
        + type
        + ", "
        + author;
  }
}