package com.example.quiz.service.ifs;

import com.example.quiz.vo.UserLoginRes;

public interface UserService {

	// �����L��@��k ���N��@��k�g�b��Lclass
	public UserLoginRes login(String account, String pwd);
	// UserLoginRes �N��Ӥ��P��ƫ��A���ݩʦ^�Ǧܤ@�Ӫ���

	// �g�kex
	// public int calculateTwoNumber( int x , int y )
	// int���^�Ǥ���ƫ��A
	
	public UserLoginRes create(String account, String pwd);
}
