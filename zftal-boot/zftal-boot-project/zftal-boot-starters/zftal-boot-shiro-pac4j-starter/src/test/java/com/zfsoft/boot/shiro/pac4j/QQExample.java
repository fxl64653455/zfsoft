package com.zfsoft.boot.shiro.pac4j;

import java.io.IOException;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.zfsoft.boot.shiro.pac4j.scribe.builder.api.QQApi20;

public class QQExample {

	public static void main(String[] args) throws IOException {

		final String apiKey = "101415402";
		final String apiSecret = "57b2bd109e2c043979c98beb2ccc46ad";
		final OAuth20Service service = new ServiceBuilder().apiKey(apiKey).apiSecret(apiSecret)
				.callback("http://portal.zfsoft.com:8000/cas/login?client_name=QQClient").state("xxxx")
				.scope("get_user_info").build(QQApi20.instance());

		System.out.println(service.getAuthorizationUrl());
		
		
		System.out.println(service.getAccessToken("3C3A374FF277F95B509A5EBD27305356").getAccessToken());
		
		
	}
		
}
