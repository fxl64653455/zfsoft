/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.handler;

/**
 * 
 * @className	： WsApiHandler
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年10月26日 下午3:15:52
 * @version 	V1.0
 */
public interface WsApiHandler {
	
	/**
	 * 业务逻辑之前的事情（目前有黑名单及针对axis客户端的权限验证）
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月30日 上午9:39:44
	 * @param deployId 发布ID
	 * @param type 客户端类型(Axis/Cxf) -- 此参数已失效
	 * @param pKeys 参数名称数组
	 * @param values 参数值 (如果客户端类型为 Axis,则最后一个参数为token)
	 * @return
	 * @throws Exception
	 */
	public boolean before(String deployId,String type,String[] pKeys, Object[] values) throws Exception;
	
	/**
	 * 执行业务处理逻辑
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月30日 上午9:42:45
	 * @param deployId 发布ID
	 * @param type 客户端类型 -- 此参数已失效
	 * @param pKeys 参数名称数组
	 * @param values 参数值 (如果客户端类型为 Axis,则最后一个参数为token)
 	 * @return
	 * @throws Exception
	 */
	public Object exec(String deployId,String type,String[] pKeys,Object[] values) throws Exception;
	
	/**
	 * 执行业务处理逻辑之后
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月30日 上午9:44:34
	 * @param deployId 发布ID
	 * @param type 客户端类型 -- 此参数已失效
	 * @param pKeys 参数名称数组
	 * @param values 参数值 (如果客户端类型为 Axis,则最后一个参数为token)
	 * @param res 执行业务处理返回值
	 * @param ex 执行业务处理时发生异常
	 * @param startTime 接口调用开始时刻
	 */
	public void after(String deployId,String type,String[] pKeys,Object[] values,Object res, Exception ex, long startTime);
	
}
