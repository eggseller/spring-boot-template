package com.bangahu.main.dao.master.sqlmapper;

import org.apache.ibatis.annotations.Mapper;

import com.bangahu.main.model.domain.User;

@Mapper
public interface UserMapper {
	User selectUser(String username);
//	Optional<User> selectUser(String username);
}
