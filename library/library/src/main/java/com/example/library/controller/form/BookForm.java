package com.example.library.controller.form;

import com.example.library.model.Book;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class BookForm {

    @NotNull @NotEmpty
    private String Title;
    @NotNull @NotEmpty
    private String Author;
    @NotNull @NotEmpty
    private String Country;
    @NotNull @NotEmpty
    private String Publisher;
    @NotNull @NotEmpty
    private String ReleaseDate;

    public Book convert(){
        return new Book(this.getTitle(),this.getAuthor(),this.getCountry(),this.getPublisher(),LocalDate.parse(this.getReleaseDate()));
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

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }
}
