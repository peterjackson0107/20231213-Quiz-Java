package com.example.quiz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.constants.RtnCode;
import com.example.quiz.service.ifs.UserService;
import com.example.quiz.vo.UserLoginReq;
import com.example.quiz.vo.UserLoginRes;

@RestController // @Controller + @ResponseBody
//@Controller  秨祇﹚竡Controller龟瞷癸场钡
//@ResponseBody 盢よ猭疭﹚Α(Json)糶responsebody跋秈τ盢计沮倒め狠

public class UserServiceController {

	@Autowired // 巨Service(穨叭呸胯糷) Τ砆癠恨碞ノ硂
	private UserService userService;

	// value = "钡URL" => 恶postmanurlノ
	@PostMapping(value = "api/login")
	public UserLoginRes login(@RequestBody UserLoginReq req, HttpSession session) {
		// 盢ㄏノ眀盞肚Service暗耞RtnCode
		String attr = (String) session.getAttribute("account");
		//除了確認session中有資訊外 還確認session與req中帳號是否一樣
		if (StringUtils.hasText(attr) && attr.equals(req.getAccount())) {
			return new UserLoginRes(RtnCode.SUCCESSFUL);
		}
		UserLoginRes res = userService.login(req.getAccount(), req.getPwd());
		if (res.getRtnCode().getCode() == 200) {
			session.setAttribute("account", req.getAccount());
			// 設定session有效時間(s) session有效預設時間30min

			// session有效時間設為10s
			session.setMaxInactiveInterval(10);
		}
		return res;
	}

	// ㄓ琌硂妓 :
	// public void login(@RequestBody UserLoginReq req){
	// 璶肚Service暗耞RtnCode ┮ぃノvoid 肚戈篈UserLoginRes
	// UserLoginRes res = userService.login(req.getAccount(), req.getPwd());
	// 钡场ㄏノ块眀盞
	// return res;
	// 肚Service暗耞RtnCode
	// }

	@GetMapping(value = "api/logout")
	public UserLoginRes logout(HttpSession session) {
		// 讓session失效
		session.invalidate();
		return new UserLoginRes(RtnCode.SUCCESSFUL);
	}
}
