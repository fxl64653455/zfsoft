/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.smbclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import com.zfsoft.smbclient.SMBClientConfig;
import com.zfsoft.smbclient.pool.SMBClientPoolConfig;

/**
 * 
 * @className	： SMBClientProperties
 * @description	： SMBClient 参数配置
 * @author 		：万大龙（743）
 * @date		： 2018年5月10日 上午10:42:47
 * @version 	V1.0
 */
@ConfigurationProperties(SMBClientProperties.PREFIX)
public class SMBClientProperties extends SMBClientConfig {

	public static final String PREFIX = "smbclient";
	
	@NestedConfigurationProperty
	private SMBClientPoolConfig pool = new SMBClientPoolConfig();

	public SMBClientPoolConfig getPool() {
		return pool;
	}

	public void setPool(SMBClientPoolConfig pool) {
		this.pool = pool;
	}
	
}
