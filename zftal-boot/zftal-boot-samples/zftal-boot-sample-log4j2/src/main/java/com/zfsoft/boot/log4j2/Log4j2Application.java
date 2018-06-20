package com.zfsoft.boot.log4j2;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zfsoft.boot.log4j2.utils.Log4jUtils;

@Configuration 				// 配置控制
@EnableScheduling
@EnableAutoConfiguration 	// 启用自动配置
@SpringBootApplication
public class Log4j2Application implements  ApplicationRunner, CommandLineRunner {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(Log4j2Application.class, args);

	}
	 

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Log4jUtils.debug(Markers.DB, "test1");
		
	}

	@Override
	public void run(String... args) throws Exception {
		Log4jUtils.debug(Markers.DB, "test2");
		 
	}


}
