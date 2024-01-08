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

	// �r��P����(���O)����
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired // �ާ@dao(�ƾڳX�ݼh)
	private QuizDao quizDao;

	@Override
	public QuizRes create(String name, String description, LocalDate startDate, LocalDate endDate,
			List<Question> questionList, boolean published) {
		// �P�_�ѼƬO�_�s�b
		if (!StringUtils.hasText(name) || !StringUtils.hasText(description) || startDate == null || endDate == null) {
			return new QuizRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage());
		}
//		// �T�{ List �O�_�s�b
//		// ���\���إ߰ݨ���ơA�Ӥ��P�ɫإ߰ݨ����D
//		if (!CollectionUtils.isEmpty(questionList)) {
//			// �Y�s�b�h�h�]���U��kcheckQuestion��for�j��
//			QuizRes checkResult = checkQuestion(questionList);
//			if (checkResult != null) {
//				// �Y checkResult != null �N��list���s�b�θ̭���Ƭ��šA�ݦ^��rtnCode
//				return checkResult;
//			}
//		}
		// �P�_�}�l�ɶ��O�_���󵲧��ɶ�
		if (startDate.isAfter(endDate)) {
			return new QuizRes(RtnCode.DATE_FORMAT_ERROR.getCode(), RtnCode.DATE_FORMAT_ERROR.getMessage());
		}
		// �Ypk���s�b�h�s�W �Y�s�b�h��s
		try { // try ���n������
			String str = mapper.writeValueAsString(questionList);
			quizDao.save(new Quiz(name, description, startDate, endDate, str, published));
			return new QuizRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
			// catch ���ഫ�X����
		} catch (JsonProcessingException e) {
			return new QuizRes(RtnCode.QUIZ_CREATE_ERROR.getCode(), RtnCode.QUIZ_CREATE_ERROR.getMessage());
		}
	}

	private QuizRes checkQuestion(List<Question> questionList) {
//		// �T�{ List �O�_�s�b
//		// List ���@�� Collection(���X)
//		if (CollectionUtils.isEmpty(questionList)) {
//			return new QuizRes(RtnCode.QUESTION_IS_EMPTY.getCode(), RtnCode.QUESTION_IS_EMPTY.getMessage());
//		}
		// �� foreach �T�{ questionList �̸�ƬO�_����
		for (Question item : questionList) {
			if (item.getNum() == 0 || !StringUtils.hasText(item.getTitle()) || !StringUtils.hasText(item.getType())) {
				return new QuizRes(RtnCode.QUESTION_PARAM_ERROR.getCode(), RtnCode.QUESTION_PARAM_ERROR.getMessage());
			}
		}
		return null;
	}

	@Override
	// ��ֵ�͸ě]ֵ����
	public QuizRes update(int num, String name, String description, LocalDate startDate, LocalDate endDate,
			List<Question> questionList, boolean published) {

		if (num <= 0) {
			return new QuizRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage());
		}
		if (!CollectionUtils.isEmpty(questionList)) {
			// �Y�s�b�h�h�]���U��kcheckQuestion��for�j��
			QuizRes checkResult = checkQuestion(questionList);
			if (checkResult != null) {
				// �Y checkResult != null �N��list���s�b�θ̭���Ƭ��šA�ݦ^��rtnCode
				return checkResult;
			}
		}
		Optional<Quiz> op = quizDao.findById(num);
		if (op.isEmpty()) {
			return new QuizRes(RtnCode.QUIZ_NOT_FOUND.getCode(), RtnCode.QUIZ_NOT_FOUND.getMessage());
		}
		Quiz quiz = op.get();
		// �Ѱl�����_ʼ(��ǰ�r�g>�_ʼ�r�g)���ܸ���
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
				// numList�����h����� ������΃ȵ�����M�µ����
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

	// �������l���Ԟ�null�r�����ь�ȫ��
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
