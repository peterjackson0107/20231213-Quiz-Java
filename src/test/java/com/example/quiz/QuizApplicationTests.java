package com.example.quiz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.quiz.service.ifs.UserService;

@SpringBootTest
public class QuizApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void userCreate() {
		userService.create("A01", "AA123");
	}

}
