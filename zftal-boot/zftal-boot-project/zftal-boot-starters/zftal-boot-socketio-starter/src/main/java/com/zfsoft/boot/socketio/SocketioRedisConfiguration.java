package com.zfsoft.boot.socketio;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.store.RedissonStoreFactory;
import com.corundumstudio.socketio.store.StoreFactory;

@Configuration
@AutoConfigureBefore( name = {
	"com.corundumstudio.socketio.spring.boot.SocketioServerAutoConfiguration"
})
@ConditionalOnClass(name = {"org.redisson.api.RedissonClient"})
@ConditionalOnProperty(prefix = SocketioRedisProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ SocketioRedisProperties.class })
public class SocketioRedisConfiguration {

	protected static Logger LOG = LoggerFactory.getLogger(SocketioRedisConfiguration.class);
	@Autowired
	private SocketioRedisProperties config;
	    
	@Bean
	public RedissonClient redisClient() {
		return Redisson.create(config);
	}
	
	@Bean
	public RedissonClient redisPub() {
		return Redisson.create(config);
	}
	
	@Bean
	public RedissonClient redisSub() {
		return Redisson.create(config);
	}
	
	@Bean
	public StoreFactory clientStoreFactory(Redisson redisClient, Redisson redisPub, Redisson redisSub) {
		return new RedissonStoreFactory(redisClient, redisPub, redisSub);
	}

}
