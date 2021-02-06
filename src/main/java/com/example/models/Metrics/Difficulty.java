package com.example.models.Metrics;

import com.google.gson.annotations.SerializedName;

public class Difficulty {
    @SerializedName("automated readability index")
    private float readabilityIndex;

    public Difficulty() { }

    //TODO add javadocs for public methods
    public float getReadabilityIndex() {
        return readabilityIndex;
    }
}
