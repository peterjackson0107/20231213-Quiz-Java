package com.example.movie.service.ifs;

import com.example.movie.vo.UserLoginRes;

public interface UserService {
	
	public UserLoginRes login(String account,String pwd);
	
	public UserLoginRes create(String account,String pwd,String email,int phone, String name);

}
