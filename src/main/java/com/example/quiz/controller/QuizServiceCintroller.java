package com.example.quiz.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.constants.RtnCode;
import com.example.quiz.service.ifs.QuizService;
import com.example.quiz.vo.DeleteQuizReq;
import com.example.quiz.vo.QuizGetRes;
import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;
import com.example.quiz.vo.QuizSearchReq;

@CrossOrigin
@RestController
public class QuizServiceCintroller {

	@Autowired
	private QuizService quizService;

	@PostMapping(value = "/quiz/create")
	public QuizRes create(@RequestBody QuizReq req,HttpSession session) {
		String attr =(String)session.getAttribute("account");
		if (StringUtils.hasText(attr)) {
			return new QuizRes(RtnCode.PLEASE_LOGIN_FIRST.getCode(),RtnCode.PLEASE_LOGIN_FIRST.getMessage());
		}
		return quizService.create(req.getName(), req.getDescription(), req.getStartDate(), req.getEndDate(),
				req.getQuestionList(), req.isPublished());
	}

	// 因為使用 @RequestParam api的url是 quiz/update?quiz_num＝數字
	// @RequestParam 參數中定義value表示取到 quiz_num此字後面的值 若沒加預設變數名稱為num
	@PostMapping(value = "/quiz/update")
	public QuizRes update(@RequestParam(value = "quiz_num") int num, @RequestBody QuizReq req) {
		return quizService.update(num, req.getName(), req.getDescription(), req.getStartDate(), req.getEndDate(),
				req.getQuestionList(), req.isPublished());
	}

	@PostMapping(value = "/quiz/delete")
	public QuizRes deleteQuiz(@RequestBody DeleteQuizReq req) {
		return quizService.deleteQuiz(req.getNumList());
	}

	// 因為只有一個請求參數建議使用此方法
	// 因為@RequestParam參數為List uri是quiz/delete_quiz?quiz_num_list=100,200,300
	@PostMapping(value = "/quiz/delete_quiz")
	public QuizRes deleteQuiz(@RequestParam(value = "quiz_num_list") List<Integer> numList) {
		return quizService.deleteQuiz(numList);
	}

	// @RequestParam的參數有多個 要用＆串接
	// uri是quiz/delete_question?quiz_num=編號&question_num_list=編號1,編號2
	@PostMapping(value = "/quiz/delete_question")
	public QuizRes deleteQuewtion(@RequestParam(value = "quiz_num_list") int quizNum,
			@RequestParam(value = "quiz_num_list") List<Integer> numList) {
		return quizService.deleteQuestion(quizNum, numList);
	}

	// uri有多個參數quiz/delete_question?quiz_num=編號&question_num_list=編號1,編號2
	// @RequestParam用Map接 quiz_num=編號,quiz_num是map中的key,編號是map中的value,其餘以此類推
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/quiz/delete_question2")
	public QuizRes deleteQuewtion2(@RequestParam Map<String, Object> paramMap) {
		int quizNum = (int) paramMap.get("quizNum");
		List<Integer> numList = (List<Integer>) paramMap.get("question_num_list");
		return quizService.deleteQuestion(quizNum, numList);
	}

	@PostMapping(value = "/quiz/search")
	public QuizGetRes search(@RequestBody QuizSearchReq req) {
		return quizService.search(req.getQuizName(), req.getStartDat(), req.getEndDate(), req.isLogin());
	}

}
