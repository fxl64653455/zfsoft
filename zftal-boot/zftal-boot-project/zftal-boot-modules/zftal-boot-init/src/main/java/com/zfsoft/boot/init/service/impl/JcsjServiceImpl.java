package com.zfsoft.boot.init.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.init.dao.daointerface.IJcsjDao;
import com.zfsoft.boot.init.dao.entities.JcsjModel;
import com.zfsoft.boot.init.service.svcinterface.IJcsjService;
import com.zfsoft.taglibs.data.TagDataProvider;


/**
 * 
* 
* 类名称：JcsjServiceImpl 
* 类描述： 基础数据业务处理实现类
* 创建人：xucy 
* 创建时间：2012-4-13 下午01:44:39 
* 修改人：xucy 
* 修改时间：2012-4-13 下午01:44:39 
* 修改备注： 
* @version 
*
 */
@Service("jcsjService")
public class JcsjServiceImpl extends BaseServiceImpl<JcsjModel, IJcsjDao> implements IJcsjService,TagDataProvider<JcsjModel> {
	
	//查询基础数据列表(不分页用于导出)
	public List<JcsjModel> cxJcsjList(JcsjModel model){
		return dao.cxJcsjList(model);
	}

	//删除基础数据
	public int[] scJcsj(JcsjModel model) {
		int deleted = 0;
		int cannotDelete = 0;
		String pkValue = model.getPkValue();
		String[] pks = null;
		if(null != pkValue){
			pks = pkValue.split(",");
			List<JcsjModel> list =new ArrayList<JcsjModel>();
			for (int i = 0; i < pks.length; i++) {
				JcsjModel jcsjmodel= new JcsjModel(pks[i]);
				if(canDelete(dao.getModel(jcsjmodel))){
					list.add(jcsjmodel);
				}else{
					cannotDelete++;
				}
			}
			if(list.size() > 0){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("list", list);
				deleted = dao.batchDelete(param);
			}
		}
		return new int[]{deleted,cannotDelete};
	}
	
	@Override
	public boolean canDelete(JcsjModel model) {
		if(XTJB_XT.equals(model.getXtjb())){
			return false;
		}
		String yz = model.getYz();
		if(StringUtils.isBlank(yz)){
			return true;
		}
		
		String zdz = model.getDm();
		String bm = StringUtils.splits(yz, ":")[0];
		String zdm = StringUtils.splits(yz, ":")[1];
		
		if(StringUtils.isBlank(bm) || StringUtils.isBlank(zdm)){
			return true;
		}
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("bm", bm);
		params.put("zdm", zdm);
		params.put("zdz", zdz);
		
		return dao.checkDeletable(params) < 1;
	}
	
	//根据类型代码查询基础数据
	public List<JcsjModel> getJcsjList(String lxdm){
		return dao.getJcsjList(lxdm);
	}
	
	//根据类型代码查询基础数据
	public List<HashMap<String, String>> getJcsjHashMap(String lxdm){
		return dao.getJcsjHashMap(lxdm);
	}
	
	/**
	 * 方法描述：获取单个基础数据
	 * @param lx	类型
	 * @param dm	代码
	 * @return
	 */
	public JcsjModel getJcsjModel(String lx, String dm) {
		if(StringUtils.isEmpty(lx) || StringUtils.isEmpty(dm)){
			return null;
		}
		JcsjModel model=new JcsjModel();
		model.setPkValue(lx+dm);
		return dao.getModel(model);
	}

	@Override
	public List<JcsjModel> getDataList(JSONObject json) {
		String lxdm = json.getString("lxdm");
		return getJcsjList(lxdm);
	}
	
}
