/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.impl;

import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiMetircDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiMetircModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiMetircService;

/**
 * 
 * @className	： ApiMetircServiceImpl
 * @description	： 接口监控数据存储查询Service实现
 * @author 		：万大龙（743）
 * @date		： 2017年11月27日 下午5:34:50
 * @version 	V1.0
 */
@Service
public class ApiMetircServiceImpl extends BaseServiceImpl<ApiMetircModel, IApiMetircDao> implements IApiMetircService{

}
