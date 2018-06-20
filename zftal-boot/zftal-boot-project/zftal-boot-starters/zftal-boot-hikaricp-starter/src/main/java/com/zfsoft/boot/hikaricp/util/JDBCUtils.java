package com.zfsoft.boot.hikaricp.util;

import java.sql.DriverManager;

import com.zfsoft.boot.hikaricp.ds.JDBCDriverEnum;

public class JDBCUtils {

	/**
	 * 
	 * @description	： TODO
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 上午10:05:22
	 * @param className
	 * @param URL
	 * @param Username
	 * @param Password
	 * @return
	 */
	public static boolean testConnection(String className,String URL,String Username,String Password) {
		try {
			Class.forName(className).newInstance();
			DriverManager.getConnection(URL, Username, Password);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static String getDriverClass(String dbtype) {
		JDBCDriverEnum driverEnum = JDBCDriverEnum.driver(dbtype);
		return driverEnum != null ? driverEnum.getDriverClass() : null; 
	}
	
}
