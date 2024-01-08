package com.example.quiz.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteQuizReq {

	@JsonProperty("quiz_num_list")
	private List<Integer> numList;

	public List<Integer> getNumList() {
		return numList;
	}

	public void setNumList(List<Integer> numList) {
		this.numList = numList;
	}

	
}
