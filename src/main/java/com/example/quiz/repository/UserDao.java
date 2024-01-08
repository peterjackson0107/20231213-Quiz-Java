package com.example.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.User;

@Repository // ��spring boot�U��
// < ��n����entity�W�� , ���U@id������ƫ��A >
public interface UserDao extends JpaRepository<User, String> {
//��M��줺��� ��k�R�W���p�m�p
	public User findByAccountAndPwd(String account, String pwd);

//�T�{��줺��ƬO�_�ŦX�W�d(���ର��)
	public boolean existsByAccountAndPwd(String account, String pwd);
}
