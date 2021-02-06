package com.example.models.Bibliography;

import com.google.gson.annotations.SerializedName;

public class Author {
    private String name;
    //TODO make sure this annotation works correctly
    @SerializedName("birth")
    private int birthYear;
    @SerializedName("death")
    private int deathYear;

    public Author() { }

    public String getName() {
        return name;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public int getDeathYear() {
        return deathYear;
    }
    //TODO add java docs for public methods
    @Override
    public String toString() {
        return name + " (" + birthYear + " - " + deathYear + ")";
    }
}
