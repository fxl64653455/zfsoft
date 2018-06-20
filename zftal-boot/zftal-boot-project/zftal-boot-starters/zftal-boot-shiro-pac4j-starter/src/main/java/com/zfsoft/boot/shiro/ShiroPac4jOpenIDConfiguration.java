/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;

import org.pac4j.openid.client.YahooOpenIdClient;
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
@ConditionalOnClass({ YahooOpenIdClient.class})
@ConditionalOnProperty(prefix = ShiroPac4jOpenIDProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jOpenIDProperties.class, ShiroBizProperties.class, ServerProperties.class })
public class ShiroPac4jOpenIDConfiguration {

	@Bean
 	@ConditionalOnProperty(prefix = ShiroPac4jOpenIDProperties.PREFIX, value = "casClient", havingValue = "true")
 	public YahooOpenIdClient yahooOpenIdClient() {
 		
 	    final YahooOpenIdClient openIdClient = new YahooOpenIdClient();
 	  
 		return openIdClient;
 	}
		
}
