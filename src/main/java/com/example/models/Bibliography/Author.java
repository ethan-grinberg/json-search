package com.example.models.Bibliography;

import com.google.gson.annotations.SerializedName;

public class Author {
  private String name;
  @SerializedName("birth")
  private int birthYear;
  @SerializedName("death")
  private int deathYear;

  public Author() {}

  /**
   * @return name as string
   */
  public String getName() {
    return name;
  }

  /**
   * @return birth year as int
   */
  public int getBirthYear() {
    return birthYear;
  }

  /**
   * @return death year as int
   */
  public int getDeathYear() {
    return deathYear;
  }

  @Override
  public String toString() {
    return name + " (" + birthYear + " - " + deathYear + ")";
  }
}