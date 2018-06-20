package com.zfsoft.boot.hikaricp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import com.zfsoft.boot.hikaricp.ds.DynamicDataSourceSetting;

@ConfigurationProperties(HikaricpDynamicProperties.PREFIX)
public class HikaricpDynamicProperties {

	public static final String PREFIX = "spring.datasource.hikari.dynamic";
	
	/** 是否激活动态数据源 */
	protected Boolean enabled = false;
	 
	/** 动态数据源连接信息 */
	@NestedConfigurationProperty
	protected List<DynamicDataSourceSetting> dataSources = new ArrayList<DynamicDataSourceSetting>();

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<DynamicDataSourceSetting> getDataSources() {
		return dataSources;
	}

	public void setDataSources(List<DynamicDataSourceSetting> dataSources) {
		this.dataSources = dataSources;
	}
	
}