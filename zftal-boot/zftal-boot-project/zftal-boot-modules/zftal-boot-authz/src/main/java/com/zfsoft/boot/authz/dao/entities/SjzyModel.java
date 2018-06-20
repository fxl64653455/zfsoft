package com.zfsoft.boot.authz.dao.entities;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

/**
 * 数据资源Model
 * @className	： SjzyModel
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2018年6月7日 下午4:23:02
 * @version 	V1.0
 */
@Alias(value="sjzyModel")
public class SjzyModel implements Serializable{

	private static final long serialVersionUID = 2945496300399140062L;

	private String zyid;
	private String zymc;
	private List<SjzygzModel> zygzList;
	
	public String getZyid() {
		return zyid;
	}
	public void setZyid(String zyid) {
		this.zyid = zyid;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public List<SjzygzModel> getZygzList() {
		return zygzList;
	}
	public void setZygzList(List<SjzygzModel> zygzList) {
		this.zygzList = zygzList;
	}
	
	
}
