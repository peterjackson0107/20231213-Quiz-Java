package com.example.quiz.entity;

import java.io.Serializable;

// 因 Question1 @Id 有兩個 ，所以需生 Question1Id 將 @Id 項目統一管理

@SuppressWarnings("serial")
public class Question1Id implements Serializable {
	// 需 implements Serializable 實做序列化
	// Question1Id 出現黃蚯蚓，前三個選項皆可選

	private int num;

	private int quizNum;

	public Question1Id() {
		super();
	}

	public Question1Id(int num, int quizNum) {
		super();
		this.num = num;
		this.quizNum = quizNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getQuizNum() {
		return quizNum;
	}

	public void setQuizNum(int quizNum) {
		this.quizNum = quizNum;
	}

}
