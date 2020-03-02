package com.itmuch.cloud.controller;

import java.sql.Connection;
import java.util.Optional;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.google.common.collect.Lists;
import com.itmuch.cloud.entity.Users;
import com.itmuch.cloud.repository.UserRepository;
//import com.netflix.appinfo.InstanceInfo;
//import com.netflix.discovery.EurekaClient;

@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

	/*
	 * @Autowired private EurekaClient eurekaClient;
	 * 
	 * @Autowired private DiscoveryClient discoveryClient;
	 */
  @Resource(name="jndiDataSource")
  private DataSource jndiDataSource;
  
  @GetMapping("/simple/{id}")
  public Optional<Users> findById(@PathVariable Long id) throws Exception {
	  
	  //need to check this ds is jndi??
	  //InitialContext initialContext = new InitialContext();
	  //DataSource datasource = (DataSource) initialContext.lookup("java:comp/env/jdbc/mysql");
	  System.out.println("======"+jndiDataSource);
	  Connection conn = jndiDataSource.getConnection();
	  System.out.println("======"+conn.getMetaData().getDatabaseProductName());
	  return this.userRepository.findById(id);
  }

	/*
	 * @GetMapping("/eureka-instance") public String serviceUrl() { InstanceInfo
	 * instance =
	 * this.eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER",
	 * false); return instance.getHomePageUrl(); }
	 */

	/*
	 * @GetMapping("/instance-info") public ServiceInstance showInfo() {
	 * ServiceInstance localServiceInstance =
	 * this.discoveryClient.getLocalServiceInstance(); return localServiceInstance;
	 * }
	 */

  @PostMapping("/user")
  public Users postUser(@RequestBody Users user) {
    return user;
  }

}
