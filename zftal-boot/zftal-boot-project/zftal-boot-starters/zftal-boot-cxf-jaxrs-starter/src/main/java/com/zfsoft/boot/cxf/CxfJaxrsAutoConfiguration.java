package com.zfsoft.boot.cxf;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.rs.security.oauth2.provider.DefaultEHCacheOAuthDataProvider;
import org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider;
import org.apache.cxf.rs.security.oauth2.services.AccessTokenService;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//http://cxf.apache.org/docs/springboot.html

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ SpringBus.class, CXFServlet.class })
@ConditionalOnProperty(prefix = CxfJaxrsProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ CxfJaxrsProperties.class })
@AutoConfigureAfter( name = {
	"org.apache.cxf.spring.boot.autoconfigure.CxfAutoConfiguration"
})
public class CxfJaxrsAutoConfiguration implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Autowired
    private Bus bus;
	
	@Bean
	@ConditionalOnMissingBean(Bus.class)
	public Bus bus(){
	    return new SpringBus();
	}
	
	@Configuration
    @ConditionalOnClass(JAXRSServerFactoryBean.class)
    @ConditionalOnExpression("'${cxf.jaxrs.component-scan}'=='true' && '${cxf.jaxrs.classes-scan}'!='true'")
    @Import(org.apache.cxf.jaxrs.spring.SpringComponentScanServer.class)
    protected static class JaxRsComponentConfiguration {

    }

    @Configuration
    @ConditionalOnClass(JAXRSServerFactoryBean.class)
    @ConditionalOnExpression("'${cxf.jaxrs.classes-scan}'=='true' && '${cxf.jaxrs.component-scan}'!='true'")
    @Import(org.apache.cxf.jaxrs.spring.SpringJaxrsClassesScanServer.class)
    protected static class JaxRsClassesConfiguration {

    }
  /*  
	@Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("/");
        // Register 2 JAX-RS root resources supporting "/sayHello/{id}" and "/sayHello2/{id}" relative paths
        endpoint.setServiceBeans(Arrays.<Object>asList(new HelloServiceImpl1(), new HelloServiceImpl2()));
        endpoint.setFeatures(Arrays.asList(new Swagger2Feature()));
        return endpoint.create();
    }*/
	
	/**
	 * 决定一个消费者将如何等待生产者将Event置入Disruptor的策略。用来权衡当生产者无法将新的事件放进RingBuffer时的处理策略。
	 * （例如：当生产者太快，消费者太慢，会导致生成者获取不到新的事件槽来插入新事件，则会根据该策略进行处理，默认会堵塞）
	 */
	@Bean
	@ConditionalOnMissingBean
	public DefaultEHCacheOAuthDataProvider oauthProvider() {
		
		/*<bean id="oauthProvider" class="org.apache.cxf.systest.jaxrs.security.oauth2.common.OAuthDataProviderImpl">
		       <property name="useJwtFormatForAccessTokens" value="true"/>
		       <property name="storeJwtTokenKeyOnly" value="true"/>
		</bean>
		*/
		/*<bean id="oauthProvider" class="org.apache.cxf.rs.security.oauth2.grants.code.EHCacheCodeDataProvider">
		       <property name="useJwtFormatForAccessTokens" value="true"/>
		</bean>*/
		
		DefaultEHCacheOAuthDataProvider dataProvider  = new DefaultEHCacheOAuthDataProvider();
		
		dataProvider.setUseJwtFormatForAccessTokens(true);
		
		return dataProvider;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public AccessTokenService accessTokenService(OAuthDataProvider dataProvider) {
		/*
		<bean id="oauthProvider" class="oauth2.manager.OAuthManager"/>
		 
		<bean id="accessTokenService" class="org.apache.cxf.rs.security.oauth2.services.AccessTokenService">
		    <property name="dataProvider" ref="oauthProvider"/>
		    <property name="writeCustomErrors" value="true"/>
		</bean>
		*/
		
		AccessTokenService accessTokenService = new AccessTokenService();
		
		accessTokenService.setDataProvider(dataProvider);
		accessTokenService.setWriteCustomErrors(true);
		
		return accessTokenService;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
