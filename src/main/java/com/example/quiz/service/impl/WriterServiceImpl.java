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
			// 檢查必田是否有答案
			for (Question qu : questionList) {
				for (Answer item : ansList) {// 問題編號, 選項 -->選項多個時用,串接
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
		// 產稱問題編號與選項List與Map
		Map<Integer, List<String>> questionOptionMap = new HashMap<>();

		for (Writer item : res) {
			// [{"qNum":1,"optionList":["aaa"]},{"qNum":2,"optionList":["bbb"]},{"qNum":3,"optionList":["ccc"]}]
			String answerStr = item.getAnswer();
			try {
				List<Answer> ansList = mapper.readValue(answerStr, new TypeReference<List<Answer>>() {
				});

				for (Answer ans : ansList) {
					if (questionOptionMap.containsKey(ans.getqNum())) {
						// 把key 對應的 value取出
						List<String> linsInMap = questionOptionMap.get(ans.getqNum());
						// 將ans 中 的optionlist 應加到原本存在的 map 中的 option list
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
		//計算每個問題中每個選項次數
		List<Count> countList = new ArrayList<>();
		//			<問題編號,選項的 List>
		for (Entry<Integer, List<String>> mapItem : questionOptionMap.entrySet()) {
			Count count = new Count();
			// 計算每題每個選項次數
			// Map選項、次數
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
			// 檢查必田是否有答案
			for (Question qu : questionList) {// 1

				for (Entry<String, String> item : map.entrySet()) {// 問題編號, 選項 -->選項多個時用,串接
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
		// 產稱問題編號與選項List與Map
		Map<String, List<String>> questionOptionMap = new HashMap<>();

		for (Writer item : res) {
			String answerStr = item.getAnswer();
			try {
				// 問題編號, 選項 -->選項多個時用,串接
				Map<String, String> answerMap = mapper.readValue(answerStr, Map.class);

				// 問題去空白用,切分
				for (Entry<String, String> mapItem : answerMap.entrySet()) {
					String valueStr = mapItem.getValue();// "選項1, 選項2, 選項3"
					valueStr = StringUtils.trimAllWhitespace(valueStr);// 去空白："選項1,選項2,選項3"
					String[] array = valueStr.split(",");// [選項1, 選項2, 選項3]
					List<String> optionList = new ArrayList<>();
					optionList.addAll(Arrays.asList(array));
					// 將先前所有回答從 questionOptionMap取出
					List<String> listInMap = questionOptionMap.get(mapItem.getKey());
					// 第一個答案進來時 questiuonOptionMap 沒東西 listMap=null
					// listInMap=null 時listInMap.allAll(optionList)就會報錯
					if (listInMap == null) {
						listInMap = new ArrayList<>();
					}

					listInMap.addAll(optionList);
					// 最後再將先前+當前回答者的結果放回questionOptionMap
					questionOptionMap.put(mapItem.getKey(), listInMap);
					// Map : 相同的key會造成value後蓋前
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return new StatisticsRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), quizNum, null);
	}

}
