/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.ftpclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import com.zfsoft.ftpclient.FTPClientConfig;
import com.zfsoft.ftpclient.pool.FTPClientPoolConfig;

/**
 * 
 * @className	： FTPClientProperties
 * @description	： FTPClient 参数配置
 * @author 		：万大龙（743）
 * @date		： 2018年5月10日 上午10:42:47
 * @version 	V1.0
 */
@ConfigurationProperties(FTPClientProperties.PREFIX)
public class FTPClientProperties extends FTPClientConfig {

	public static final String PREFIX = "ftpclient";
	
	@NestedConfigurationProperty
	private FTPClientPoolConfig pool = new FTPClientPoolConfig();

	public FTPClientPoolConfig getPool() {
		return pool;
	}

	public void setPool(FTPClientPoolConfig pool) {
		this.pool = pool;
	}
	
}
