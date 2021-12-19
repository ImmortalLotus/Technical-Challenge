package com.example.film.controller;

import com.example.film.bookConnection.BookConnection;
import com.example.film.controller.dto.ArtWorksDto;
import com.example.film.controller.dto.FilmDto;
import com.example.film.controller.form.FilmForm;
import com.example.film.model.Film;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {
    private List<Film> films;
    @Bean
    public WebClient localApiClient() {
        return WebClient.create("http://localhost:8080");
    }
    @GetMapping
    public List<FilmDto> list(){
        if(films ==null){
            films = new ArrayList<>();
            films.add(new Film("","","","", LocalDate.of(2021,12,10)));
            System.out.println("funfou");
            return FilmDto.convert(films);
        }else{
            return FilmDto.convert(films);
        }

    }

    @PostMapping
    public ResponseEntity<ArtWorksDto> insert(@RequestBody @Valid FilmForm form, UriComponentsBuilder uriComponentsBuilder){
        if(films ==null)
            films = new ArrayList<>();
        Film film = form.convert();
        films.add(film);
        URI uri = uriComponentsBuilder.path("/book/{title}").buildAndExpand(film.getTitle()).toUri();
        ArtWorksDto createdFilm= new FilmDto(film);
        createdFilm.setType("film");
        return ResponseEntity.created(uri).body(createdFilm);
    }

    @GetMapping("/apiConnect/{title}")
    public ResponseEntity<List<ArtWorksDto>> searchByNameApiConn(@PathVariable String title){
        List<ArtWorksDto> foundFilms= new ArrayList<>();
        ArtWorksDto foundFilm = null;
        for(Film film : films){
            if(film.getTitle().contains(title)){
                foundFilm= new FilmDto(film);
                foundFilm.setType("film");
                foundFilms.add(foundFilm);
            }
        }
        if(foundFilms.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(foundFilms);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<ArtWorksDto>> searchByName(@PathVariable String title){
        List<ArtWorksDto> foundFilms= new ArrayList<>();
        ArtWorksDto foundFilm = null;
        BookConnection bookConnection = new BookConnection();
        List<ArtWorksDto> foundBooks= (List<ArtWorksDto>) bookConnection.getBooks(title);
        for(Film film : films){
            if(film.getTitle().contains(title)){
                foundFilm= new FilmDto(film);
                foundFilm.setType("film");
                foundFilms.add(foundFilm);
            }
        }
        foundFilms.addAll(foundBooks);
        if(foundFilms.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(foundFilms);
    }

    @PutMapping("/{title}")
    public ResponseEntity<ArtWorksDto> update(@PathVariable String title, @RequestBody @Valid FilmForm form){
        ArtWorksDto updatedBook = null;
        for(Film film : films){
            if(film.getTitle().equals(title)){
                film =form.convert();
                updatedBook= new FilmDto(film);
                updatedBook.setType("film");
            }
        }
        if(updatedBook!=null)
            return ResponseEntity.ok(updatedBook);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<ArtWorksDto> delete(@PathVariable String title){
        ArtWorksDto deletedBook =null;
        for(Film film : films){
            if(film.getTitle().equals(title)) {
                deletedBook = new FilmDto(film);
                deletedBook.setType("film");
                films.remove(film);
            }
        }
        if(deletedBook!=null)
            return ResponseEntity.ok(deletedBook);
        return ResponseEntity.notFound().build();
    }
}
