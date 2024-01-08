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

@Service // �O�o�[��spring boot �U��
			// implements �������O(UserServiceImpl)�i�h��@����(UserService)
public class UserServiceImpl implements UserService {

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //�]���U��Ӥ�k���|�ϥΨ� �G����̤W�� 	

	@Autowired // �ާ@dao(�ƾڳX�ݼh) ���Q�U�޴N�γo��
	private UserDao userDao;

	@Override // ��@��k �мgUserService�̪���k
	public UserLoginRes login(String account, String pwd) {
		// �P�_�ѼƬO�_�s�b
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new UserLoginRes(RtnCode.PARAM_ERROR);
		}

//		User res = userDao.findByAccountAndPwd(account, pwd);
//		if (res == null) {
//			return; // ����
//		}
//		return; // ���\

		// �ˬd�b���O�_�s�b
		Optional<User> op = userDao.findById(account);
		// findById ���� Optional �]�� User �h�T�{�O�_�� null
		if (op.isEmpty()) {
			return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND);
		}
		User user = op.get();
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// �T�{�K�X���T��
		if (!encoder.matches(pwd, user.getPwd())) {
			return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND);
		}

//		// �T�{��줺��ƬO�_�ŦX�W�d(���ର��)
//		boolean res = userDao.existsByAccountAndPwd(account, pwd);
//		if (!res) { // !res ���P�� res == false
//			return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND); // ����
//		}
		return new UserLoginRes(RtnCode.SUCCESSFUL); // ���\
	}

	@Override
	public UserLoginRes create(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new UserLoginRes(RtnCode.PARAM_ERROR);
		}

		// �ˬd�b���O�_�w�s�b
		if (userDao.existsById(account)) {
			// ById ���즳@Id��������
			return new UserLoginRes(RtnCode.ACCOUNT_EXISTED);
		}
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userDao.save(new User(account, encoder.encode(pwd)));
		// .encode ���sĶ�[�K
		return new UserLoginRes(RtnCode.SUCCESSFUL);
	}

}
