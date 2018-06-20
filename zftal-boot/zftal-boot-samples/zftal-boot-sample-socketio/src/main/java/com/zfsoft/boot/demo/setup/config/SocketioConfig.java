package com.zfsoft.boot.demo.setup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.listener.ExceptionListener;
import com.zfsoft.boot.demo.setup.listener.SocketExceptionListener;

@Configuration
public class SocketioConfig {

	@Bean
	public ExceptionListener exceptionListener() {
		return new SocketExceptionListener();
	}
	
}
