package com.example.gradedclasswork;

public class Movie {
    String title;
    String Author;
    String Genre ;
    String duration ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Movie(String title, String author, String genre, String duration) {
        this.title = title;
        Author = author;
        Genre = genre;
        this.duration = duration;
    }
}
