package com.example.models.Metrics;

public class Metrics {
    private Difficulty difficulty;
    private Sentiments sentiments;
    private Statistics statistics;
    //TODO add javadocs for public methods


    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Sentiments getSentiments() {
        return sentiments;
    }

    public Statistics getStatistics() {
        return statistics;
    }
    @Override
    public String toString() {
        return "Metrics\n[statistics: " + statistics.getNumOfCharacters() + ", "
                + statistics.getAverageSentenceLength() + ", " + statistics.getNumOfSentences() + ", "
                + statistics.getNumOfWords() + "]\n" + "[sentiments: " + sentiments.getPolarity()
                + ", " + sentiments.getSubjectivity() + "]\n" + "[ difficulty: " + difficulty.getReadabilityIndex()
                + "]";
    }
}
