package com.example.film.controller.form;

import com.example.film.model.Film;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class FilmForm {

    @NotNull @NotEmpty
    private String title;
    @NotNull @NotEmpty
    private String author;
    @NotNull @NotEmpty
    private String country;
    @NotNull @NotEmpty
    private String cinematography;
    @NotNull @NotEmpty
    private String releaseDate;

    public Film convert(){
        return new Film(this.getTitle(),this.getAuthor(),this.getCountry(),this.getCinematography(),LocalDate.parse(this.getReleaseDate()));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCinematography() {
        return cinematography;
    }

    public void setCinematography(String publisher) {
        this.cinematography = publisher;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
