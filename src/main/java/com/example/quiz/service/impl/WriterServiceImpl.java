package com.example.quiz.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.quiz.constants.RtnCode;
import com.example.quiz.entity.Question;
import com.example.quiz.entity.Quiz;
import com.example.quiz.entity.Writer;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.repository.WriterDao;
import com.example.quiz.service.ifs.WriterService;
import com.example.quiz.vo.Answer;
import com.example.quiz.vo.Count;
import com.example.quiz.vo.QuizRes;
import com.example.quiz.vo.StatisticsRes;
import com.example.quiz.vo.WriterGetRes;
import com.example.quiz.vo.WriterReq;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WriterServiceImpl implements WriterService {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private QuizDao quizDao;

	@Autowired
	private WriterDao writerDao;

	@Override
	public QuizRes write(WriterReq req) {

		if (req.getQuizNum() <= 0 || StringUtils.hasText(req.getName())) {
			return new QuizRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage());
		}

		if (StringUtils.hasText(req.getAnswer())) {
			return new QuizRes(RtnCode.NO_QUESTION_ANSWER.getCode(), RtnCode.NO_QUESTION_ANSWER.getMessage());
		}

		// answer String translate to List<Answer>
		try {

			List<Answer> ansList = mapper.readValue(req.getAnswer(), new TypeReference<List<Answer>>() {
			});

			Optional<Quiz> op = quizDao.findById(req.getQuizNum());
			if (op.isEmpty()) {
				return new QuizRes(RtnCode.QUIZ_NOT_FOUND.getCode(), RtnCode.QUIZ_NOT_FOUND.getMessage());
			}
			Quiz quiz = op.get();
			String questionStr = quiz.getQuestionStr();
			List<Question> questionList = mapper.readValue(questionStr, new TypeReference<List<Question>>() {
			});
			// �z������Ƿ��д�
			for (Question qu : questionList) {
				for (Answer item : ansList) {// ���}��̖, �x� -->�x헶����r��,����
					if (qu.getNum() == item.getqNum() && qu.isNecessary()
							&& CollectionUtils.isEmpty(item.getOptionList())) {
						return new QuizRes(RtnCode.NO_QUESTION_ANSWER.getCode(),
								RtnCode.NO_QUESTION_ANSWER.getMessage());
					}
				}
			}

		} catch (Exception e) {
			return new QuizRes(RtnCode.NO_QUESTION_ANSWER.getCode(), RtnCode.NO_QUESTION_ANSWER.getMessage());
		}
		writerDao.save(new Writer(req));
		return new QuizRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());

	}

	@Override
	public WriterGetRes findByQuizNum(int quizNum) {
		if (quizNum <= 0) {
			return new WriterGetRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage(), null);
		}
		List<Writer> res = writerDao.findByQuizNumOrderByNumDesc(quizNum);
		return new WriterGetRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), res);
	}

	@Override
	public StatisticsRes count(int quizNum) {

		if (quizNum <= 0) {
			return new StatisticsRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage(), quizNum, null);
		}
		List<Writer> res = writerDao.findByQuizNumOrderByNumDesc(quizNum);
		// �a�Q���}��̖�c�x�List�cMap
		Map<Integer, List<String>> questionOptionMap = new HashMap<>();

		for (Writer item : res) {
			// [{"qNum":1,"optionList":["aaa"]},{"qNum":2,"optionList":["bbb"]},{"qNum":3,"optionList":["ccc"]}]
			String answerStr = item.getAnswer();
			try {
				List<Answer> ansList = mapper.readValue(answerStr, new TypeReference<List<Answer>>() {
				});

				for (Answer ans : ansList) {
					if (questionOptionMap.containsKey(ans.getqNum())) {
						// ��key ������ valueȡ��
						List<String> linsInMap = questionOptionMap.get(ans.getqNum());
						// ��ans �� ��optionlist ���ӵ�ԭ�����ڵ� map �е� option list
						linsInMap.addAll(ans.getOptionList());
						questionOptionMap.put(ans.getqNum(), linsInMap);
					} else {
						questionOptionMap.put(ans.getqNum(), ans.getOptionList());
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		//Ӌ��ÿ�����}��ÿ���x헴Δ�
		List<Count> countList = new ArrayList<>();
		//			<���}��̖,�x헵� List>
		for (Entry<Integer, List<String>> mapItem : questionOptionMap.entrySet()) {
			Count count = new Count();
			// Ӌ��ÿ�}ÿ���x헴Δ�
			// Map�x헡��Δ�
			Map<String, Integer> optionCountMap = new HashMap<>();
			for (String str : mapItem.getValue()) {
				if (questionOptionMap.containsKey(str)) {
					int oldCount = optionCountMap.get(str);
					oldCount++;
					optionCountMap.put(str, oldCount);
				} else {
					optionCountMap.put(str, 1);
				}
			}
			count.setQuesionNum(mapItem.getKey());
			count.setOptionCountMap(optionCountMap);
			countList.add(count);
		}
		return new StatisticsRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), quizNum, countList);
	}

	
	
	public QuizRes write1(WriterReq req) {

		if (req.getQuizNum() <= 0 || StringUtils.hasText(req.getName())) {
			return new QuizRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage());
		}

		if (StringUtils.hasText(req.getAnswer())) {
			return new QuizRes(RtnCode.NO_QUESTION_ANSWER.getCode(), RtnCode.NO_QUESTION_ANSWER.getMessage());
		}

		// answer String translate to Map
		try {
			//[{"qNum":1,"optionList":["aaa"]},{"qNum":2,"optionList":["bbb"]},{"qNum":3,"optionList":["aaa"]}]

			Map<String, String> map = mapper.readValue(req.getAnswer(), Map.class);

			Optional<Quiz> op = quizDao.findById(req.getQuizNum());
			if (op.isEmpty()) {
				return new QuizRes(RtnCode.QUIZ_NOT_FOUND.getCode(), RtnCode.QUIZ_NOT_FOUND.getMessage());
			}
			Quiz quiz = op.get();
			String questionStr = quiz.getQuestionStr();
			List<Question> questionList = mapper.readValue(questionStr, new TypeReference<List<Question>>() {
			});
			// �z������Ƿ��д�
			for (Question qu : questionList) {// 1

				for (Entry<String, String> item : map.entrySet()) {// ���}��̖, �x� -->�x헶����r��,����
					if (String.valueOf(qu.getNum()).equals(item.getKey()) && qu.isNecessary()
							&& !StringUtils.hasText(item.getValue())) {
						return new QuizRes(RtnCode.NO_QUESTION_ANSWER.getCode(),
								RtnCode.NO_QUESTION_ANSWER.getMessage());
					}
				}
			}

		} catch (Exception e) {
			return new QuizRes(RtnCode.NO_QUESTION_ANSWER.getCode(), RtnCode.NO_QUESTION_ANSWER.getMessage());
		}
		writerDao.save(new Writer(req));
		return new QuizRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	public StatisticsRes count1(int quizNum) {

		if (quizNum <= 0) {
			return new StatisticsRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage(), quizNum, null);
		}
		List<Writer> res = writerDao.findByQuizNumOrderByNumDesc(quizNum);
		// �a�Q���}��̖�c�x�List�cMap
		Map<String, List<String>> questionOptionMap = new HashMap<>();

		for (Writer item : res) {
			String answerStr = item.getAnswer();
			try {
				// ���}��̖, �x� -->�x헶����r��,����
				Map<String, String> answerMap = mapper.readValue(answerStr, Map.class);

				// ���}ȥ�հ���,�з�
				for (Entry<String, String> mapItem : answerMap.entrySet()) {
					String valueStr = mapItem.getValue();// "�x�1, �x�2, �x�3"
					valueStr = StringUtils.trimAllWhitespace(valueStr);// ȥ�հף�"�x�1,�x�2,�x�3"
					String[] array = valueStr.split(",");// [�x�1, �x�2, �x�3]
					List<String> optionList = new ArrayList<>();
					optionList.addAll(Arrays.asList(array));
					// ����ǰ���лش��� questionOptionMapȡ��
					List<String> listInMap = questionOptionMap.get(mapItem.getKey());
					// ��һ�����M��r questiuonOptionMap �]�|�� listMap=null
					// listInMap=null �rlistInMap.allAll(optionList)�͕����e
					if (listInMap == null) {
						listInMap = new ArrayList<>();
					}

					listInMap.addAll(optionList);
					// �����ٌ���ǰ+��ǰ�ش��ߵĽY���Ż�questionOptionMap
					questionOptionMap.put(mapItem.getKey(), listInMap);
					// Map : ��ͬ��key�����value���wǰ
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return new StatisticsRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), quizNum, null);
	}

}
