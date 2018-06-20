package com.zfsoft.boot.shiro.pac4j.oauth.client;

import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.client.OAuth20Client;

import com.zfsoft.boot.shiro.pac4j.oauth.profile.weixin.WeiXinProfile;
import com.zfsoft.boot.shiro.pac4j.oauth.profile.weixin.WeiXinProfileDefinition;
import com.zfsoft.boot.shiro.pac4j.scribe.builder.api.WeiXinApi20;

/**
 * 此类用于处理CAS与微信的OAUTH通信
 */
public class WeiXinClient extends OAuth20Client<WeiXinProfile> {

	public WeiXinClient() {
	}

	public WeiXinClient(final String key, final String secret) {
		setKey(key);
		setSecret(secret);
	}

	@Override
	protected void clientInit(final WebContext context) {

		configuration.setApi(WeiXinApi20.instance());
		configuration.setProfileDefinition(new WeiXinProfileDefinition());
		setConfiguration(configuration);

		super.clientInit(context);
	}
	
}
