/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.count;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.boot.apimgr.dao.daointerface.IApiMetircDao;

@Configuration
public class CountConfiguration {
	
	@Bean
	@ConditionalOnProperty(prefix = "shiro.oauth", name = "enabled", havingValue = "true")
	public InvokeCount invokeCount(IApiMetircDao metircDao) {
		return new InvokeCount(metircDao);
	}
	
}
