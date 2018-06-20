package com.zfsoft.boot.shiro;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.spring.web.config.AbstractShiroWebConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.pac4j.cas.config.CasConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.subject.Pac4jSubjectFactory;

@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration",  // shiro-spring-boot-web-starter
	"com.zfsoft.boot.shiro.ShiroCasWebAutoConfiguration", // zftal-boot-shiro-cas-starter
	"com.zfsoft.boot.shiro.ShiroBizWebAutoConfiguration" // zftal-boot-shiro-starter
})
@ConditionalOnWebApplication
@ConditionalOnClass({ CallbackFilter.class, SecurityFilter.class, SecurityFilter.class, CasConfiguration.class })
@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jCasProperties.class, ShiroPac4jProperties.class, ShiroBizProperties.class, ServerProperties.class })
public class ShiroPac4jWebAutoConfiguration extends AbstractShiroWebConfiguration {
			
	@Autowired
	private ShiroBizProperties bizProperties;
	/**
	 * 责任链定义 ：定义Shiro的逻辑处理责任链
	 */
	@Bean
    @Override
	protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		Map<String /* pattert */, String /* Chain names */> pathDefinitions = bizProperties.getFilterChainDefinitionMap();
		if (MapUtils.isNotEmpty(pathDefinitions)) {
			chainDefinition.addPathDefinitions(pathDefinitions);
			return chainDefinition;
		}
		chainDefinition.addPathDefinition("/logout", "logout");
		chainDefinition.addPathDefinition("/**", "authc");
		return chainDefinition;
	}
	
	@Bean
	public SubjectFactory subjectFactory() {
		return new Pac4jSubjectFactory();
	}

}
