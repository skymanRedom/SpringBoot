package com.springcloud.springcloudgateway.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixFallback {

	@RequestMapping(value = "/fallbackcontroller")
	public Map<String, String> fallBackController() {
		Map<String, String> res = new HashMap();
		res.put("code", "-100");
		res.put("data", "service not available");
		return res;
	}
	
	@PostMapping(value = "/fallbackcontroller")
	public Map<String, String> postFallBackController() {
		Map<String, String> res = new HashMap();
		res.put("code", "-100");
		res.put("data", "service not available");
		return res;
	}

}
