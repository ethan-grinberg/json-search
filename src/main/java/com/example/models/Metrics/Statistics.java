package com.example.models.Metrics;

import com.google.gson.annotations.SerializedName;

public class Statistics {
    //TODO make sure annotation works correctly
    @SerializedName("average sentence length")
    private float averageSentenceLength;
    @SerializedName("characters")
    private int numOfCharacters;
    @SerializedName("sentences")
    private int numOfSentences;
    @SerializedName("words")
    private int numOfWords;

    //TODO add java docs for public methods
    public float getAverageSentenceLength() {
        return averageSentenceLength;
    }
    public int getNumOfCharacters() {
        return numOfCharacters;
    }
    public int getNumOfSentences() {
        return numOfSentences;
    }
    public int getNumOfWords() {
        return numOfWords;
    }

}
