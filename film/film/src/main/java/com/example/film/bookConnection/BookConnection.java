package com.example.film.bookConnection;

import com.example.film.controller.dto.ArtWorksDto;
import com.example.film.controller.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookConnection {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    @Autowired
    private WebClient localApiClient=WebClient.create("http://localhost:8080");

    public BookConnection() {
    }

    public List<ArtWorksDto> getBooks(String title){
        Mono<List<BookDto>> response = localApiClient.get()
                .uri("/book/apiConnect/"+title)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<BookDto>>() {});
        List<BookDto> books = response.block();
        List<ArtWorksDto> genericBooks = new ArrayList<>();
        for(BookDto b : books) {
            genericBooks.add(b);
        }
        return genericBooks;
    }
}
