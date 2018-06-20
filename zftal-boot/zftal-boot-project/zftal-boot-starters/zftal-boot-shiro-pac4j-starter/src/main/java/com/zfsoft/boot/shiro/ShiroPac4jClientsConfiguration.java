/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.pac4j.core.client.Client;
import org.pac4j.core.client.Clients;
import org.pac4j.core.http.AjaxRequestResolver;
import org.pac4j.core.http.DefaultAjaxRequestResolver;
import org.pac4j.core.http.UrlResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import com.zfsoft.boot.shiro.pac4j.ShiroPac4jRelativeUrlResolver;

import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;

@Configuration
@AutoConfigureBefore( name = {
	"com.zfsoft.boot.shiro.ShiroPac4jCasWebFilterConfiguration"
})
@ConditionalOnWebApplication
@ConditionalOnClass({CallbackFilter.class, SecurityFilter.class, LogoutFilter.class})
@ConditionalOnProperty(prefix = ShiroPac4jProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jProperties.class, ShiroBizProperties.class, ServerProperties.class })
@SuppressWarnings("rawtypes")
public class ShiroPac4jClientsConfiguration implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;

	@Autowired
	private ShiroPac4jProperties pac4jProperties;
	@Autowired
	private ServerProperties serverProperties;
	
	@Bean
	@ConditionalOnMissingBean
	protected AjaxRequestResolver ajaxRequestResolver() {
		return new DefaultAjaxRequestResolver();
	}
	
	@Bean
	@ConditionalOnMissingBean
	protected UrlResolver urlResolver() {
		return new ShiroPac4jRelativeUrlResolver(serverProperties.getServlet().getContextPath());
	}
	
	@Bean
	public Clients clients(@Autowired(required = false) @Qualifier("defaultClient") Client defaultClient,
			@Autowired(required = false) @Qualifier("oauth20Clients") List<Client> oauth20Clients,
			AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final List<Client> clientList = new ArrayList<Client>();
		Map<String, Client> beansOfType = getApplicationContext().getBeansOfType(Client.class);
		if (!ObjectUtils.isEmpty(beansOfType)) {
			Iterator<Entry<String, Client>> ite = beansOfType.entrySet().iterator();
			while (ite.hasNext()) {
				clientList.add(ite.next().getValue());
			}
		}
		
		if(oauth20Clients != null) {
			clientList.addAll(oauth20Clients);
		}
		
		final Clients clients = new Clients(pac4jProperties.getCallbackUrl(), clientList);
		
		clients.setAjaxRequestResolver(ajaxRequestResolver);
		clients.setCallbackUrl(pac4jProperties.getCallbackUrl());
		clients.setClients(clientList);
		clients.setClientNameParameter(pac4jProperties.getClientParameterName());
		if(defaultClient != null) {
			clients.setDefaultClient(defaultClient);
		}
		clients.setUrlResolver(urlResolver);
		
		return clients;
	}
	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
