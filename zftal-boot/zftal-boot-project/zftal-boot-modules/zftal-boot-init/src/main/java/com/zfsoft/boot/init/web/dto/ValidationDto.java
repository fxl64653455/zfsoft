package com.zfsoft.boot.init.web.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.zfsoft.basemodel.BaseModel;
import com.zfsoft.basemodel.PairModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 *@类名称:ValidationModel.java
 *@类描述：公共验证模型
 *@创建人：zhangxiaobin
 *@版本号:v1.0
 */
@SuppressWarnings("serial")
@ApiModel(value = "ValidationDto", description = "唯一校验数据传输对象")
public class ValidationDto extends BaseModel {

	/* 主键名称 */
	@ApiModelProperty(value = "主键名称 ")
	@NotBlank(message="主键名称必填")
	private String filed_name;
	/* 主键值 */
	@ApiModelProperty(value = "主键值")
	@NotBlank(message="主键值必填")
	private String filed_value;
	/* 主键原值：更新时用到 */
	private String old_filed_value;
	/* 其他级联字段 */
	private List<PairModel> filed_list;
	private List<PairModel> old_filed_list;
	/* 表名称 */
	private String table;
	
	public String getFiled_name() {
		return filed_name;
	}

	public void setFiled_name(String filedName) {
		filed_name = filedName;
	}

	public String getFiled_value() {
		return filed_value;
	}

	public void setFiled_value(String filedValue) {
		filed_value = filedValue;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<PairModel> getFiled_list() {
		return filed_list;
	}

	public void setFiled_list(List<PairModel> filedList) {
		filed_list = filedList;
	}

	public String getOld_filed_value() {
		return old_filed_value;
	}

	public void setOld_filed_value(String oldFiledValue) {
		old_filed_value = oldFiledValue;
	}

	public List<PairModel> getOld_filed_list() {
		return old_filed_list;
	}

	public void setOld_filed_list(List<PairModel> oldFiledList) {
		old_filed_list = oldFiledList;
	}

	
	
}
