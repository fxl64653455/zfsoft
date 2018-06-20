/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.druid.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zfsoft.boot.druid.dao.daointerface.IUuidDao;

@Repository
public class UuidDaoImpl implements IUuidDao {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public String get() {
		//SELECT UUID() as id 
		return jdbcTemplate.queryForObject("select sys_guid() as id from dual ", String.class );
	}
	 
	
}
