package com.example.quiz.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.quiz.constants.RtnCode;
import com.example.quiz.entity.Question;
import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.service.ifs.QuizService;
import com.example.quiz.vo.QuizGetRes;
import com.example.quiz.vo.QuizRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuizServiceImpl implements QuizService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	// ﹃籔ン(摸)が锣
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired // 巨dao(计沮砐拜糷)
	private QuizDao quizDao;

	@Override
	public QuizRes create(String name, String description, LocalDate startDate, LocalDate endDate,
			List<Question> questionList, boolean published) {
		// 耞把计琌
		if (!StringUtils.hasText(name) || !StringUtils.hasText(description) || startDate == null || endDate == null) {
			return new QuizRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage());
		}
//		// 絋粄 List 琌
//		// す砛ミ拜戈τぃミ拜拜肈
//		if (!CollectionUtils.isEmpty(questionList)) {
//			// 璝玥禲┏よ猭checkQuestionfor癹伴
//			QuizRes checkResult = checkQuestion(questionList);
//			if (checkResult != null) {
//				// 璝 checkResult != null listぃ┪柑戈惠肚rtnCode
//				return checkResult;
//			}
//		}
		// 耞秨﹍丁琌Ν挡丁
		if (startDate.isAfter(endDate)) {
			return new QuizRes(RtnCode.DATE_FORMAT_ERROR.getCode(), RtnCode.DATE_FORMAT_ERROR.getMessage());
		}
		// 璝pkぃ玥穝糤 璝玥穝
		try { // try 璶暗ㄆ
			String str = mapper.writeValueAsString(questionList);
			quizDao.save(new Quiz(name, description, startDate, endDate, str, published));
			return new QuizRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
			// catch 锣传岿
		} catch (JsonProcessingException e) {
			return new QuizRes(RtnCode.QUIZ_CREATE_ERROR.getCode(), RtnCode.QUIZ_CREATE_ERROR.getMessage());
		}
	}

	private QuizRes checkQuestion(List<Question> questionList) {
//		// 絋粄 List 琌
//		// List  Collection(栋)
//		if (CollectionUtils.isEmpty(questionList)) {
//			return new QuizRes(RtnCode.QUESTION_IS_EMPTY.getCode(), RtnCode.QUESTION_IS_EMPTY.getMessage());
//		}
		// ノ foreach 絋粄 questionList 柑戈琌
		for (Question item : questionList) {
			if (item.getNum() == 0 || !StringUtils.hasText(item.getTitle()) || !StringUtils.hasText(item.getType())) {
				return new QuizRes(RtnCode.QUESTION_PARAM_ERROR.getCode(), RtnCode.QUESTION_PARAM_ERROR.getMessage());
			}
		}
		return null;
	}

	@Override
	// 有值就改沒值不改
	public QuizRes update(int num, String name, String description, LocalDate startDate, LocalDate endDate,
			List<Question> questionList, boolean published) {

		if (num <= 0) {
			return new QuizRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage());
		}
		if (!CollectionUtils.isEmpty(questionList)) {
			// 璝玥禲┏よ猭checkQuestionfor癹伴
			QuizRes checkResult = checkQuestion(questionList);
			if (checkResult != null) {
				// 璝 checkResult != null listぃ┪柑戈惠肚rtnCode
				return checkResult;
			}
		}
		Optional<Quiz> op = quizDao.findById(num);
		if (op.isEmpty()) {
			return new QuizRes(RtnCode.QUIZ_NOT_FOUND.getCode(), RtnCode.QUIZ_NOT_FOUND.getMessage());
		}
		Quiz quiz = op.get();
		// 已發布且開始(當前時間>開始時間)不能更新
		if (quiz.isPublished() && LocalDate.now().isAfter(quiz.getStartDate())) {
			return new QuizRes(RtnCode.QUIZ_CANNOT_UIPDATE.getCode(), RtnCode.QUIZ_CANNOT_UIPDATE.getMessage());
		}

		if (StringUtils.hasText(name)) {
			quiz.setName(name);
		}
		if (StringUtils.hasText(description)) {
			quiz.setDescription(description);
		}
		if (startDate.isAfter(endDate)) {
			return new QuizRes(RtnCode.DATE_FORMAT_ERROR.getCode(), RtnCode.DATE_FORMAT_ERROR.getMessage());
		}
		if (startDate != null) {
			quiz.setStartDate(startDate);
		}
		if (endDate != null) {
			quiz.setEndDate(endDate);
		}
		quiz.setPublished(published);
		try {
			String str = mapper.writeValueAsString(questionList);
			quiz.setquestionStr(str);
			quizDao.save(new Quiz(num, name, description, startDate, endDate, str, published));
		} catch (JsonProcessingException e) {
			return new QuizRes(RtnCode.QUIZ_CREATE_ERROR.getCode(), RtnCode.QUIZ_CREATE_ERROR.getMessage());
		}
		return new QuizRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public QuizRes deleteQuiz(List<Integer> numList) {

		if (CollectionUtils.isEmpty(numList)) {
			return new QuizRes(RtnCode.QUIZ_ID_LIST_IS_EMPTY.getCode(), RtnCode.QUIZ_ID_LIST_IS_EMPTY.getMessage());
		}
		List<Quiz> quizList = quizDao.findAllById(numList);
		for (Quiz item : quizList) {
			if (item.isPublished() && LocalDate.now().isAfter(item.getStartDate())) {
				return new QuizRes(RtnCode.QUIZ_CANNOT_UIPDATE.getCode(), RtnCode.QUIZ_CANNOT_UIPDATE.getMessage());
			}
		}
		quizDao.deleteByNumIn(numList);
		return new QuizRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public QuizRes deleteQuestion(int quizNum, List<Integer> numList) {
		if (quizNum <= 0 || CollectionUtils.isEmpty(numList)) {
			return new QuizRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage());
		}
		Optional<Quiz> op = quizDao.findById(quizNum);
		if (op.isEmpty()) {
			return new QuizRes(RtnCode.QUIZ_NOT_FOUND.getCode(), RtnCode.QUIZ_NOT_FOUND.getMessage());
		}
		Quiz quiz = op.get();
		String questionStr = quiz.getQuestionStr();
		try {
			List<Question> questionList = mapper.readValue(questionStr, new TypeReference<List<Question>>() {
			});

			List<Question> retainList = new ArrayList<>();
			int index = 1;
			for (Question item : questionList) {
				// numList為欲刪除清單 不在清單內的添加進新的清單
				if (!numList.contains(item.getNum())) {
					item.setNum(index);
					retainList.add(item);
					index++;
				}
			}
			String retainListStr = mapper.writeValueAsString(retainList);
			quiz.setquestionStr(retainListStr);
			quizDao.save(quiz);
		} catch (Exception e) {
			return new QuizRes(RtnCode.QUESTION_DELETE_ERROR.getCode(), RtnCode.QUESTION_DELETE_ERROR.getMessage());
		}
		quizDao.save(quiz);
		return null;
	}
//
//	@Override
//	public QuizGetRes getAll() {
//		return new QuizGetRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), quizDao.findAll());
//	}

	// 當三個條件皆為null時就是搜尋全部
	@Override
	public QuizGetRes search(String quizName, LocalDate startDate, LocalDate endDate, boolean islogin) {

		quizName = !StringUtils.hasText(quizName) ? "" : quizName;
		startDate = startDate == null ? LocalDate.of(1970, 01, 01) : startDate;
		endDate = endDate == null ? LocalDate.of(2099, 12, 31) : endDate;

		List<Quiz> res = islogin ? quizDao.findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(quizName, startDate, endDate)
				: quizDao.findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqualAndPublishedTrue(quizName, startDate, endDate);
		return new QuizGetRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), res);
	}
}
