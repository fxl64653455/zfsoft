/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiMetircAnalysisDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiMetircModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiMetircAnalysisService;

/**
 * 
 * @className ： ApiMetircAnalysisServiceImpl
 * @description ：接口监控数据分析Service实现
 * @author ：万大龙（743）
 * @date ： 2017年11月28日 下午5:37:12
 * @version V1.0
 */
@Service
public class ApiMetircAnalysisServiceImpl extends BaseServiceImpl<ApiMetircModel, IApiMetircAnalysisDao>
		implements IApiMetircAnalysisService {

	@Override
	public List<Map<String, String>> ratio(ApiMetircModel model) {
		return getDao().getRatio(model);
	}

	@Override
	public List<Map<String, String>> top5(ApiMetircModel model) {
		model.setTop(5);
		return getDao().getTop(model);
	}

	@Override
	public List<Map<String, String>> top10(ApiMetircModel model) {
		model.setTop(10);
		return getDao().getTop(model);
	}

	@Override
	public List<Map<String, String>> top50(ApiMetircModel model) {
		model.setTop(50);
		return getDao().getTop(model);
	}

	@Override
	public List<Map<String, String>> top100(ApiMetircModel model) {
		model.setTop(100);
		return getDao().getTop(model);
	}

	@Override
	public List<Map<String, String>> appRatio(ApiMetircModel model) {
		return getDao().getAppRatio(model);
	}

	@Override
	public List<Map<String, String>> appDaily(ApiMetircModel model) {
		model.setTop(Math.max(model.getTop(), 7));
		return getDao().getAppDaily(model);
	}

	@Override
	public List<Map<String, String>> appList(ApiMetircModel model) {
		return getDao().getAppList(model);
	}

	@Override
	public List<Map<String, String>> appTop5(ApiMetircModel model) {
		model.setTop(5);
		return getDao().getAppTop(model);
	}

	@Override
	public List<Map<String, String>> appTop10(ApiMetircModel model) {
		model.setTop(10);
		return getDao().getAppTop(model);
	}

	@Override
	public List<Map<String, String>> bizRatio(ApiMetircModel model) {
		return getDao().getBizRatio(model);
	}

	@Override
	public List<Map<String, String>> bizDaily(ApiMetircModel model) {
		model.setTop(Math.max(model.getTop(), 7));
		return getDao().getBizDaily(model);
	}

	@Override
	public List<Map<String, String>> bizList(ApiMetircModel model) {
		return getDao().getBizList(model);
	}

	@Override
	public List<Map<String, String>> bizTop5(ApiMetircModel model) {
		model.setTop(5);
		return getDao().getBizTop(model);
	}

	@Override
	public List<Map<String, String>> bizTop10(ApiMetircModel model) {
		model.setTop(10);
		return getDao().getBizTop(model);
	}

	@Override
	public List<Map<String, String>> deviceRatio(ApiMetircModel model) {
		return getDao().getDeviceRatio(model);
	}

	@Override
	public List<Map<String, String>> deviceDaily(ApiMetircModel model) {
		model.setTop(Math.max(model.getTop(), 7));
		return getDao().getDeviceDaily(model);
	}

	@Override
	public List<Map<String, String>> deviceList(ApiMetircModel model) {
		return getDao().getDeviceList(model);
	}

	@Override
	public List<Map<String, String>> deviceTop5(ApiMetircModel model) {
		model.setTop(5);
		return getDao().getDeviceTop(model);
	}

	@Override
	public List<Map<String, String>> deviceTop10(ApiMetircModel model) {
		model.setTop(10);
		return getDao().getDeviceTop(model);
	}

	@Override
	public List<Map<String, String>> osRatio(ApiMetircModel model) {
		return getDao().getOsRatio(model);
	}

	@Override
	public List<Map<String, String>> osDaily(ApiMetircModel model) {
		model.setTop(Math.max(model.getTop(), 7));
		return getDao().getOsDaily(model);
	}

	@Override
	public List<Map<String, String>> osList(ApiMetircModel model) {
		return getDao().getOsList(model);
	}

	@Override
	public List<Map<String, String>> osTop5(ApiMetircModel model) {
		model.setTop(5);
		return getDao().getOsTop(model);
	}

	@Override
	public List<Map<String, String>> osTop10(ApiMetircModel model) {
		model.setTop(10);
		return getDao().getOsTop(model);
	}

	@Override
	public List<Map<String, String>> browserRatio(ApiMetircModel model) {
		return getDao().getBrowserRatio(model);
	}

	@Override
	public List<Map<String, String>> browserDaily(ApiMetircModel model) {
		model.setTop(Math.max(model.getTop(), 7));
		return getDao().getBrowserDaily(model);
	}

	@Override
	public List<Map<String, String>> browserList(ApiMetircModel model) {
		return getDao().getBrowserList(model);
	}

	@Override
	public List<Map<String, String>> browserTop5(ApiMetircModel model) {
		model.setTop(5);
		return getDao().getBrowserTop(model);
	}

	@Override
	public List<Map<String, String>> browserTop10(ApiMetircModel model) {
		model.setTop(10);
		return getDao().getBrowserTop(model);
	}

}
