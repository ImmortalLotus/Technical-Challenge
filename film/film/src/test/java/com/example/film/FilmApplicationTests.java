package com.example.film;

import com.example.film.controller.FilmController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class FilmApplicationTests {

	@Autowired
	private FilmController controller;

	@Test
	void ensureThatControllerWasCreated() throws Exception{
		assertThat(controller).isNotNull();

	}

}
