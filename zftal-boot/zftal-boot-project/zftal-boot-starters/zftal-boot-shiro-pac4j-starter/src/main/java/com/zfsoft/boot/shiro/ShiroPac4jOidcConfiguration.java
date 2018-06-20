/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;

import org.pac4j.oidc.client.GoogleOidcClient;
import org.pac4j.oidc.config.OidcConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nimbusds.jose.JWSAlgorithm;

@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.boot.ShiroPac4jClientsConfiguration	"
})
@ConditionalOnWebApplication
@ConditionalOnClass({ GoogleOidcClient.class, OidcConfiguration.class})
@ConditionalOnProperty(prefix = ShiroPac4jOidcProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jOidcProperties.class, ShiroBizProperties.class, ServerProperties.class })
public class ShiroPac4jOidcConfiguration {

	@Bean
    public OidcConfiguration oidcConfiguration() {
		
		/*final OidcConfiguration oidcConfiguration = new OidcConfiguration();
	    oidcConfiguration.setClientId("test");
	    oidcConfiguration.setSecret("secret");
	    oidcConfiguration.setUseNonce(true);
	    oidcConfiguration.setDiscoveryURI("http://localhost:5000/.well-known/openid-configuration");
	    oidcConfiguration.setScope("openid api1");
	    oidcConfiguration.setClientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
	    oidcConfiguration.addCustomParam("prompt", "consent");
	    final OidcClient oidcClient = new OidcClient(oidcConfiguration);
	    oidcClient.setName("test");
	    oidcClient.setCallbackUrl("http://localhost:8080/callback");*/

	    final OidcConfiguration configuration = new OidcConfiguration();
	    configuration.setClientId("167480702619-8e1lo80dnu8bpk3k0lvvj27noin97vu9.apps.googleusercontent.com");
	    configuration.setSecret("MhMme_Ik6IH2JMnAT6MFIfee");
	    configuration.setPreferredJwsAlgorithm(JWSAlgorithm.PS384);
	    configuration.addCustomParam("prompt", "consent");
	    
	    /*configuration.setCallbackUrl(callbackUrl);
	    configuration.setClientAuthenticationMethod(clientAuthenticationMethod);
	    configuration.setClientAuthenticationMethodAsString(auth);
	    configuration.setClientId(clientId);
	    configuration.setConnectTimeout(connectTimeout);
	    configuration.setCustomParams(customParams);
	    configuration.setDiscoveryURI(discoveryURI);
	    configuration.setLogoutUrl(logoutUrl);
	    configuration.setMaxClockSkew(maxClockSkew);
	    configuration.setPreferredJwsAlgorithm(preferredJwsAlgorithm);
	    configuration.setProviderMetadata(providerMetadata);
	    configuration.setReadTimeout(readTimeout);
	    configuration.setResourceRetriever(resourceRetriever);
	    configuration.setResponseMode(responseMode);
	    configuration.setResponseType(responseType);
	    configuration.setScope(scope);
	    configuration.setSecret(secret);
	    configuration.setUseNonce(useNonce);*/
		
		return configuration;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOidcProperties.PREFIX, value = "casClient", havingValue = "true")
	public GoogleOidcClient oidcClient(OidcConfiguration oidcConfiguration) {
		
		final GoogleOidcClient oidcClient = new GoogleOidcClient(oidcConfiguration);
	    oidcClient.setAuthorizationGenerator((ctx, profile) -> { 
	    	
	    	profile.addRole("ROLE_ADMIN"); 
	    	return profile; 
	    	
	    });
	    
		return oidcClient;
	}
	
    
    
    
}
