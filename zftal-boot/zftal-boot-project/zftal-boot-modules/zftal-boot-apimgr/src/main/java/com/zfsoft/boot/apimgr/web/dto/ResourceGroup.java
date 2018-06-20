/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * 资源组
 * @className	： ResourceGroup
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月20日 下午8:30:09
 * @version 	V1.0
 */
public class ResourceGroup{
	/**标识*/
	private String groupId;
	/**组名称*/
	private String groupName;
	/**资源地址正则表达式*/
	private String patterns;
	/**描述信息*/
	private String description;
	/**拥有的资源*/
	private Set<Resources> resources = new HashSet<>();
	
	private String totalResult = "0";
	public String getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(String totalResult) {
		this.totalResult = totalResult;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPatterns() {
		return patterns;
	}
	public void setPatterns(String patterns) {
		this.patterns = patterns;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Resources> getResources() {
		return resources;
	}
	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}
}
