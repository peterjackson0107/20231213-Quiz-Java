package com.example.quiz.constants;

// enum : �h�ӱ`�ƥi�H�Τ@�åΤ@�������i��޲z
public enum RtnCode {
//	�I�s���ɭԨϥΪ��ܼ� (code =>�������N�X, message =>�n��ܵ��ϥΪ̪��T��)
	SUCCESSFUL(200, "Successful~~~"), //
	PARAM_ERROR(400, "Param error~~"), //
	ACCOUNT_NOT_FOUND(404, "Account not found~~"), //
	DATE_FORMAT_ERROR(400, "Date format error~~"), //
	ACCOUNT_EXISTED(400, "Account existed~~"), //
	QUESTION_IS_EMPTY(400, "Question is empty~~"), //
	QUESTION_PARAM_ERROR(400, "Question param error~~"), //
	QUIZ_CREATE_ERROR(400, "Quiz create error~~"),
	QUIZ_NOT_FOUND(400,"Quiz not found~~"),
	QUIZ_CANNOT_UIPDATE(400,"Quiz cannot update~~"),
	QUIZ_ID_LIST_IS_EMPTY(400,"Quiz id list is empty~~"),
	QUESTION_DELETE_ERROR(400,"Question delete error"),
	NO_QUESTION_ANSWER(400,"No Question answer"),
	STRING_PASER_ERROR(400,"String paser error"),
	PLEASE_LOGIN_FIRST(400,"Please login first");
	private int code;

	private String message;

	private RtnCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	// �u��get ����set
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
