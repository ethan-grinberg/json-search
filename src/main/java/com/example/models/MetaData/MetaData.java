package com.example.models.MetaData;

public class MetaData {
  private int downloads;
  private int rank;
  private int id;

  public MetaData() {}

  /**
   * @return downloads as an int
   */
  public int getDownloads() {
    return downloads;
  }

  /**
   * @return rank as an int
   */
  public int getRank() {
    return rank;
  }

  /**
   * @return id as an int
   */
  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "MetaData: " + downloads + ", " + rank + ", " + id;
  }
}