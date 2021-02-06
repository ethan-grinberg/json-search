package com.example.models.Metrics;

import com.google.gson.annotations.SerializedName;

public class Difficulty {
    //TODO make sure annotation works correctly
    @SerializedName("automated readability index")
    private float readabilityIndex;

    //TODO add javadocs for public methods
    public float getReadabilityIndex() {
        return readabilityIndex;
    }
}
