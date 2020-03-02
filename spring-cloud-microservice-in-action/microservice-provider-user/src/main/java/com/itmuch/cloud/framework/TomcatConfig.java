package com.itmuch.cloud.framework;

import javax.sql.DataSource;
import javax.naming.NamingException;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class TomcatConfig {
	@Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
        	
            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
            	// for db connection pool configure 
            	//System.setProperty("javax.sql.DataSource.Factory","org.apache.commons.dbcp2.BasicDataSourceFactory");
            	System.setProperty("javax.sql.DataSource.Factory","com.zaxxer.hikari.HikariJNDIFactory");
            	
                tomcat.enableNaming();
                return super.getTomcatWebServer(tomcat);
            }
            
            @Override
            protected void postProcessContext(Context context) {
            	
                ContextResource resource = new ContextResource();
                //for BasicDataSourceFactory
                resource.setName("jdbc/mysql");
                resource.setType(DataSource.class.getName());
                resource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
                resource.setProperty("url", "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
                resource.setProperty("username", "root");
                resource.setProperty("password","mechipoderranen");
                
                //for HikariJNDIFactory
                resource.setProperty("jdbcUrl", "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
                resource.setProperty("dataSourceJndiName","jdbc/mysql");
                //resource.setProperty("javax.sql.DataSource.Factory", "");
                // validationQuery="SELECT 1 FROM DUAL
                context.getNamingResources().addResource(resource);
                
                super.postProcessContext(context);
            }
        };
        
        return tomcat;
    }
	
	@Bean
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        //bean.setBeanFactory(beanFactory);
        bean.setJndiName("java:/comp/env/jdbc/mysql");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        //bean.setResourceRef(true);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }
	
	/*
	 * @Bean public WebServerFactoryCustomizer<TomcatServletWebServerFactory>
	 * webServerFactoryCustomizer(){ return new
	 * WebServerFactoryCustomizer<TomcatServletWebServerFactory>(){
	 * 
	 * @Override public void customize(TomcatServletWebServerFactory factory) {
	 * factory.setPort(8084); } }; }
	 */
}
