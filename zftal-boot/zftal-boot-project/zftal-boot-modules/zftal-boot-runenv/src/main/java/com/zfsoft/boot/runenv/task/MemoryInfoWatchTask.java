package com.zfsoft.boot.runenv.task;

import javax.annotation.Resource;

import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zfsoft.api.conf.PropertiesProvider;
import com.zfsoft.boot.runenv.provider.MemoryInfoProvider;
import com.zfsoft.boot.runenv.provider.MemoryInfoProviderManager;
import com.zfsoft.metrics.sigar.OSEnvInfo;

//@Component("memoryInfoTast")
public class MemoryInfoWatchTask  {

	protected final Logger LOG = LoggerFactory.getLogger(MemoryInfoWatchTask.class);
	
	@Resource
	protected Sigar sigar = null;

	@Resource
	protected MemoryInfoProviderManager providerManager;
	
	@Resource(name = "wacthPropsProvider")
	protected PropertiesProvider propsProvider = null;
	
	/**
	 * 
	 *@描述		：使用率采集：JVM,RAM,CPU
	 *@创建人	: wandalong
	 *@创建时间	: 2017年6月22日下午12:00:00
	 *@修改人	: 
	 *@修改时间	: 
	 *@修改描述	:
	 */
	@Scheduled(fixedDelay = 1500)
	public void usage(){
		try {
			String provider = getPropsProvider().props().getProperty(MemoryInfoProvider.SMS_PROVIDER);
			getProviderManager().getMemoryInfoProvider(provider).setUsage(OSEnvInfo.usage(sigar));
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * 
	 *@描述		：使用量采集：JVM,RAM,SWAP
	 *@创建人	: wandalong
	 *@创建时间	: 2017年6月22日下午12:00:52
	 *@修改人	: 
	 *@修改时间	: 
	 *@修改描述	:
	 */
	@Scheduled(fixedDelay = 1500)
	public void memory(){
		try {
			String provider = getPropsProvider().props().getProperty(MemoryInfoProvider.SMS_PROVIDER);
			getProviderManager().getMemoryInfoProvider(provider).setMemory(OSEnvInfo.memory(sigar));
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}
	
	public Sigar getSigar() {
		return sigar;
	}

	public void setSigar(Sigar sigar) {
		this.sigar = sigar;
	}

	public MemoryInfoProviderManager getProviderManager() {
		return providerManager;
	}

	public void setProviderManager(MemoryInfoProviderManager providerManager) {
		this.providerManager = providerManager;
	}

	public PropertiesProvider getPropsProvider() {
		return propsProvider;
	}

	public void setPropsProvider(PropertiesProvider propsProvider) {
		this.propsProvider = propsProvider;
	}
	
}
