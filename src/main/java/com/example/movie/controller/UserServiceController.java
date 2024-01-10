package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.service.ifs.UserService;
import com.example.movie.vo.UserLoginReq;
import com.example.movie.vo.UserLoginRes;

@CrossOrigin(origins = "*")
@RestController
public class UserServiceController {
	
	@Autowired
	private UserService userService;
	
	//
	@PostMapping(value = "/movie/user/login")
	public UserLoginRes login(@RequestBody UserLoginReq req) {
		return userService.login(req.getAccount(),req.getPwd());
	}
	
	@PostMapping(value = "movie/user/create")
	public UserLoginRes create(@RequestBody UserLoginReq req) {
		return userService.create(req.getAccount(),req.getPwd(),req.getEmail(),req.getPhone(),req.getName());
	}

}
