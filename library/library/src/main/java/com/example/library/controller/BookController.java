package com.example.library.controller;

import com.example.filmConnection.FilmConnection;
import com.example.library.controller.dto.ArtWorksDto;
import com.example.library.controller.dto.BookDto;
import com.example.library.controller.form.BookForm;
import com.example.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/book")
public class BookController {
    private List<Book> books;

    @Bean
    public WebClient localApiClient() {
        return WebClient.create("localhost:8081");
    }

    



    @GetMapping
    public List<BookDto> list(){
        if(books ==null){
            books= new ArrayList<>();
            books.add(new Book("","","","", LocalDate.of(2021,12,10)));
            System.out.println("funfou");
            return BookDto.convert(books);
        }else{
            return BookDto.convert(books);
        }

    }

    @PostMapping
    public ResponseEntity<ArtWorksDto> insert(@RequestBody @Valid BookForm form, UriComponentsBuilder uriComponentsBuilder){
        if(books ==null)
            books= new ArrayList<>();
        Book book = form.convert();
        books.add(book);
        URI uri = uriComponentsBuilder.path("/book/{title}").buildAndExpand(book.getTitle()).toUri();
        ArtWorksDto newBook = new BookDto(book);
        newBook.setType("book");
        return ResponseEntity.created(uri).body(newBook);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<ArtWorksDto>> searchByName(@PathVariable String title){
        List<ArtWorksDto> foundBooks= new ArrayList<>();
        FilmConnection filmConn= new FilmConnection();
        ArtWorksDto foundBook =null;
        List<ArtWorksDto> foundFilms = filmConn.getFilms(title);
        for(Book book : books){
            if(book.getTitle().contains(title)){
            	foundBook=new BookDto(book);
            	foundBook.setType("book");
                foundBooks.add(foundBook);
                
            }
        }
        foundBooks.addAll(foundFilms);
        if(foundBooks.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(foundBooks);
    }
    

    @GetMapping("/apiConnect/{title}")
    public ResponseEntity<List<ArtWorksDto>> searchByNameApiConn(@PathVariable String title){
        List<ArtWorksDto> foundBooks= new ArrayList<>();
        ArtWorksDto foundBook = null;
        for(Book book : books){
            if(book.getTitle().contains(title)){
                foundBook= new BookDto(book);
                foundBook.setType("film");
                foundBooks.add(foundBook);
            }
        }
        if(foundBooks.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(foundBooks);
    }

    @PutMapping("/{title}")
    public ResponseEntity<ArtWorksDto> update(@PathVariable String title, @RequestBody @Valid BookForm form){
        ArtWorksDto updatedBook = null;
        for(Book book : books){
            if(book.getTitle().equals(title)){
                book=form.convert();
                updatedBook= new BookDto(book);
                updatedBook.setType("book");
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
        for(Book book : books){
            if(book.getTitle().equals(title)) {
                deletedBook = new BookDto(book);
                deletedBook.setType("book");
                books.remove(book);
            }
        }
        if(deletedBook!=null)
            return ResponseEntity.ok(deletedBook);
        return ResponseEntity.notFound().build();
    }
}
