package com.maven.service;

import java.util.List;

import com.maven.model.UserEntity;

public interface UserService {
	List<UserEntity> getAll();
}
