package com.zfsoft.boot.cxf;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.apache.cxf.metrics.codahale.CodahaleMetricsProvider;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//http://cxf.apache.org/docs/springboot.html

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ SpringBus.class, CXFServlet.class })
@ConditionalOnProperty(prefix = CxfJaxrsProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ CxfJaxrsProperties.class })
@AutoConfigureAfter( name = {
	"org.apache.cxf.spring.boot.autoconfigure.CxfAutoConfiguration"
})
public class CxfJaxrsAutoConfiguration2 implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Autowired
    private Bus bus;
	
	@Bean
	@ConditionalOnMissingBean(Bus.class)
	public Bus bus(){
	    return new SpringBus();
	}
	
	@Bean
	public MetricsProvider metricsProvider() {
		return new CodahaleMetricsProvider(bus);
    }
	
	@Bean
	public MetricsFeature metricsFeature(MetricsProvider provider) {

		MetricsFeature feature = new MetricsFeature(provider);
		
		
		return feature;
    }
 
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
