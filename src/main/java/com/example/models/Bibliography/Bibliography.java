package com.example.models.Bibliography;

public class Bibliography {
    private String languages;
    private String subjects;
    private String title;
    private String type;
    private Author author;
    //TODO add java docs for all these public methods
    public Bibliography() {
    }
    public String getLanguages() {
        return languages;
    }
    public String getSubjects() {
        return subjects;
    }
    public String getTitle() {
        return title;
    }
    public String getType() {
        return type;
    }
    public Author getAuthor() {
        return author;
    }
    @Override
    public String toString() {
        return "Bibliography: " + languages + ", " + subjects
                + ", " + title + ", " + type + ", " + author;
    }

}
