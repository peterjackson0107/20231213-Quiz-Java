package com.example.quiz.vo;

import java.util.List;
import java.util.Map;

public class Count {

	private int quesionNum;

	// ßxí—,´Î”µ
	private Map<String, Integer> optionCountMap;


	public Count() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Count(int quesionNum, Map<String, Integer> optionCountMap) {
		super();
		this.quesionNum = quesionNum;
		this.optionCountMap = optionCountMap;
	}

	public int getQuesionNum() {
		return quesionNum;
	}


	public void setQuesionNum(int quesionNum) {
		this.quesionNum = quesionNum;
	}


	public Map<String, Integer> getOptionCountMap() {
		return optionCountMap;
	}


	public void setOptionCountMap(Map<String, Integer> optionCountMap) {
		this.optionCountMap = optionCountMap;
	}

}
