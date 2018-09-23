package com.maven.redis;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private final Log logger = LogFactory.getLog(this.getClass());
	
	//可以把这个time，类似ehcache那样配置到xml文件中
	public boolean set(String key, Object value, long time) {

	     try {

	         if (time > 0) {

	             redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);

	         } else {

	             set(key, value);

	         }

	         return true;

	     } catch (Exception e) {

	    	 logger.error("set and expired time=",e);

	         return false;

	     }

	    }
	
	public boolean expire(String key, long time) {

		try {

			if (time > 0) {

				redisTemplate.expire(key, time, TimeUnit.SECONDS);

			}

			return true;

		} catch (Exception e) {

			logger.error("expire=",e);

			return false;

		}

	}

	public boolean set(String key, Object value) {

		try {

			redisTemplate.opsForValue().set(key, value);

			return true;

		} catch (Exception e) {

			logger.error("set object in redis=",e);

			return false;

		}

	}
	
	public Object get(String key) {

	      return key == null ? null : redisTemplate.opsForValue().get(key);

	    }
}
