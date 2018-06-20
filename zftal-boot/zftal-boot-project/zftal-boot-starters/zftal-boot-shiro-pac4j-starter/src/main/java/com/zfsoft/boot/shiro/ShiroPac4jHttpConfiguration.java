/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;

import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
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
@ConditionalOnClass({ FormClient.class, IndirectBasicAuthClient.class})
@ConditionalOnProperty(prefix = ShiroPac4jHttpProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jHttpProperties.class, ShiroBizProperties.class, ServerProperties.class })
public class ShiroPac4jHttpConfiguration {

	@Autowired
	private ShiroPac4jHttpProperties pac4jHttpProperties;
	
    @Bean
 	@ConditionalOnProperty(prefix = ShiroPac4jHttpProperties.PREFIX, value = "form-client", havingValue = "true")
 	public FormClient formClient() {

		SimpleTestUsernamePasswordAuthenticator usernamePasswordAuthenticator = new SimpleTestUsernamePasswordAuthenticator();
		final FormClient formClient = new FormClient(pac4jHttpProperties.getLoginUrl(),
				pac4jHttpProperties.getUsernameParameter(), pac4jHttpProperties.getPasswordParameter(),
				usernamePasswordAuthenticator);
		
 		return formClient;
 	}
    
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jHttpProperties.PREFIX, value = "indirect-basic-auth-client", havingValue = "true")
	public IndirectBasicAuthClient indirectBasicAuthClient() {
		
		final SimpleTestUsernamePasswordAuthenticator usernamePasswordAuthenticator = new SimpleTestUsernamePasswordAuthenticator();
		
		final IndirectBasicAuthClient indirectBasicAuthClient = new IndirectBasicAuthClient(pac4jHttpProperties.getRealmName(), usernamePasswordAuthenticator);

		return indirectBasicAuthClient;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jHttpProperties.PREFIX, value = "direct-basic-auth-client", havingValue = "true")
	public DirectBasicAuthClient directBasicAuthClient() {
		
		final SimpleTestUsernamePasswordAuthenticator usernamePasswordAuthenticator = new SimpleTestUsernamePasswordAuthenticator();
		// basic auth
	    final DirectBasicAuthClient directBasicAuthClient = new DirectBasicAuthClient(usernamePasswordAuthenticator);
	    
		return directBasicAuthClient;
		
	}
	
}
