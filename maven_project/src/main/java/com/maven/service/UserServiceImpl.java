package com.maven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maven.mapper.UserMapper;
import com.maven.model.UserEntity;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserMapper userMapper;
	

	@Override
	public List<UserEntity> getAll() {
		// TODO 自动生成的方法存根
		System.out.println(userMapper.getAll());
		return userMapper.getAll();
	}

}
