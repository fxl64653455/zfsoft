package com.zfsoft.boot.rocketmq;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(RocketmqPushEventHandlerDefinitionProperties.PREFIX)
public class RocketmqPushEventHandlerDefinitionProperties {
	
	public static final String PREFIX = RocketmqPushConsumerProperties.PREFIX + ".event";

	private String definitions = null;

    private Map<String /* ruleExpress */, String /* handler names */> definitionMap = new LinkedHashMap<String, String>();

	public String getDefinitions() {
		return definitions;
	}

	public void setDefinitions(String definitions) {
		this.definitions = definitions;
	}

	public Map<String, String> getDefinitionMap() {
		return definitionMap;
	}

	public void setDefinitionMap(Map<String, String> definitionMap) {
		this.definitionMap = definitionMap;
	}

	
    
    
}
