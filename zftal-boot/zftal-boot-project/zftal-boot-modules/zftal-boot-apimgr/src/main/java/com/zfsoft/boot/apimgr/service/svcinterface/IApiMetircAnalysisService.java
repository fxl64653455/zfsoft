/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.svcinterface;

import java.util.List;
import java.util.Map;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.apimgr.dao.entities.ApiMetircModel;

/**
 * 
 * @className	： IApiMetircAnalysisService
 * @description	： 接口监控数据分析Service接口
 * @author 		：万大龙（743）
 * @date		： 2017年11月28日 下午5:36:38
 * @version 	V1.0
 */
public interface IApiMetircAnalysisService extends BaseService<ApiMetircModel>{

	/**
	 * 
	 * @description	： 客户端调用接口中各接口占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> ratio(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口排名前5条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:17
	 * @param model
	 * @return
	 */
	List<Map<String, String>> top5(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类业务占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> top10(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口排名前50条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> top50(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口排名前100条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> top100(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类应用占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> appRatio(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类应用日访问量(默认最近7天)
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> appDaily(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类应用List
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> appList(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类应用排名前5条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> appTop5(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类应用排名前10条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> appTop10(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类业务占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> bizRatio(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类业务日访问量(默认最近7天)
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> bizDaily(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类业务List
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> bizList(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类业务排名前5条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> bizTop5(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类业务排名前10条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> bizTop10(ApiMetircModel model);

	
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类设备类型占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> deviceRatio(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类设备类型日访问量(默认最近7天)
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> deviceDaily(ApiMetircModel model);
	
	/**
	 * 
	 * @description	：客户端调用接口中各类设备类型List
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> deviceList(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类设备排名前5条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> deviceTop5(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类设备排名前10条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> deviceTop10(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类操作系统占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> osRatio(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类操作系统日访问量(默认最近7天)
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> osDaily(ApiMetircModel model);

	/**
	 * 
	 * @description	：客户端调用接口中各类操作系统List
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> osList(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类操作系统排名前5条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> osTop5(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类操作系统排名前10条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> osTop10(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类浏览器占比
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> browserRatio(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类浏览器日访问量(默认最近7天)
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> browserDaily(ApiMetircModel model);
	
	/**
	 * 
	 * @description	：客户端调用接口中各类浏览器List
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> browserList(ApiMetircModel model);
	
	/**
	 * 
	 * @description	： 客户端调用接口中各类浏览器排名前5条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> browserTop5(ApiMetircModel model);

	/**
	 * 
	 * @description	： 客户端调用接口中各类浏览器排名前10条记录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月28日 下午6:26:52
	 * @param model
	 * @return
	 */
	List<Map<String, String>> browserTop10(ApiMetircModel model);


	
}
