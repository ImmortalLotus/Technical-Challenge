package com.example.film.controller.dto;

import com.example.film.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmDto extends ArtWorksDto{

    String cinematography;

    public FilmDto(Film film) {
        super();
        this.title= film.getTitle();
        this.cinematography= film.getPublisher();
        this.country=film.getCountry();
        this.author= film.getAuthor();
        this.releaseDate=film.getReleaseDate();
        // TODO Auto-generated constructor stub
    }



    public String getCinematography() {
        return cinematography;
    }



    public void setCinematography(String cinematography) {
        this.cinematography = cinematography;
    }



    public static List<FilmDto> convert(List<Film> films){
        List<FilmDto> convertedFilms = new ArrayList<>();
        FilmDto film;
        for(Film f : films){
            film= new FilmDto(f);
            film.setType("film");
            convertedFilms.add(film);
        }
        return convertedFilms;
    }

}
