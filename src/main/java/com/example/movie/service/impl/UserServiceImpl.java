package com.example.movie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.movie.constant.RtnCode;
import com.example.movie.entity.Comment;
import com.example.movie.entity.User;
import com.example.movie.repository.UserDAO;
import com.example.movie.service.ifs.UserService;
import com.example.movie.vo.UserLoginRes;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private UserDAO userDao;

    @Override
    public UserLoginRes login(String account, String pwd) {
        if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
        }
        Optional<User> op = userDao.findById(account);
        if (op.isEmpty()) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        User user = op.get();
        if (!encoder.matches(pwd, user.getPwd())) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
        //成功
    }

    @Override
    public UserLoginRes create(String account, String pwd, String email,int phone, String name) {
        if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
        }
        if (userDao.existsById(account)) {
            return new UserLoginRes(RtnCode.ACCOUNT_EXISTED.getCode(),RtnCode.ACCOUNT_EXISTED.getMessage());
        }
        String encodedPwd = encoder.encode(pwd);

        User newUser = new User();
        newUser.setAccount(account);
        newUser.setPwd(encodedPwd);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setName(name);

        userDao.save(newUser);

        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
    }


}
