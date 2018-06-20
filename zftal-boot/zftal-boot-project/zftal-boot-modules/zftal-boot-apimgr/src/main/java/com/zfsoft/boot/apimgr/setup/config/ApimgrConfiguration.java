package com.zfsoft.boot.apimgr.setup.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.enhanced.factory.support.DefaultDynamicControllerRegistry;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zfsoft.security.algorithm.DesBase64Codec;

/**
 * 
 * @className	： ApiDeployConfig
 * @description	：  系统启动自动创建接口对象
 * @author 		：万大龙（743）
 * @date		： 2017年10月17日 下午5:03:14
 * @version 	V1.0
 */
@Configuration
public class ApimgrConfiguration{
	
	private DesBase64Codec dbencrypt = new DesBase64Codec();
	
	@Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
	@Bean(name = "dbencrypt")
    public DesBase64Codec dbencrypt() {
        return dbencrypt;
    }
	
	@Bean("endpointMap")
	public Map<String, EndpointImpl> endpoints() {
		return new HashMap<String, EndpointImpl>();
	}
	
	@Bean("classStore")
	public Map<String, Class<?>> classStore(){
		return new ConcurrentHashMap<>();
	}
	
	@Bean
	public DefaultDynamicControllerRegistry dynamicControllerRegistry() {
		return new DefaultDynamicControllerRegistry();
	}
	
}