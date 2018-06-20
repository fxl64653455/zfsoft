package com.zfsoft.boot.cxf;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration 				// 配置控制
@SpringBootApplication
public class CxfApplication implements ApplicationRunner, CommandLineRunner {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(CxfApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}


}
