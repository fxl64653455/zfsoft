/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.daointerface;

import org.apache.ibatis.annotations.Mapper;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiAuditModel;

/**
 * 
 * @className	： IApiAudit
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月28日 下午3:30:29
 * @version 	V1.0
 */
@Mapper
public interface IApiAuditDao extends BaseDao<ApiAuditModel>{

}
