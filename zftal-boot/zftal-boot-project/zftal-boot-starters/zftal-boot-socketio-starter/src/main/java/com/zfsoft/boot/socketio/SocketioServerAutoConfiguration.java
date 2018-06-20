package com.zfsoft.boot.socketio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.handler.SuccessAuthorizationListener;
import com.corundumstudio.socketio.listener.DefaultExceptionListener;
import com.corundumstudio.socketio.listener.ExceptionListener;
import com.corundumstudio.socketio.store.MemoryStoreFactory;
import com.corundumstudio.socketio.store.StoreFactory;
import com.zfsoft.boot.socketio.hooks.SocketioServerShutdownHook;

@Configuration
@ConditionalOnProperty(prefix = SocketioServerProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ SocketioServerProperties.class })
public class SocketioServerAutoConfiguration {

	protected static Logger LOG = LoggerFactory.getLogger(SocketioServerAutoConfiguration.class);

	@Autowired
	private SocketioServerProperties config;
	
	@Bean
	@ConditionalOnMissingBean
	public AuthorizationListener socketAuthzListener() {
		AuthorizationListener authorizationListener = config.getAuthorizationListener();
		return authorizationListener == null ? new SuccessAuthorizationListener() : authorizationListener;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public ExceptionListener exceptionListener() {
		ExceptionListener exceptionListener = config.getExceptionListener();
		return exceptionListener == null ? new DefaultExceptionListener() : exceptionListener;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public StoreFactory clientStoreFactory() {
		StoreFactory storeFactory = config.getStoreFactory();
		return storeFactory == null ? new MemoryStoreFactory() : storeFactory;
	}
	
	@Bean
	public SocketIOServer socketIOServer(AuthorizationListener socketAuthzListener,
			ExceptionListener exceptionListener, StoreFactory clientStoreFactory) {

		// 身份验证
		config.setAuthorizationListener(socketAuthzListener);
		config.setExceptionListener(exceptionListener);
		config.setStoreFactory(clientStoreFactory);

		final SocketIOServer server = new SocketIOServer(config);
		
		/**
		 * 应用退出时，要调用shutdown来清理资源，关闭网络连接，注销自己
		 * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
		 */
		Runtime.getRuntime().addShutdownHook(new SocketioServerShutdownHook(server));
		
		server.start();
		
		return server;
	}

	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
		return new SpringAnnotationScanner(socketServer);
	}

}
