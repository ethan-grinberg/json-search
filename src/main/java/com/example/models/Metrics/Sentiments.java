package com.example.models.Metrics;

public class Sentiments {
  private double polarity;
  private double subjectivity;

  public Sentiments() {}

  /**
   * @return polarity as a double
   */
  public double getPolarity() {
    return polarity;
  }

  /**
   * @return subjectivity as a double
   */
  public double getSubjectivity() {
    return subjectivity;
  }
}