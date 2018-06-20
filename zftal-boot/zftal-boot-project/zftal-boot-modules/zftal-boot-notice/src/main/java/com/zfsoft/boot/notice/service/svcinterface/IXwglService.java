/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.notice.service.svcinterface;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.notice.dao.entities.XwglModel;

public interface IXwglService extends BaseService<XwglModel>{
	
	public static final String SFFB_WFB="0";//是否发布：未发布
	public static final String SFFB_YFB="1";//是否发布：已发布
	
	public static final String SFZD_WZD="0";//是否置顶：未置顶
	public static final String SFZD_YZD="1";//是否置顶：已置顶
	
	public static final String FBDX_TRECHER="tea";//发布对象：老师
	public static final String FBDX_STUDENT="stu";//发布对象：学生
	
	/**
	 *删除新闻
	 * @param bean
	 * @return
	 */
	public boolean scXw(String idStr) ;
	
	
	/**
	 * 发布新闻
	 * 
	 * @param bean
	 * @return
	 */
	public boolean xgFbxw(String idStr) ;
	
	
	/**
	 * 取消发布新闻
	 * 
	 * @param bean
	 * @return
	 */
	public boolean xgQxfbxw(String idStr) ;
	
	/**
	 * 置顶新闻
	 * 
	 * @param bean
	 * @return
	 */
	public boolean xgZdxw(String idStr) ;
	
	/**
	 * 取消置顶新闻
	 * 
	 * @param bean
	 * @return
	 */
	public boolean xgQxzdXw(String idStr) ;
	
	/**
	 * 增加保存新闻
	 * @param model
	 * @return
	 */
	public boolean zjBcXw(XwglModel model);
	
	/**
	 * 修改保存新闻
	 * @param model
	 * @return
	 */
	public boolean xgBcXw(XwglModel model);
}
