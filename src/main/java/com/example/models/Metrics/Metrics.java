package com.example.models.Metrics;

public class Metrics {
  private Difficulty difficulty;
  private Sentiments sentiments;
  private Statistics statistics;

  public Metrics() {}

  /**
   * @return difficulty object
   */
  public Difficulty getDifficulty() {
    return difficulty;
  }

  /**
   * @return sentiments object
   */
  public Sentiments getSentiments() {
    return sentiments;
  }

  /**
   * @return statistics object
   */
  public Statistics getStatistics() {
    return statistics;
  }

  @Override
  public String toString() {
    return "Metrics\n[statistics: "
        + statistics.getNumOfCharacters()
        + ", "
        + statistics.getAverageSentenceLength()
        + ", "
        + statistics.getNumOfSentences()
        + ", "
        + statistics.getNumOfWords()
        + "]\n"
        + "[sentiments: "
        + sentiments.getPolarity()
        + ", "
        + sentiments.getSubjectivity()
        + "]\n"
        + "[ difficulty: "
        + difficulty.getReadabilityIndex()
        + "]";
  }
}