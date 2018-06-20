package com.zfsoft.boot.flyway;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.boot.flyway.ext.FlywayMigrationProvider;

/**
 * 
 * @className	： FlywayMigrationAutoConfiguration
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月15日 上午8:56:37
 * @version 	V1.0
 * http://blog.csdn.net/tanghin/article/details/51264795
 */
@Configuration
@ConditionalOnClass(Flyway.class)
@ConditionalOnBean(DataSource.class)
@ConditionalOnProperty(prefix = FlywayMigrationProperties.PREFIX, name = "enabled", matchIfMissing = true)
@AutoConfigureBefore(FlywayAutoConfiguration.class)
@EnableConfigurationProperties({ FlywayMigrationProperties.class })
public class FlywayMigrationAutoConfiguration{
	
	@Bean
	@FlywayDataSource
	public DataSource flywayDatasource(DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().build();
	}
	
	@Bean
	public FlywayMigrationProvider flywayMigration(FlywayMigrationProperties properties) {
		return new FlywayMigrationProvider(properties);
	}
	
}
