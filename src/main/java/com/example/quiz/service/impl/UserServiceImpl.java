package com.example.quiz.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.quiz.constants.RtnCode;
import com.example.quiz.entity.User;
import com.example.quiz.repository.UserDao;
import com.example.quiz.service.ifs.UserService;
import com.example.quiz.vo.UserLoginRes;

@Service // 記得加讓spring boot 託管
			// implements 讓此類別(UserServiceImpl)可去實作介面(UserService)
public class UserServiceImpl implements UserService {

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //因底下兩個方法都會使用到 故移到最上面 	

	@Autowired // 操作dao(數據訪問層) 有被託管就用這個
	private UserDao userDao;

	@Override // 實作方法 覆寫UserService裡的方法
	public UserLoginRes login(String account, String pwd) {
		// 判斷參數是否存在
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new UserLoginRes(RtnCode.PARAM_ERROR);
		}

//		User res = userDao.findByAccountAndPwd(account, pwd);
//		if (res == null) {
//			return; // 失敗
//		}
//		return; // 成功

		// 檢查帳號是否存在
		Optional<User> op = userDao.findById(account);
		// findById 為用 Optional 包住 User 去確認是否為 null
		if (op.isEmpty()) {
			return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND);
		}
		User user = op.get();
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// 確認密碼正確性
		if (!encoder.matches(pwd, user.getPwd())) {
			return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND);
		}

//		// 確認欄位內資料是否符合規範(不能為空)
//		boolean res = userDao.existsByAccountAndPwd(account, pwd);
//		if (!res) { // !res 等同於 res == false
//			return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND); // 失敗
//		}
		return new UserLoginRes(RtnCode.SUCCESSFUL); // 成功
	}

	@Override
	public UserLoginRes create(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new UserLoginRes(RtnCode.PARAM_ERROR);
		}

		// 檢查帳號是否已存在
		if (userDao.existsById(account)) {
			// ById 為抓有@Id的資料欄位
			return new UserLoginRes(RtnCode.ACCOUNT_EXISTED);
		}
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userDao.save(new User(account, encoder.encode(pwd)));
		// .encode 為編譯加密
		return new UserLoginRes(RtnCode.SUCCESSFUL);
	}

}
