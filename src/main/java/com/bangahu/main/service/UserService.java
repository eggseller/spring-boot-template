package com.bangahu.main.service;

import org.springframework.stereotype.Service;

import com.bangahu.main.dao.master.sqlmapper.UserMapper;
import com.bangahu.main.model.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserMapper userMapper;
	
	public User selectUser(String username) {
		User user = userMapper.selectUser(username);
		return user;
	}
}
