package com.zfsoft.boot.log4j2.appender.db.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.core.appender.db.jdbc.ConnectionSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @className	： JDBCConnectionSource
 * @description	：使用已有数据源作为连接获取基础
 * @author 		：万大龙（743）
 * @date		： 2017年10月16日 下午12:16:14
 * @version 	V1.0
 */
public class JdbcConnectionSource implements ConnectionSource {

	private static Logger LOG = LoggerFactory.getLogger(JdbcConnectionSource.class);
	private DataSource dataSource;
	
	public JdbcConnectionSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		LOG.debug("Get ");
		return dataSource.getConnection();
	}
	
}