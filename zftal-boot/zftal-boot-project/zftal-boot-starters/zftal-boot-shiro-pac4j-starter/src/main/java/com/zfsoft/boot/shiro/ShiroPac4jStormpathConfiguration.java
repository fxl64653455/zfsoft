/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;

import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.stormpath.credentials.authenticator.StormpathAuthenticator;
import org.pac4j.stormpath.profile.StormpathProfileDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter( name = {
	"org.apache.shiro.spring.boot.ShiroPac4jClientsConfiguration"
})
@ConditionalOnWebApplication
@ConditionalOnClass({ StormpathAuthenticator.class})
@ConditionalOnProperty(prefix = ShiroPac4jStormpathProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jStormpathProperties.class, ShiroBizProperties.class, ServerProperties.class })
public class ShiroPac4jStormpathConfiguration {
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jStormpathProperties.PREFIX, value = "casClient", havingValue = "true")
	public ParameterClient parameterClient() {
		
        final StormpathAuthenticator authenticator = new StormpathAuthenticator();
        
        /*authenticator.setAccessId(accessId);
        authenticator.setApplicationId(applicationId);
        authenticator.setSecretKey(secretKey);*/
        
        authenticator.setProfileDefinition(new StormpathProfileDefinition());
        
        ParameterClient parameterClient = new ParameterClient("token", authenticator);
        parameterClient.setSupportGetRequest(true);
        parameterClient.setSupportPostRequest(false);
	    
		return parameterClient;
	}
	
}
