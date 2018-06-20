package com.zfsoft.boot.druid;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import com.zfsoft.boot.druid.ds.DynamicDataSourceSetting;

/**
 * 
 * @className	： DruidDynamicProperties
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年10月16日 下午12:22:46
 * @version 	V1.0
 */
@ConfigurationProperties(DruidDynamicProperties.PREFIX)
public class DruidDynamicProperties {

	public static final String PREFIX = "spring.datasource.druid.dynamic";
	
	/** 是否激活动态数据源 */
	protected Boolean enabled = false;
	 
	/** 动态数据源连接信息 */
	@NestedConfigurationProperty
	protected List<DynamicDataSourceSetting> dataSourceList;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<DynamicDataSourceSetting> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<DynamicDataSourceSetting> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}

}