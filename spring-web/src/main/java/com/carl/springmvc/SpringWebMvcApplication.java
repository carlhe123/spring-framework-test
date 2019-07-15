package com.carl.springmvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 配置mybatis需要有DataSource故注释掉
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication
@MapperScan("com.carl.springmvc.mapper")
public class SpringWebMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebMvcApplication.class, args);
	}
}
