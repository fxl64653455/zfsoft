/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.handler;

import java.util.Map;

/**
 * 
 * @className	： ApiHandlerExec
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年10月27日 上午11:13:50
 * @version 	V1.0
 */
public interface ApiHandlerExec {
	
	/**
	 * 根据发布ID和调用接口传入的参数执行我们自己的处理逻辑
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月27日 上午11:14:51
	 * @param deploy 接口发布ID
	 * @param par 传入的参数
	 * @return
	 * @throws Exception
	 */
	public Object exec(String deployId,Map<String, Object> par) throws Exception;
	
	/**
	 * @description	： 根据接口ID和调用接口传入的参数执行我们自己的处理逻辑
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年12月18日 下午1:51:16
	 * @param apiId 接口ID
	 * @param par 传入的参数
	 * @return
	 * @throws Exception
	 */
	public Object debugExec(String apiId,Map<String, Object> par);
	
}
