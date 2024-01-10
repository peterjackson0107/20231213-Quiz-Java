package com.example.movie.service.ifs;


import com.example.movie.vo.UserLoginRes;

public interface MypageService {
	
	public UserLoginRes create(String account,String favorit,String watchList, String accountMovieList);

	public UserLoginRes update(String account, String favorit, String watchList, String accountMovieList);
	
	public UserLoginRes search(String account);

}
