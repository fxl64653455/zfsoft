/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shardingjdbc.sharding;

import io.shardingjdbc.core.yaml.sharding.YamlShardingRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Sharding rule configuration properties.
 */
@ConfigurationProperties(prefix = "sharding.jdbc.config.sharding")
public class ShardingRuleConfigurationProperties extends YamlShardingRuleConfiguration {
}
