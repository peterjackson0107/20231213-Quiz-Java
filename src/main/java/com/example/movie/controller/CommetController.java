package com.example.movie.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.service.ifs.CommentService;
import com.example.movie.vo.CommentReq;
import com.example.movie.vo.UserLoginRes;

@CrossOrigin(origins = "*")
@RestController
public class CommetController {
	
	@Autowired
	private CommentService commentService;
	

	@PostMapping(value = "movie/comment/create")
	public UserLoginRes create(@RequestBody CommentReq req) {
		return commentService.create(req.getMovie(),req.getCommentText());
	}
	
	@PostMapping(value = "movie/comment/createchild")
	public UserLoginRes createchild(@RequestBody CommentReq req) {
		return commentService.createchild(req.getCommentIndex(),req.getMovie(),req.getCommentText());
	}
	
	@PostMapping(value = "movie/comment/update")
	public UserLoginRes update(@RequestBody CommentReq req) {
				return commentService.update(req.getCommentIndex(),req.getCommentIndexOrder(),req.getMovie(),req.getCommentText());
	}
	
	@PostMapping(value = "movie/comment/delete")
	public UserLoginRes delete(@RequestBody CommentReq req) {
		return commentService.delete(req.getCommentIndex(),req.getCommentIndexOrder(),req.getMovie());
	}
	
	@PostMapping(value = "movie/comment/likeAndDislike")
	public UserLoginRes likeAndDislike(@RequestBody CommentReq req) {
				return commentService.likeAndDislike(req.getCommentIndex(),req.getCommentIndexOrder(),req.getMovie(),req.getLike(),req.getDislike());
	}

}
