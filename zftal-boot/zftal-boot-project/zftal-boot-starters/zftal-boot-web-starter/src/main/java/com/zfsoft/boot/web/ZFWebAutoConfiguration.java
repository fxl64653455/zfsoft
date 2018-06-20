package com.zfsoft.boot.web;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.enhanced.context.SpringContextAwareContext;

/**
 * 
 * @className	： ZFWebAutoConfiguration
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月9日 下午12:09:08
 * @version 	V1.0
 */
@Configuration
@ImportResource(locations = { 
	"classpath*:conf/spring/config-*.xml", 
	"classpath*:conf/dataTable/config-*.xml",
	"classpath*:conf/cachekey/config-*.xml"
})
@EnableConfigurationProperties({ ZFWebProperties.class })
public class ZFWebAutoConfiguration {
	 
	@Bean("tableMapper")
	@ConditionalOnMissingBean(name = "tableMapper")
	public Map<String /* alias */, String /* table names */> tableMapper(ZFWebProperties properties) {
		return properties.getTableMap();
	}
	
	@Bean
	public SpringContextAwareContext springContextAwareContext() {
		return new SpringContextAwareContext();
	}
	
}
