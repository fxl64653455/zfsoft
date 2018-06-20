/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.smbclient;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.smbclient.SMBClient;
import com.zfsoft.smbclient.SMBClientBuilder;
import com.zfsoft.smbclient.client.ISMBClient;
import com.zfsoft.smbclient.client.SMBPooledResourceClient;
import com.zfsoft.smbclient.pool.SMBClientManager;
import com.zfsoft.smbclient.pool.SMBClientPoolConfig;
import com.zfsoft.smbclient.pool.SMBPooledClientManager;


@Configuration
@ConditionalOnClass({ ISMBClient.class, SMBClient.class, SMBClientPoolConfig.class, GenericObjectPoolConfig.class })
@EnableConfigurationProperties({ SMBClientProperties.class })
public class SMBClientPooledConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
	public SMBClientBuilder SMBClientBuilder( SMBClientProperties properties) {
		return new SMBClientBuilder(properties);
	}
	
	@Bean
	public SMBPooledClientManager SMBClientManager( SMBClientBuilder clientBuilder, SMBClientProperties properties) {
		
		SMBClientPoolConfig clientPoolConfig = properties.getPool();
		
		return new SMBPooledClientManager(clientBuilder , clientPoolConfig);
	}
	
	
	@Bean
	public ISMBClient SMBClient(SMBClientManager clientManager) {
		return new SMBPooledResourceClient(clientManager);
	}

}
