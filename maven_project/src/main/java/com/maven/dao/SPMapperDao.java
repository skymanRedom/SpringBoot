package com.maven.dao;

import java.util.Map;

import com.maven.model.UserEntity;

public interface SPMapperDao {
	
	String getUserName(Map<String,Object> para);
	
	Integer updateUser(UserEntity user);
}
