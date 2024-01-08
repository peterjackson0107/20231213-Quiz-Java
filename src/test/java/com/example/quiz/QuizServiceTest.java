package com.example.quiz;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;

import com.example.quiz.entity.Question;
import com.example.quiz.service.ifs.QuizService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//@SpringBootTest
public class QuizServiceTest {

	@Autowired
	private QuizService quizService;

	@Test
	public void createQuizTest() {
		List<Question> list = Arrays.asList(new Question(1, "test1", "single", true, "AAA;BBB,CCC"),
				new Question(2, "test2", "single", false, "QQQ;WWW,EEE"));
		quizService.create("AAA", "BBB", LocalDate.now(), LocalDate.now().plusDays(1), list, false);
	}

	@Test
	public void objectMapperTest() {
		ObjectMapper mapper = new ObjectMapper();
		String str = "[{\"num\":2,\"title\":\"test2\",\"type\":\"single\",\"necessary\":false}]";
		try {
			List<Question> questionList = mapper.readValue(str, new TypeReference<List<Question>>() {
			});
			for (Question item : questionList) {
				System.out.println(item.getNum());
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void objectMapperTest2() {
		ObjectMapper mapper = new ObjectMapper();
		String str = "{\"num\":2,\"title\":\"test2\",\"type\":\"single\",\"necessary\":false}";
		try {
			Map<String, Object> res = mapper.readValue(str, Map.class);
			for (Entry<String, Object> item : res.entrySet()) {
				System.out.println("key:" + item.getKey());
				System.out.println("value:" + item.getValue());
			}
		} catch (Exception e) {

		}
	}
}
