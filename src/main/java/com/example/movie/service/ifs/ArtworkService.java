package com.example.movie.service.ifs;

import com.example.movie.vo.UserLoginRes;

public interface ArtworkService {
	
	public UserLoginRes create(String movie,String account,String artname,String artDescription);

	public UserLoginRes update(int artorder,String artname,String artDescription);
	
	public UserLoginRes delete(int artorder);
	
	public UserLoginRes search(String movie,String artname);

}
