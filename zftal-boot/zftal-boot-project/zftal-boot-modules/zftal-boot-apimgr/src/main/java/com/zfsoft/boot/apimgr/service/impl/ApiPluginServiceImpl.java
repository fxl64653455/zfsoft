/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.basemodel.PairModel;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiPluginDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiPluginModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiPluginService;
import com.zfsoft.plugin.api.ApiAuthExtensionPoint;
import com.zfsoft.plugin.api.ApiExtensionPoint;
import com.zfsoft.plugin.api.annotation.ApiExtensionMapping;
import com.zfsoft.plugin.api.annotation.ExtensionMapping;

import ro.fortsoft.pf4j.PluginDescriptor;
import ro.fortsoft.pf4j.PluginManager;
import ro.fortsoft.pf4j.PluginState;
import ro.fortsoft.pf4j.PluginStateEvent;
import ro.fortsoft.pf4j.PluginStateListener;
import ro.fortsoft.pf4j.PluginWrapper;
import ro.fortsoft.pf4j.update.UpdateManager;

@Service
public class ApiPluginServiceImpl extends BaseServiceImpl<ApiPluginModel, IApiPluginDao> implements IApiPluginService {

	@Autowired
	private PluginManager pluginManager;
	@Autowired
	private UpdateManager updateManager;
	
	@PostConstruct
	public void initPlugin() {

		pluginManager.addPluginStateListener(new PluginStateListener() {

			@Override
			public void pluginStateChanged(PluginStateEvent event) {

				if (PluginState.STOPPED.equals(event.getPluginState())) {

				}

			}

		});

	}

	@Override
	public List<PairModel> getPlugins() {

		List<PairModel> rtList = new ArrayList<PairModel>();
		List<PluginWrapper> list = getPluginManager().getPlugins();
		for (PluginWrapper wrapper : list) {

			PluginDescriptor descriptor = wrapper.getDescriptor();
			rtList.add(new PairModel(descriptor.getPluginId(), new StringBuilder(descriptor.getPluginDescription())
					.append("（").append(descriptor.getVersion().toString()).append("）").toString()));

		}

		return rtList;
	}

	@Override
	public List<PairModel> getAuthzExtensions(String pluginId) {
		List<PairModel> rtList = new ArrayList<PairModel>();
		List<ApiAuthExtensionPoint> extensions = getPluginManager().getExtensions(ApiAuthExtensionPoint.class, pluginId);
		for (ApiAuthExtensionPoint extension : extensions) {
	    	ExtensionMapping mapping = extension.getClass().getAnnotation(ExtensionMapping.class);
			rtList.add(new PairModel(mapping.id(), mapping.title()));
		}
		
		return rtList;
	}

	@Override
	public List<PairModel> getApiExtensions(String pluginId) {
		List<PairModel> rtList = new ArrayList<PairModel>();
		List<ApiExtensionPoint> extensions = getPluginManager().getExtensions(ApiExtensionPoint.class, pluginId);
		for (ApiExtensionPoint extension : extensions) {
			ApiExtensionMapping mapping = extension.getClass().getAnnotation(ApiExtensionMapping.class);
			rtList.add(new PairModel(mapping.id(), mapping.title()));
		}

		return rtList;
	}

	public PluginManager getPluginManager() {
		return pluginManager;
	}

	public void setPluginManager(PluginManager pluginManager) {
		this.pluginManager = pluginManager;
	}

	public UpdateManager getUpdateManager() {
		return updateManager;
	}

	public void setUpdateManager(UpdateManager updateManager) {
		this.updateManager = updateManager;
	}
	
}
