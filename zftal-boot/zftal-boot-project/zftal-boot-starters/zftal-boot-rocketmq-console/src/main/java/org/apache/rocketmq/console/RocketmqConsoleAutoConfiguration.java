package org.apache.rocketmq.console;

import org.apache.rocketmq.console.config.RMQConfigure;
import org.apache.rocketmq.console.task.DashboardCollectTask;
import org.apache.rocketmq.console.task.MonitorTask;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/*@ConditionalOnBean({ MonitorService.class, ConsumerService.class, DashboardCollectService.class, MQAdminExt.class,
		RMQConfigure.class })*/
@EnableConfigurationProperties({ RMQConfigure.class })
public class RocketmqConsoleAutoConfiguration {

	@Bean
	public DashboardCollectTask dashboardCollectTask() {
		return new DashboardCollectTask();
	}

	@Bean
	public MonitorTask monitorTask() {
		return new MonitorTask();
	}
    
}
