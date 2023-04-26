package com.sns.user.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.DAO.UserMapper;
import com.sns.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUserByLoginId(String loginId) {
		return userMapper.selectUserByLoginId(loginId);
	}
	
	public int addUser(String loginId, String password, String name, String email) {
		return userMapper.insertUser(loginId, password, name, email);
	}
	
	public User getUserByLoginIdPassword(String loginId, String Password) {
		return userMapper.selectUserByLoginIdPassword(loginId, Password);
	}
	
	public User getUserByid(int id) {
		return userMapper.selectUserByid(id);
	}
}
