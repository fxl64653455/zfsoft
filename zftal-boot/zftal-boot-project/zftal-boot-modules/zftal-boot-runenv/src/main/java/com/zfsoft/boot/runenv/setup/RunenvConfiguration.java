package com.zfsoft.boot.runenv.setup;

import java.util.Map;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.basicutils.uid.Sequence;
import com.zfsoft.boot.runenv.provider.KeyValues;
import com.zfsoft.boot.runenv.provider.MemoryInfoProvider;
import com.zfsoft.boot.runenv.provider.MemoryInfoProviderManager;

@Configuration
public class RunenvConfiguration {
	
	@Bean
	public Sigar sigar() {
		return (Sigar)SigarFactory.newSigar();
	}
	
	@Bean
	public MemoryInfoProviderManager memoryInfoProviderManager(Map<String, MemoryInfoProvider> retakeMap) {
		
		//构造密码相关策略对象管理者
		MemoryInfoProviderManager strategyManager = new MemoryInfoProviderManager();
		
		//注册扩展的密码找回策略
		if(retakeMap != null){
			for (MemoryInfoProvider provider : retakeMap.values()) {
				strategyManager.register(provider);
			}
		}
		
		return strategyManager;
	}
	
	@Bean
	public KeyValues keyValuesProvider() {
		return new KeyValues();
	}
	
	@Bean
	public Sequence sequence() {
		return new Sequence();
	}
	
}
