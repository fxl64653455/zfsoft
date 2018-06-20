/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiAppModel;

/**
 * 
 * @className	： IApiAppDao
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月28日 下午3:28:50
 * @version 	V1.0
 */
@Mapper
public interface IApiAppDao extends BaseDao<ApiAppModel>{
	
	/**
	 * 获取接口申请信息
	 * @description	： 获取接口申请信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午5:53:49
	 * @param appName
	 * @param appOwner
	 * @param apiId
	 * @return
	 */
	public List<ApiAppModel> getPagedApplyTableData(ApiAppModel app);
	
	/**
	 * @description	： 根据标识删除菜单
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月16日 下午4:09:01
	 * @param dm 标识
	 * @return
	 */
	int deleteMenu(String dm);
	
	/**
	 * @description	： 根据标识返回是否存在
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月16日 下午4:22:51
	 * @param dm
	 * @return
	 */
	int hasMenu(String dm);
	
	/**
	 * @description	： 添加应用菜单
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月16日 下午4:20:32
	 * @return
	 */
	int addAppMenu();
}
