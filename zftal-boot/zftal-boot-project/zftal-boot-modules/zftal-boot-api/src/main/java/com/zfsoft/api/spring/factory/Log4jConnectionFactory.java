/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.api.spring.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class Log4jConnectionFactory {

	private static interface Singleton {
		final Log4jConnectionFactory INSTANCE = new Log4jConnectionFactory();
	}

	private DataSource dataSource;

	private Log4jConnectionFactory() {
	}

	public static void setDataSource(DataSource dataSource) throws SQLException {
		Singleton.INSTANCE.dataSource = dataSource;
	}

	public static Connection getDatabaseConnection() throws SQLException {
		return Singleton.INSTANCE.dataSource.getConnection();
	}

}
