package com.example.models;

import com.example.models.Bibliography.Bibliography;
import com.example.models.MetaData.MetaData;
import com.example.models.Metrics.Metrics;
import com.google.gson.annotations.SerializedName;

public class Book {
    //TODO check to make sure annotation works right
    private Bibliography bibliography;
    @SerializedName("metadata")
    private MetaData metaData;
    private Metrics metrics;
}
