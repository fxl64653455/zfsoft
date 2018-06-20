package com.zfsoft.boot.init.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.init.dao.daointerface.IValidationDao;
import com.zfsoft.boot.init.dao.entities.ValidationModel;
import com.zfsoft.boot.init.service.svcinterface.IValidationService;

@Service
public class ValidationServiceImpl implements IValidationService {

	@Resource
	private IValidationDao validationDao;

	// 系统表映射
	@Resource
	private Map<String, String> tableMapper;

	@Override
	public boolean unique(ValidationModel model) {
		String table = tableMapper.get(model.getTable());
		model.setTable(table);
		if (StringUtils.isEmpty(model.getFiled_value())) {
			int count = validationDao.getMultiCount(model);
			return count == 0;
		} else {
			int count = validationDao.getCount(model);
			return count == 0;
		}
	}

	public Map<String, String> getTableMapper() {
		return tableMapper;
	}

	public void setTableMapper(Map<String, String> tableMapper) {
		this.tableMapper = tableMapper;
	}

	public IValidationDao getValidationDao() {
		return validationDao;
	}

	public void setValidationDao(IValidationDao validationDao) {
		this.validationDao = validationDao;
	}
	
}
