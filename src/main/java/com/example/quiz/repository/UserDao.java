package com.example.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.User;

@Repository // 讓spring boot託管
// < 對要做的entity名稱 , 有下@id的欄位資料型態 >
public interface UserDao extends JpaRepository<User, String> {
//找尋欄位內資料 方法命名須小駝峰
	public User findByAccountAndPwd(String account, String pwd);

//確認欄位內資料是否符合規範(不能為空)
	public boolean existsByAccountAndPwd(String account, String pwd);
}
