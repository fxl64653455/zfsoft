/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.handler;

import java.io.IOException;
import java.util.Map;

/**
 * 
 * @className	： ApiProxyHandler
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年10月18日 下午6:00:08
 * @version 	V1.0
 */
public interface ApiProxyHandler {
	
	/**
	 * 执行方法之前做的事情 如果返回false跳过 exec 和 after
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月18日 下午2:56:39
	 * @param deployId 接口发布ID
	 * @param map 调用接口时传入的参数
	 * @return
	 */
	public boolean before(String deployId,Map<String, Object> map) throws Exception;
	/**
	 * 调用接口做的事情
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月18日 下午2:56:45
	 * @param deployId 接口发布ID
	 * @param map 调用接口时传入的参数
	 * @return
	 * @throws IOException 
	 */
	public Object exec(String deployId,Map<String, Object> map) throws Exception;
	/**
	 * 调用接口之后做的事情
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月18日 下午2:56:49
	 * @param deployId 接口发布ID
	 * @param map 调用接口时传入的参数
	 * @param res exec返回的结果
	 * @param ex exec执行时发生异常
	 * @param startTime 接口调用开始时刻
	 */
	public void after(String deployId,Map<String, Object> map,Object res, Exception ex, long startTime);
}
