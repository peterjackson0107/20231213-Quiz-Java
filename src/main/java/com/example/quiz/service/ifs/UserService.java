package com.example.quiz.service.ifs;

import com.example.quiz.vo.UserLoginRes;

public interface UserService {

	// 介面無實作方法 須將實作方法寫在其他class
	public UserLoginRes login(String account, String pwd);
	// UserLoginRes 將兩個不同資料型態的屬性回傳至一個物件內

	// 寫法ex
	// public int calculateTwoNumber( int x , int y )
	// int為回傳之資料型態
	
	public UserLoginRes create(String account, String pwd);
}
