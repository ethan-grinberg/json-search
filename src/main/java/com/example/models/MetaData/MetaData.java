package com.example.models.MetaData;

public class MetaData {
    private int downloads;
    private int rank;
    private int id;

    public int getDownloads() {
        return downloads;
    }
    public int getRank() {
        return rank;
    }
    public int getId() {
        return id;
    }
    //TODO add java docs for all public methods
    public String toString() {
        return "MetaData: " + downloads + ", " + rank + ", " + id;
    }

}
