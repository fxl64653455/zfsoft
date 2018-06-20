/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.smbclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.smbclient.SMBClient;
import com.zfsoft.smbclient.SMBClientBuilder;
import com.zfsoft.smbclient.client.ISMBClient;
import com.zfsoft.smbclient.client.SMBResourceClient;


@Configuration
@ConditionalOnClass({ ISMBClient.class, SMBClient.class })
@EnableConfigurationProperties({ SMBClientProperties.class })
public class SMBClientConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public SMBClientBuilder SMBClientBuilder( SMBClientProperties properties) {
		return new SMBClientBuilder(properties);
	}
	
	@Bean
	public ISMBClient SMBClient(SMBClientBuilder clientBuilder) {
		return new SMBResourceClient(clientBuilder);
	}

}
