package com.maven.mybatisConfig;

import java.util.Properties;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MyBatisMapperScannerConfig {
	@Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
		
		System.out.println("================");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.maven.dao");
        Properties properties = new Properties();//设置通用mapper的属性
        // 这里要特别注意，不要把MyMapper放到 basePackage 中，也就是不能同其他Mapper一样被扫描到。 
        //properties.setProperty("mappers", MyMapper.class.getName());
        //properties.setProperty("notEmpty", "false");
        //properties.setProperty("IDENTITY", "MYSQL");
        //mapperScannerConfigurer.setProperties(properties);
        
        return mapperScannerConfigurer;
    }
}
