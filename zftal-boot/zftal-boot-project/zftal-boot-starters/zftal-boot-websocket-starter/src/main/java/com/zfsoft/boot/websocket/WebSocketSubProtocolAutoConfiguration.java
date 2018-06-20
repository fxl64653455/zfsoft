/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.websocket;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;
import org.springframework.web.socket.messaging.SubProtocolHandler;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

@Configuration
@AutoConfigureBefore( name = {
	"com.zfsoft.boot.websocket.WebSocketAutoConfiguration",
	"com.zfsoft.boot.websocket.WebSocketBrokerAutoConfiguration"
})
@ConditionalOnWebApplication
@ConditionalOnClass({ SubProtocolHandler.class })
@ConditionalOnProperty(prefix = WebSocketProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ WebSocketProperties.class })
public class WebSocketSubProtocolAutoConfiguration implements ApplicationContextAware, InitializingBean {

	private ApplicationContext applicationContext;
	
	@Bean
	@ConditionalOnMissingBean
	public StompSubProtocolErrorHandler stompErrorHandler() {
		return new StompSubProtocolErrorHandler();
	}

	@Bean
	@ConditionalOnMissingBean
	public SubProtocolHandler subProtocolHandler(StompSubProtocolErrorHandler stompErrorHandler) {
		StompSubProtocolHandler subProtocolHandler = new StompSubProtocolHandler();
		subProtocolHandler.setErrorHandler(stompErrorHandler);
		return subProtocolHandler;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
	
		Map<String, SubProtocolHandler> beansOfSubProtocol = getApplicationContext().getBeansOfType(SubProtocolHandler.class);
		Map<String, SubProtocolWebSocketHandler> beansOfHandler = getApplicationContext().getBeansOfType(SubProtocolWebSocketHandler.class);
		if (!ObjectUtils.isEmpty(beansOfHandler)) {
			Iterator<Entry<String, SubProtocolWebSocketHandler>> ite = beansOfHandler.entrySet().iterator();
			while (ite.hasNext()) {
				Entry<String, SubProtocolWebSocketHandler> entry = ite.next();
				Iterator<Entry<String, SubProtocolHandler>> iteProtocol = beansOfSubProtocol.entrySet().iterator();
				while (iteProtocol.hasNext()) {
					entry.getValue().addProtocolHandler(iteProtocol.next().getValue());
				}
			}
		}
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	
}
