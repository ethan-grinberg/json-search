package com.example.models.Metrics;

import com.google.gson.annotations.SerializedName;

public class Statistics {
  @SerializedName("average sentence length")
  private float averageSentenceLength;
  @SerializedName("characters")
  private int numOfCharacters;
  @SerializedName("sentences")
  private int numOfSentences;
  @SerializedName("words")
  private int numOfWords;

  public Statistics() {}

  /**
   * @return averageSentenceLength as a float
   */
  public float getAverageSentenceLength() {
    return averageSentenceLength;
  }

  /**
   * @return number of characters as an int
   */
  public int getNumOfCharacters() {
    return numOfCharacters;
  }

  /**
   * @return number of sentences as an int
   */
  public int getNumOfSentences() {
    return numOfSentences;
  }

  /**
   * @return number of words as an int
   */
  public int getNumOfWords() {
    return numOfWords;
  }
}