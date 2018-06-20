package com.zfsoft.boot.pac4j;

import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.springframework.web.SecurityInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass({ SecurityInterceptor.class, CasConfiguration.class})
@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
public class Pac4jWebAutoConfiguration {
	
	
	
	
}
