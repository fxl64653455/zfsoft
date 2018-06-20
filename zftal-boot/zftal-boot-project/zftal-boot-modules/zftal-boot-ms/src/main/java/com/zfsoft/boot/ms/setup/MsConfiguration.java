package com.zfsoft.boot.ms.setup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.boot.ms.provider.KeyValue;

@Configuration
public class MsConfiguration {
	
	@Bean
	public KeyValue keyValueProvider() {
		return new KeyValue();
	}
	
}
