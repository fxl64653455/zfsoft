/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfsoft.boot.mybatis.dao.daointerface.IUuidDao;
import com.zfsoft.boot.mybatis.service.svcinterface.IUuidService;

@Service
public class IUuidServiceImpl implements IUuidService {

	@Autowired
	protected IUuidDao uuidDao;

	@Override
	public String get() {
		return uuidDao.get();
	}
	
}
