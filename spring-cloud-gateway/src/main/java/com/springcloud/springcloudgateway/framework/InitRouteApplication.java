package com.springcloud.springcloudgateway.framework;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.springcloudgateway.config.RouteDefines;

@Component
public class InitRouteApplication implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(InitRouteApplication.class);
	
	@Autowired
	private RouteDefines routeDefines;

	@Autowired
	private DynamicRouteService routeService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Map<String, String> methods = routeDefines.getMethods();
		methods.values().stream().forEach(x -> {
			try {
				logger.info("配置文件读取的信息" + x);
				JSONObject jsonObject = JSONObject.parseObject(x);
				// 组装RouteDefinition
				RouteDefinition routeDefinition = getRouteDefinition(jsonObject);
				// 路由信息写入
				String save = routeService.add(routeDefinition);
				logger.info("result: " + save);
			} catch (Exception e) {
				logger.error("[路由初始化] 异常", e);
			}
		});

	}

	/**
	 * 组装RouteDefinition
	 * 
	 * @param jsonObject
	 * @return
	 * @throws URISyntaxException
	 */
	private RouteDefinition getRouteDefinition(JSONObject jsonObject) throws URISyntaxException {
		
		RouteDefinition routeDefinition = new RouteDefinition();
		routeDefinition.setId(jsonObject.getString("id"));
		List<PredicateDefinition> predicateList = getPredicateList(jsonObject);
		routeDefinition.setPredicates(predicateList);

		List<FilterDefinition> filterDefinition1 = getFilterDefinition(jsonObject);
		routeDefinition.setFilters(filterDefinition1);

		URI uri = new URI(jsonObject.getString("uri"));
		routeDefinition.setUri(uri);
		routeDefinition.setOrder(jsonObject.getIntValue("order"));
		return routeDefinition;
	}

	/**
	 * 解析json 获得PredicateList
	 * 
	 * @param jsonObject
	 * @return
	 */
	private List<PredicateDefinition> getPredicateList(JSONObject jsonObject) {
		
		JSONArray predicateDefinition = jsonObject.getJSONArray("predicateDefinition");
		List<PredicateDefinition> predicates = new ArrayList<>();
		predicateDefinition.stream().forEach(predicate -> {
			JSONObject jsonObject1 = JSONObject.parseObject(predicate.toString());
			PredicateDefinition definition = new PredicateDefinition();
			definition.setName(jsonObject1.getString("name"));
			definition.addArg(jsonObject1.getString("predicateKey"), jsonObject1.getString("predicateValue"));
			predicates.add(definition);

		});
		return predicates;
	}

	/**
	 * 解析json 获得FilterDefinitionList
	 * 
	 * @param jsonObject
	 * @return
	 */
	private List<FilterDefinition> getFilterDefinition(JSONObject jsonObject) {
		
		JSONArray predicateDefinition = jsonObject.getJSONArray("filterDefinition");
		List<FilterDefinition> predicates = new ArrayList<>();
		predicateDefinition.stream().forEach(predicate -> {
			JSONObject jsonObject1 = JSONObject.parseObject(predicate.toString());
			FilterDefinition definition = new FilterDefinition();
			definition.setName(jsonObject1.getString("name"));
			definition.addArg(jsonObject1.getString("filterKey"), jsonObject1.getString("filterValue"));
			predicates.add(definition);

		});
		return predicates;
	}

}
