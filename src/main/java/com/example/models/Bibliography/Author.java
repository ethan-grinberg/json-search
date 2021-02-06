package com.example.models.Bibliography;

import com.google.gson.annotations.SerializedName;

public class Author {
    private String name;
    //TODO make sure this annotation works correctly
    @SerializedName("birth")
    private String birthYear;
    @SerializedName("death")
    private String deathYear;

    public Author() { }
    //TODO add java docs for public methods
    @Override
    public String toString() {
        return name + " (" + birthYear + " - " + deathYear + ")";
    }
}