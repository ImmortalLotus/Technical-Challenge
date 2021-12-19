package com.example.film.model;

import java.time.LocalDate;

public class Film {

    String Title, Author, Country, Publisher;
    LocalDate ReleaseDate;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Film(String title, String author, String country, String publisher, LocalDate releaseDate) {
        Title = title;
        Author = author;
        Country = country;
        Publisher = publisher;
        ReleaseDate = releaseDate;
        this.type="film";
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public LocalDate getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        ReleaseDate = releaseDate;
    }
}
