package com.example.library.controller.dto;

import com.example.library.model.Book;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonPropertyOrder({"title","author","country","releaseDate","publisher","type"})
public class ArtWorksDto {
    String title, author, country;
    LocalDate releaseDate;

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
    
    public void setType(String type) {
    	this.type= type;
    }

    public String type;

    public ArtWorksDto() {

    }



    public String getAuthor() {
        return author;
    }

    public String getCountry() {
        return country;
    }



    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    
}
