package com.example.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.library.controller.BookController;

@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	private BookController controller;
	
	@Test
	void ensureThatControllerIsNotNull() throws Exception{
		assertThat(controller).isNotNull();
	}

}
