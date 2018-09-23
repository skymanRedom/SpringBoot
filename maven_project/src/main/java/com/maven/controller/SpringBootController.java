package com.maven.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maven.dao.SPMapperDao;
import com.maven.mapper.UserMapper;
import com.maven.model.UserEntity;
import com.maven.redis.RedisUtil;


@Controller
@RequestMapping("/springBoot")
public class SpringBootController {

	@Autowired
    private UserMapper userMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private SPMapperDao spMapperDao;
	private final Log logger = LogFactory.getLog(this.getClass());
	@RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    @ResponseBody
    public List<UserEntity> queryUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
	 
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("id", 1);
		para.put("p_out", "result");
		System.out.println("first spring boot mvc===" + spMapperDao.getUserName(para));
		logger.info("for test debug log4j");
		UserEntity user = new UserEntity();
		user.setId(1);
		user.setUserName("skyman8");
		user.setPassWord("poi90-");
		user.setNickName("skymanSpring1");
		redisUtil.set("skyman8", user);
		logger.info("get user from redis==" + redisUtil.get("skyman8"));
		System.out.println("first spring boot mvc===" + spMapperDao.updateUser(user));
		
 		return userMapper.getAll();
	 }
}
