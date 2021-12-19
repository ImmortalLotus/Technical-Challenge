package com.example.library.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.library.model.Book;

public class BookDto extends ArtWorksDto{

	String publisher;
	
	public BookDto(Book book) {
		super();
        this.title= book.getTitle();
        this.publisher= book.getPublisher();
        this.country=book.getCountry();
        this.author= book.getAuthor();
        this.releaseDate=book.getReleaseDate();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getPublisher() {
		return publisher;
	}



	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}



	public static List<BookDto> convert(List<Book> books){
    	List<BookDto> convertedBooks = new ArrayList<>();
        BookDto book;
        for(Book b : books){
            book= new BookDto(b);
            book.setType("book");
            convertedBooks.add(book);
        }
        return convertedBooks;
    }

}
