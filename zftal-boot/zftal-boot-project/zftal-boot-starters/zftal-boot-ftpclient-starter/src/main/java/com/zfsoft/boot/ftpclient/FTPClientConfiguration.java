/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.ftpclient;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.ftpclient.FTPClientBuilder;
import com.zfsoft.ftpclient.client.FTPResourceClient;
import com.zfsoft.ftpclient.client.IFTPClient;


@Configuration
@ConditionalOnClass({ IFTPClient.class, FTPClientBuilder.class, FTPClient.class })
@EnableConfigurationProperties({ FTPClientProperties.class })
public class FTPClientConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public FTPClientBuilder ftpClientBuilder( FTPClientProperties properties) {
		return new FTPClientBuilder(properties);
	}
	
	@Bean
	public IFTPClient ftpClient(FTPClientBuilder clientBuilder) {
		return new FTPResourceClient(clientBuilder);
	}

}
