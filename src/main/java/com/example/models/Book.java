package com.example.models;

import com.example.models.Bibliography.Bibliography;
import com.example.models.MetaData.MetaData;
import com.example.models.Metrics.Metrics;
import com.google.gson.annotations.SerializedName;

public class Book {
    private Bibliography bibliography;
    @SerializedName("metadata")
    private MetaData metaData;
    private Metrics metrics;

    //TODO add javadoc for public methods
    public Book() { }

    public Bibliography getBibliography() {
        return bibliography;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    @Override
    public String toString() {
        return bibliography + "\n" + metrics
                + "\n" + metaData + "\n";
    }
}
