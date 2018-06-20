/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.ftpclient;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.ftpclient.FTPClientBuilder;
import com.zfsoft.ftpclient.client.FTPPooledResourceClient;
import com.zfsoft.ftpclient.client.IFTPClient;
import com.zfsoft.ftpclient.pool.FTPClientManager;
import com.zfsoft.ftpclient.pool.FTPClientPoolConfig;
import com.zfsoft.ftpclient.pool.FTPPooledClientManager;


@Configuration
@ConditionalOnClass({ FTPClientPoolConfig.class, GenericObjectPoolConfig.class })
@EnableConfigurationProperties({ FTPClientProperties.class })
public class FTPClientPooledConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
	public FTPClientBuilder ftpClientBuilder( FTPClientProperties properties) {
		return new FTPClientBuilder(properties);
	}
	
	@Bean
	public FTPPooledClientManager ftpClientManager( FTPClientBuilder clientBuilder, FTPClientProperties properties) {
		
		FTPClientPoolConfig clientPoolConfig = properties.getPool();
		
		return new FTPPooledClientManager(clientBuilder , clientPoolConfig);
	}
	
	@Bean
	public IFTPClient ftpClient(FTPClientManager clientManager) {
		return new FTPPooledResourceClient(clientManager);
	}

}
