/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;

import org.pac4j.kerberos.client.direct.DirectKerberosClient;
import org.pac4j.kerberos.client.indirect.IndirectKerberosClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.boot.ShiroPac4jClientsConfiguration"
})
@ConditionalOnWebApplication
@ConditionalOnClass({ DirectKerberosClient.class, IndirectKerberosClient.class })
@ConditionalOnProperty(prefix = ShiroPac4jKerberosProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jKerberosProperties.class, ShiroBizProperties.class, ServerProperties.class })
public class ShiroPac4jKerberosConfiguration {

	@Bean
 	@ConditionalOnProperty(prefix = ShiroPac4jKerberosProperties.PREFIX, value = "casClient", havingValue = "true")
 	public DirectKerberosClient directKerberosClient() {
 		
 	    final DirectKerberosClient kerberosClient = new DirectKerberosClient();
 	  
 		return kerberosClient;
 	}
    
    @Bean
 	@ConditionalOnProperty(prefix = ShiroPac4jKerberosProperties.PREFIX, value = "casClient", havingValue = "true")
 	public IndirectKerberosClient indirectKerberosClient() {
 		
 	    final IndirectKerberosClient kerberosClient = new IndirectKerberosClient();
 	  
 		return kerberosClient;
 	}
	
}
