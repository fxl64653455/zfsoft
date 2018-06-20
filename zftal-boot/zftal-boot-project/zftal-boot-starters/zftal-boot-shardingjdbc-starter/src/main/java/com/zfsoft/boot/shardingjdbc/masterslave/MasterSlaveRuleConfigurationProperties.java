/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shardingjdbc.masterslave;

import io.shardingjdbc.core.yaml.masterslave.YamlMasterSlaveRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Master-slave rule configuration properties.
 */
@ConfigurationProperties(prefix = "sharding.jdbc.config.masterslave")
public class MasterSlaveRuleConfigurationProperties extends YamlMasterSlaveRuleConfiguration {
}
