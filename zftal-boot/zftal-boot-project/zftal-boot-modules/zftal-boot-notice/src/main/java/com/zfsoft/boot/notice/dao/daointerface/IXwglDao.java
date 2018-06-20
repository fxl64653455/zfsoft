/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.notice.dao.daointerface;


import org.apache.ibatis.annotations.Mapper;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.notice.dao.entities.XwglModel;


/**
 * @className	： IXwglDao
 * @description	： 新闻管理
 * @author 		：万大龙（743）
 * @date		： 2018年4月10日 下午2:58:39
 * @version 	V1.0
 */
@Mapper
public interface IXwglDao extends BaseDao<XwglModel>{
	
	public void getList();
	
}
