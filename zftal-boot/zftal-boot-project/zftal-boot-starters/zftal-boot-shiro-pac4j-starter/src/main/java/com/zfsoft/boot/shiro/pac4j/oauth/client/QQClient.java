package com.zfsoft.boot.shiro.pac4j.oauth.client;

import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.client.OAuth20Client;

import com.zfsoft.boot.shiro.pac4j.oauth.profile.qq.QQProfile;
import com.zfsoft.boot.shiro.pac4j.oauth.profile.qq.QQProfileCreator;
import com.zfsoft.boot.shiro.pac4j.oauth.profile.qq.QQProfileDefinition;
import com.zfsoft.boot.shiro.pac4j.scribe.builder.api.QQApi20;
import com.zfsoft.boot.shiro.utils.StringUtils;

/**
 */
public class QQClient extends OAuth20Client<QQProfile> {

	public final static String DEFAULT_SCOPE = "get_user_info,list_album";

	public QQClient() {
	}

	public QQClient(final String key, final String secret) {
		setKey(key);
		setSecret(secret);
	}

	@Override
	protected void clientInit(final WebContext context) {

		configuration.setApi(QQApi20.instance());
		configuration.setProfileDefinition(new QQProfileDefinition());
		configuration.setScope(StringUtils.hasText(configuration.getScope()) ? configuration.getScope(): DEFAULT_SCOPE);
		setConfiguration(configuration);
		defaultProfileCreator(new QQProfileCreator(configuration));
		 
		super.clientInit(context);
	}

}
