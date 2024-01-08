package com.example.quiz.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.quiz.entity.Question;
import com.example.quiz.vo.QuizGetRes;
import com.example.quiz.vo.QuizRes;

public interface QuizService {

	// ³Ð³y°Ý¨÷
	public QuizRes create(String name, String description, LocalDate startDate, LocalDate endDate,
			List<Question> questionList, boolean published);

	public QuizRes update(int num, String name, String description, LocalDate startDate, LocalDate endDate,
			List<Question> questionList, boolean published);

	public QuizRes deleteQuiz(List<Integer> numList);

	public QuizRes deleteQuestion(int quizNUm, List<Integer> numList);

//	public QuizGetRes getAll();
	
	public QuizGetRes search(String quizName, LocalDate startDate, LocalDate endDate, boolean isLogin);

}
