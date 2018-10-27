package com.rabbit.sender;

import java.util.HashMap;
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

import com.rabbit.sender.HelloSender;


@Controller
@RequestMapping("/springRabbit")
public class SpringBootController {

	@Autowired
    private HelloSender helloSender;
	
	private final Log logger = LogFactory.getLog(this.getClass());
	@RequestMapping(value = "/rabbit", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
	 
		Map<String,Object> para = new HashMap<String,Object>();
		helloSender.send();
		para.put("success", "true");
 		return para;
	 }
}
