/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.svcinterface;

import java.util.List;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;

public interface IApiDeployService extends BaseService<ApiDeployModel> {
	
	/**
	 * 
	 * @description	： 发布接口：创建新的接口发布信息并注册实体对象到上下文
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月12日 下午2:23:56
	 * @param apiInfoModel
	 */
	public boolean newDeploy(ApiDeployModel deployModel) ;
	
	/**
	 * 
	 * @description	： 发布接口：注册实体对象到上下文
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月12日 下午2:23:56
	 * @param apiInfoModel
	 */
	public boolean deploy(ApiDeployModel deployModel) ;
	
	/**
	 * 
	 * @description	： 重新发布接口：重新注册实体对象到上下文
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月12日 下午2:24:16
	 * @param apiInfoModel
	 */
	public boolean redeploy(ApiDeployModel deployModel) ;
	
	/**
	 * 
	 * @description	：  取消发布接口：从上下文删除实体对象
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月12日 下午2:24:36
	 * @param apiInfoModel
	 */
	public boolean undeploy(ApiDeployModel deployModel) ;

	/**
	  *@description:计算该业务系统接口发布的版本信息 
	  *@author ：杨碧琳(1422)
	  *@date   ： 2017年10月13日 
	  *@param id
	  */
	public ApiDeployModel getVersions(String id);
	
	/**
	  *@description:判断发布接口地址是否已经存在
	  *@author 杨碧琳(1422)
	  *@date   ： 2017年10月13日 
	  *@param addr
	  */
	public int checkAddr(String addr);
	
	/***
	 * 
	 * @description	： 查询已发布的接口信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月16日 上午9:55:37
	 * @param apiInfoModel
	 * @return
	 */
	public QueryModel getPagedSearchList(ApiDeployModel apiInfoModel);
	/**
	 * 
	 * @description	： 查询一条发布信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月19日 上午11:48:33
	 * @param deployId 发布ID
	 * @param flag 是否返回子信息
	 * @return
	 */
	public ApiDeployModel findDeployById(String deployId,boolean flag);

	public void online(ApiDeployModel deployModel);
	
	public void offline(ApiDeployModel deployModel);
	/**
	 * 查询图标数据
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月24日 下午5:30:11
	 * @param deployId
	 * @return
	 */
	public byte[] getIcon(String deployId);
	/**
	 * @description	： 查询下拉框数据
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年11月18日 下午4:06:13
	 * @param deployModel
	 * @return
	 */
	public List<ApiDeployModel> getComboList(ApiDeployModel deployModel);
}
