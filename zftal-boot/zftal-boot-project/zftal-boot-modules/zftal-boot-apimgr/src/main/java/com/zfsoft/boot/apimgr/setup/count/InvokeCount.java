/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.count;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;

import com.zfsoft.boot.apimgr.dao.daointerface.IApiMetircDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiMetircModel;
import com.zfsoft.boot.flyway.ext.FlywayMigratedEvent;

public class InvokeCount implements ApplicationListener<FlywayMigratedEvent>{
	
	private IApiMetircDao metircDao;
	private Map<String, Integer> store;

	public InvokeCount(IApiMetircDao metircDao) {
		this.metircDao = metircDao;
	}
	
	public Integer get(String key) {
		return store.get(key) != null ? store.get(key) : 0;
	}
	
	public void increase(String key, Integer n) {
		store.put(key, get(key) + n);
	}
	
	@Scheduled(cron="0 0 0 * * ?")
	public void reset() {
		store.clear();
	}

	@Override
	public void onApplicationEvent(FlywayMigratedEvent event) {
		store = new ConcurrentHashMap<>();
		List<ApiMetircModel> list = metircDao.countByAppKey();
		for (ApiMetircModel apiMetircModel : list) {
			store.put(apiMetircModel.getAppKey(), apiMetircModel.getTop());
		}
	}
	
}
