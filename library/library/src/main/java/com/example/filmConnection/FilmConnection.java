package com.example.filmConnection;

import java.io.Reader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.library.controller.dto.ArtWorksDto;
import com.example.library.controller.dto.FilmDto;
import com.example.library.model.Book;

import reactor.core.publisher.Mono;

@Service
public class FilmConnection {

		private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
	
	    private WebClient localApiClient=WebClient.create("http://localhost:8081");
	    public FilmConnection() {
	    }
	    
	    public List<ArtWorksDto> getFilms(String title){	    	
	    	Mono<List<FilmDto>> response = localApiClient.get()
	    				.uri("/film/apiConnect/"+title)
	    			  .accept(MediaType.APPLICATION_JSON)
	    			  .retrieve()
	    			  .bodyToMono(new ParameterizedTypeReference<List<FilmDto>>() {});
	    			List<FilmDto> films = response.block();
	    	List<ArtWorksDto> genericFilms = new ArrayList<>();		
	    	for(FilmDto f : films) {
	    		genericFilms.add(f);
	    	}
	    	return genericFilms;   	
	    }
}
