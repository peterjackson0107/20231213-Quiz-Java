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
//@Controller  ���}�o�̦۩w�q�@��Controller��{��~�������f
//@ResponseBody ���N��k����^�ȥH�S�w�榡(Json)�g�Jresponse��body�ϡA�i�ӱN�ƾڪ�^���Ȥ��

public class UserServiceController {

	@Autowired // �ާ@Service(�~���޿�h) ���Q�U�޴N�γo��
	private UserService userService;

	// value = "�~����URL" => ���postman��url��
	@PostMapping(value = "api/login")
	public UserLoginRes login(@RequestBody UserLoginReq req, HttpSession session) {
		// �N�ϥΪ̱b�K�Ǧ^Service�h���P�_RtnCode
		String attr = (String) session.getAttribute("account");
		//���˴_�Jsession�����YӍ�� ߀�_�Jsession�creq�Ў�̖�Ƿ�һ��
		if (StringUtils.hasText(attr) && attr.equals(req.getAccount())) {
			return new UserLoginRes(RtnCode.SUCCESSFUL);
		}
		UserLoginRes res = userService.login(req.getAccount(), req.getPwd());
		if (res.getRtnCode().getCode() == 200) {
			session.setAttribute("account", req.getAccount());
			// �O��session��Ч�r�g(s) session��Ч�A�O�r�g30min

			// session��Ч�r�g�O��10s
			session.setMaxInactiveInterval(10);
		}
		return res;
	}

	// ��ӬO���o�� :
	// public void login(@RequestBody UserLoginReq req){
	// �]�n�^�Ǩ�Service�h���P�_RtnCode �ҥH�����void �^�ǭȪ���ƫ��A��UserLoginRes
	// UserLoginRes res = userService.login(req.getAccount(), req.getPwd());
	// ���~���ϥΪ̿�J���b�K
	// return res;
	// �^�Ǩ�Service�h���P�_RtnCode
	// }

	@GetMapping(value = "api/logout")
	public UserLoginRes logout(HttpSession session) {
		// ׌sessionʧЧ
		session.invalidate();
		return new UserLoginRes(RtnCode.SUCCESSFUL);
	}
}
