package com.example.quiz.entity;

import java.io.Serializable;

// �] Question1 @Id ����� �A�ҥH�ݥ� Question1Id �N @Id ���زΤ@�޲z

@SuppressWarnings("serial")
public class Question1Id implements Serializable {
	// �� implements Serializable �갵�ǦC��
	// Question1Id �X�{���L�C�A�e�T�ӿﶵ�ҥi��

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
