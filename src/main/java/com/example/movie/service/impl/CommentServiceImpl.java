package com.example.movie.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.movie.constant.RtnCode;
import com.example.movie.entity.Comment;
import com.example.movie.repository.CommentDAO;
import com.example.movie.service.ifs.CommentService;
import com.example.movie.vo.UserLoginRes;

@Service
public class CommentServiceImpl implements CommentService {
	
    @Autowired
    private CommentDAO commentDao;

    @Override
    public UserLoginRes create(String movie,String commentText) {
        if (!StringUtils.hasText(commentText)) {
            return new UserLoginRes(RtnCode.COMMENT_TEXT_IS_NONE.getCode(),RtnCode.COMMENT_TEXT_IS_NONE.getMessage());
        }
        Optional<Comment> op = commentDao.findAllByMovie(movie);
        Comment comment = null;
        if(op.isEmpty()) {
        	comment = commentDao.save(new Comment(movie, 1, commentText));
        } else {
        	comment = op.get();
            int x = comment.getCommentIndex() + 1 ;
            
            commentDao.save(new Comment(movie, x, commentText));
        }
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
    }
    
	@Override
	public UserLoginRes createchild(int commentIndex, String movie, String commentText) {
        if (!StringUtils.hasText(commentText)) {
            return new UserLoginRes(RtnCode.COMMENT_TEXT_IS_NONE.getCode(),RtnCode.COMMENT_TEXT_IS_NONE.getMessage());
        }
        Optional<Comment> op = commentDao.findTopByMovieAndCommentIndexOrderByCommentIndexOrderDesc(movie,commentIndex);
        Comment comment;
        
        if(op.isPresent()) {
        	comment = op.get();
        	int newCommentIndexOrder = comment.getCommentIndexOrder() + 1;
        	commentDao.save(new Comment(movie, commentIndex, newCommentIndexOrder, commentText));
        } else {
        	commentDao.save(new Comment(movie, commentIndex, 1 , commentText));
        }

        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
	}


	@Override
	public UserLoginRes update(int commentIndex,int commentIndexOrder, String movie, String commentText) {
		if(commentIndex ==0 || (commentIndex==0 && commentIndexOrder==0) || movie=="" || movie==null || commentText=="" || commentText==null) {
			return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
		}
		Optional<Comment> op = commentDao.findByCommentIndexAndCommentIndexOrderAndMovie(commentIndex,commentIndexOrder,movie);
		if (op.isEmpty()){
			return new UserLoginRes(RtnCode.MOVIE_COMMENT_NOT_FOUND.getCode(),RtnCode.MOVIE_COMMENT_NOT_FOUND.getMessage());
		}
		Comment comment = op.get();
		if(comment.getCommentIndex() != commentIndex) {
			return new UserLoginRes(RtnCode.MOVIE_COMMENT_NOT_FOUND.getCode(),RtnCode.MOVIE_COMMENT_NOT_FOUND.getMessage());
		}
		if(StringUtils.hasText(commentText)){
			comment.setCommentText(commentText);
			comment.setCommentTime(LocalDateTime.now());
		}
		try {
			commentDao.save(comment);
		} catch (Exception e) {
			return new UserLoginRes(RtnCode.PAGE_CREATE_ERROR.getCode(), RtnCode.PAGE_CREATE_ERROR.getMessage());
		}
		return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public UserLoginRes delete(int commentIndex,int commentIndexOrder,String movie) {
		int res = commentDao.deleteByCommentIndexAndCommentIndexOrderAndMovie(commentIndex,commentIndexOrder,movie);
		if(res == 0) {
			return new UserLoginRes(RtnCode.COMMENT_IS_NOT_EXSISTED.getCode(), RtnCode.COMMENT_IS_NOT_EXSISTED.getMessage());
		}else {
			return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
		}
		
	}

	@Override
	public UserLoginRes likeAndDislike(int commentIndex, int commentIndexOrder, String movie, int like, int dislike) {
        Optional<Comment> op = commentDao.findByCommentIndexAndCommentIndexOrderAndMovie(commentIndex,commentIndexOrder,movie);
        Comment comment = op.get();
        if(comment.getCommentIndex() != commentIndex) {
        	return new UserLoginRes(RtnCode.COMMENT_IS_NOT_EXSISTED.getCode(), RtnCode.COMMENT_IS_NOT_EXSISTED.getMessage());
        }
        if(comment.getCommentIndexOrder() !=commentIndexOrder) {
        	return new UserLoginRes(RtnCode.COMMENT_IS_NOT_EXSISTED.getCode(), RtnCode.COMMENT_IS_NOT_EXSISTED.getMessage());
        }
        if(like == 0 && dislike ==0) {
        	return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage());
        }
        
        int likeNew = comment.getFavorite() + like;
        int dislikeNew = comment.getDislike() + dislike;
        comment.setFavorite(likeNew);
        comment.setDislike(dislikeNew);
        commentDao.save(comment);
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

}
