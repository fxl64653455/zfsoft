package com.zfsoft.boot.freemarker;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * 
 * @className ： FreemarkerTaglibAutoConfiguration
 * @description ： Freemarker 整合内嵌模式下的Jstl标签
 * @author ：万大龙（743）
 * @date ： 2018年3月8日 上午9:52:32
 * @version V1.0
 */
@Configuration
@ConditionalOnClass({ freemarker.template.Configuration.class, FreeMarkerConfigurer.class })
@ConditionalOnProperty(prefix = "spring.freemarker", value = "enabled", havingValue = "true")
@AutoConfigureAfter({ org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration.class })
@EnableConfigurationProperties({ FreemarkerTaglibProperties.class })
public class FreemarkerTaglibAutoConfiguration {

	private static final String ZFTAL_SEARCH_TLD = "/META-INF/zftal-search.tld";

	@Autowired
	private FreemarkerTaglibProperties properties;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@PostConstruct
	public void loadClassPathTlds() {

		List<String> classPathTlds = properties.getClassPathTlds();
		if (CollectionUtils.isEmpty(classPathTlds)) {
			classPathTlds = Arrays.asList(ZFTAL_SEARCH_TLD);
		}
		freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(classPathTlds);

	}

}
