/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.hikaricp.ds;

import com.zaxxer.hikari.HikariDataSource;
import com.zfsoft.security.algorithm.DesBase64Codec;

/**
 * 
 * @className	： NewHikariDataSource
 * @description	： 基于 Hikari 数据连接池扩展公司内部加密解密工具后的数据源对象
 * @author 		：万大龙（743）
 * @date		： 2017年11月28日 上午10:19:59
 * @version 	V1.0
 */
public class NewHikariDataSource extends HikariDataSource {

	private DesBase64Codec dbencrypt = new DesBase64Codec();
	
	@Override
	public String getJdbcUrl() {
		return dbencrypt.decrypt(super.getJdbcUrl().getBytes());
	}
    
	@Override
	public String getUsername() {
		return dbencrypt.decrypt(super.getUsername().getBytes());
	}
	
	@Override
	public String getPassword() {
		return dbencrypt.decrypt(super.getPassword().getBytes());
	}
	
}
