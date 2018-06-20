/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.daointerface;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiMetircModel;

/**
 * 
 * @className	： IApiMetircAnalysisDao
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月28日 下午5:35:37
 * @version 	V1.0
 */
@Mapper
public interface IApiMetircAnalysisDao extends BaseDao<ApiMetircModel>{
	
	/**
	 * 
	 * @description	： 客户端调用接口中各接口占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getRatio(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口排名前n条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:17
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getTop(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类应用占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getAppRatio(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类应用日访问量(默认最近7天)
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getAppDaily(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类应用List
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getAppList(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类应用排名前n条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getAppTop(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类业务占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getBizRatio(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类业务日访问量(默认最近7天)
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getBizDaily(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类业务List
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getBizList(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类业务排名前n条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getBizTop(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类设备类型占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getDeviceRatio(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类设备类型日访问量(默认最近7天)
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getDeviceDaily(ApiMetircModel model);
	
	/**
	 * 
	 * @description	：客户端调用接口中各类设备类型List
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getDeviceList(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类设备排名前n条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getDeviceTop(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类操作系统占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getOsRatio(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类操作系统日访问量(默认最近7天)
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getOsDaily(ApiMetircModel model);

	/**
	 * 
	 * @description	：客户端调用接口中各类操作系统List
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getOsList(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类操作系统排名前n条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getOsTop(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类浏览器占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getBrowserRatio(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类浏览器日访问量(默认最近7天)
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getBrowserDaily(ApiMetircModel model);
	
	/**
	 * 
	 * @description	：客户端调用接口中各类浏览器List
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getBrowserList(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类浏览器排名前n条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> getBrowserTop(ApiMetircModel model);

	
}
