/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.basicutils.CollectionUtils;
import com.zfsoft.basicutils.StringEscapeUtils;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.druid.ds.JDBCDriverEnum;
import com.zfsoft.boot.druid.ds.JDBCUtils;
import com.zfsoft.boot.apimgr.dao.entities.ApiDatabaseModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDatabaseService;
import com.zfsoft.boot.apimgr.setup.data.LogType.Czmk;
import com.zfsoft.boot.apimgr.util.enums.DataExchangeTypeEnum;
import com.zfsoft.boot.apimgr.web.dto.ApiDataBaseDto;
import com.zfsoft.boot.apimgr.web.dto.ApiDatasourceDto;
import com.zfsoft.boot.apimgr.web.dto.InputColumn;
import com.zfsoft.security.algorithm.DesBase64Codec;
import com.zfsoft.webmvc.controller.BaseController;

import freemarker.core.InvalidReferenceException;
import freemarker.core.NonNumericalException;
import freemarker.core.ParseException;
import net.sf.jsqlparser.JSQLParserException;

/**
 * 
 * @className	： ApiDatabaseController
 * @description	： 接口数据库Controller
 * @author 		：万大龙（743）
 * @date		： 2017年9月22日 下午1:38:44
 * @version 	V1.0
 */
@Controller	
@RequestMapping("/manager/api/database/")
public class ApiDatabaseController extends BaseController{
	
	@Autowired
	private IApiDatabaseService apiDatabaseService;
	@Autowired
	private DesBase64Codec dbencrypt;
	
	/**
	 * 
	 * @description	： 接口数据来源功能首页
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月17日 上午8:54:45
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "index", produces = "text/html; charset=UTF-8")
    public String index(HttpServletRequest request, Model model) throws Exception {
		//查询当前用户的数据源记录
		model.addAttribute("driverList", JDBCDriverEnum.driverList());
    	return "html/api/database/index";
    }
	
	/*@ApiOperation(value = "数据库配置信息查询", notes = "分页查询信息", httpMethod = "POST")
	@ApiImplicitParam(name = "dbDto", value = "数据库配置信息", required = true, dataType = "ApiDataBaseDto")*/
	@BusinessLog(czmk = Czmk.N0601, ywmc = "我的数据库", czms = "查询我的数据库配置信息", czlx = BusinessType.SELECT)
    @PostMapping(value = "list")
    @ResponseBody
    public Object dbList(ApiDatabaseModel dbModel, HttpServletRequest request) throws Exception {
    	try {
    		
    		/*ApiDatabaseModel dbModel = new ApiDatabaseModel();
    		PropertyUtils.copyProperties(dbModel, dbDto);*/
    		dbModel.setOwner(getUser().getYhm());
    		
    		QueryModel queryModel =	dbModel.getQueryModel();
    		
    		List<ApiDatabaseModel> list = getApiDatabaseService().getPagedList(dbModel);
    		for (ApiDatabaseModel apiModel : list) {
    			apiModel.setUrl(StringEscapeUtils.unescapeHtml(apiModel.getUrl()));
    			apiModel.setDbTypeDesc(JDBCDriverEnum.driver(apiModel.getDbtype()).getDesc());
			}
    		
    		queryModel.setItems(list);
    		return queryModel;
    	} catch (Exception e) {
			logException(this, e);
			return errorStatus(); 
		}
    }
	
	/*@ApiOperation(value = "", notes = "数据库配置有效性测试", httpMethod = "POST")
	@ApiImplicitParam(name = "dbDto", value = "数据库配置信息", required = true, dataType = "ApiDataBaseDto")*/
	@PostMapping(value = "test")
    @ResponseBody
    public Object dbtest(@Valid ApiDataBaseDto dbDto, HttpServletRequest request) throws Exception {
    	try {
    		String url = StringEscapeUtils.unescapeHtml(dbDto.getUrl());
    		boolean ok = JDBCUtils.testConnection(JDBCUtils.getDriverClass(dbDto.getDbtype()), url, dbDto.getUsername(), dbDto.getPassword());
    		if(!ok) {
    			return ResultUtils.statusMap(STATUS_FAIL, "数据库无法连接！");
    		}
    		return ResultUtils.statusMap(STATUS_SUCCESS, "数据库连接成功！");
    	} catch (Exception e) {
			logException(this, e);
			return ResultUtils.statusMap(STATUS_FAIL, "数据库无法连接！");
		}
    }
    
	@PostMapping(value = "testConnection")
    @ResponseBody
    public Object testConnection(ApiDataBaseDto dbDto, HttpServletRequest request) throws Exception {
    	try {
    		String url = StringEscapeUtils.unescapeHtml(dbDto.getUrl());
			String encryptKeyText = Base64.encodeBase64String(dbDto.getUrl().getBytes());
    		boolean ok = JDBCUtils.testConnection(JDBCUtils.getDriverClass(dbDto.getDbtype()), url, 
					dbencrypt.decrypt(dbDto.getUsername().getBytes(), encryptKeyText), 
					dbencrypt.decrypt(dbDto.getPassword().getBytes(), encryptKeyText));
    		if(!ok) {
    			return ResultUtils.statusMap(STATUS_FAIL, dbDto.getId());
    		}
    		return ResultUtils.statusMap(STATUS_SUCCESS, dbDto.getId());
    	} catch (Exception e) {
			logException(this, e);
			return ResultUtils.statusMap(STATUS_FAIL, dbDto.getId());
		}
    }
	
    @RequestMapping(value = "to/new-db", produces = "text/html; charset=UTF-8")
    public String toNewDatabase(HttpServletRequest request, Model model) throws Exception {
    	model.addAttribute("driverList", JDBCDriverEnum.driverList());
    	return "html/api/database/new-database"; 
    }
    
    @BusinessLog(czmk = Czmk.N0601, ywmc = "我的数据库", czms = "新增我的数据库配置信息", czlx = BusinessType.INSERT)
    @PostMapping(value = "new/form")
    @ResponseBody
    public Object newDatabase(@Valid ApiDataBaseDto db,HttpServletRequest request, BindingResult result) throws Exception {
    	try {
    		if (result.hasErrors()) {
    			return ResultUtils.statusMap(STATUS_FAIL, result.getFieldError().getDefaultMessage());
    		}
    		ApiDatabaseModel dbModel = new ApiDatabaseModel();
    		PropertyUtils.copyProperties(dbModel, db);
    		dbModel.setOwner(getUser().getYhm());
    		//使用数据库URL生成Base64秘钥
    		String encryptKeyText = Base64.encodeBase64String(dbModel.getUrl().getBytes());
    		//进行数据库账号密码加密
    		dbModel.setUsername(dbencrypt.encrypt(dbModel.getUsername(), encryptKeyText));
    		dbModel.setPassword(dbencrypt.encrypt(dbModel.getPassword(), encryptKeyText));
    		//新增一条数据库配置记录
    		getApiDatabaseService().insert(dbModel);
    		
    		return successStatus("I99001");
    	} catch (Exception e) {
			logException(this, e);
			return failStatus("I99002");
		}
    }
    
    @RequestMapping(value = "to/edit-db", produces = "text/html; charset=UTF-8")
    public String toEditDatabase(@RequestParam("id") String id, Model model) throws Exception {
    	
    	ApiDatabaseModel rtModel = getApiDatabaseService().getModel(id);
    	
    	//使用数据库URL生成Base64秘钥
		String encryptKeyText = Base64.encodeBase64String(rtModel.getUrl().getBytes());
    	//进行数据库账号密码解密
    	rtModel.setUsername(dbencrypt.decrypt(rtModel.getUsername().getBytes(), encryptKeyText));
    	rtModel.setPassword(dbencrypt.decrypt(rtModel.getPassword().getBytes(), encryptKeyText));
    	
    	model.addAttribute("dbModel",rtModel);
    	model.addAttribute("driverList", JDBCDriverEnum.driverList());
    	return "html/api/database/edit-database"; 
    }
    
    @BusinessLog(czmk = Czmk.N0601, ywmc = "我的数据库", czms = "编辑我的数据库配置信息", czlx = BusinessType.UPDATE)
    @PostMapping(value = "edit/form")
    @ResponseBody
    public Object editDatabase(@Valid ApiDataBaseDto db,HttpServletRequest request, BindingResult result) throws Exception {
    	try {
    		if (result.hasErrors()) {
    			return ResultUtils.statusMap(STATUS_FAIL, result.getFieldError().getDefaultMessage());
    		}
    		ApiDatabaseModel dbModel = new ApiDatabaseModel();
    		PropertyUtils.copyProperties(dbModel, db);
    		
    		//使用数据库URL生成Base64秘钥
    		String encryptKeyText = Base64.encodeBase64String(dbModel.getUrl().getBytes());
    		//进行数据库账号密码加密
    		dbModel.setUsername(dbencrypt.encrypt(dbModel.getUsername(), encryptKeyText));
    		dbModel.setPassword(dbencrypt.encrypt(dbModel.getPassword(), encryptKeyText));
    		//修改一条数据库配置记录
    		getApiDatabaseService().update(dbModel);
    		return successStatus("I99003");
    	} catch (Exception e) {
			logException(this, e);
			return failStatus("I99004");
		}
    }
    
	@BusinessLog(czmk = Czmk.N0601, ywmc = "我的数据库", czms = "删除我的数据库配置信息", czlx = BusinessType.DELETE)
	@RequestMapping("delete")
    @ResponseBody
    @SuppressWarnings("unchecked")
	public Object delete(@RequestParam(value="ids") String ids,HttpServletRequest request){
    	try {
    		if(StringUtils.isEmpty(ids)) {
    			return failStatus("I00002");
    		}
    		List<String> list = CollectionUtils.arrayToList(StringUtils.tokenizeToStringArray(ids));
    		//查询要删除的数据对应的依赖
    		List<Map<String,String>> dependencies = getApiDatabaseService().getDependencies(list);
    		//不为空，则表示有数据在被使用
    		if(!CollectionUtils.isEmpty(dependencies)) {
    			StringBuilder builder = new StringBuilder("存在未解除的依赖关系：【");
    			for (Map<String, String> hashMap : dependencies) {
    				builder.append(hashMap.get("DB_NAME_CN")).append("(").append(hashMap.get("DB_NAME")).append(")").append(" >> ").append(hashMap.get("DATA_NAME")).append(",");
				}
    			builder.deleteCharAt(builder.length() - 1);
    			builder.append("】,无法删除.");
    			return ResultUtils.statusMap(STATUS_FAIL, builder.toString());
    		}
    		//批量删除数据库配置记录
    		getApiDatabaseService().batchDelete(list);
    		return successStatus("I99005");
    	} catch (Exception e) {
			logException(this, e);
			return failStatus("I99005");
		}
    }
    
    /**
     * 
     * @description	： 查询知道数据源下的数据表信息
     * @author 		： 万大龙（743）
     * @date 		：2017年10月19日 上午9:02:48
     * @param dbname
     * @return
     */
    @PostMapping(value = "table/names")
    @ResponseBody
	public Object tableNames(@RequestParam("dbname") String dbname) {
		try {
			if(StringUtils.isEmpty(dbname)) {
    			return CollectionUtils.EMPTY_COLLECTION;
    		}
			String dbtype = JDBCDriverEnum.ORACLE.getKey();
			if(!dbname.equals(ApiDatasourceDto.DEFAULT_DATASOURCE)) {
				dbtype = apiDatabaseService.getModelByDbName(dbname).getDbtype();
			}
			return getApiDatabaseService().getTableNameList(dbname,dbtype);
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
    
    /**
     * 
     * @description	： 查询指定数据源、数据表对应的列信息
     * @author 		： 万大龙（743）
     * @date 		：2017年10月19日 上午9:03:11
     * @param dbname
     * @param table
     * @return
     */
    @PostMapping(value = "table/columns")
    @ResponseBody
	public Object tableColumns(@RequestParam("dbname") String dbname,@RequestParam("table")String table) {
		try {
			if(StringUtils.isEmpty(dbname)) {
    			return CollectionUtils.EMPTY_COLLECTION;
    		}
			if(StringUtils.isEmpty(table)) {
    			return CollectionUtils.EMPTY_COLLECTION;
    		}
			String dbtype = JDBCDriverEnum.ORACLE.getKey();
			if(!dbname.equals(ApiDatasourceDto.DEFAULT_DATASOURCE)) {
				dbtype = apiDatabaseService.getModelByDbName(dbname).getDbtype();
			}
			return getApiDatabaseService().getTableColumnList(dbname,table,dbtype);
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
    
	/**
	 * 
	 * @description	： 解析SQL并返回对应的输入列信息
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月19日 上午9:03:44
	 * @param sql
	 * @return
	 */
    @PostMapping(value = "sql/columns")
    @ResponseBody
	public Object sqlColumns(@RequestParam("dbname") String dbname,@RequestParam("sql") String sql,@RequestParam("exchType") String exchType) {
		try {
			Map<String,Object> dataMap = new HashMap<String, Object>(); 
			if(StringUtils.isEmpty(sql)) {
				dataMap.put("input", CollectionUtils.EMPTY_COLLECTION);
				dataMap.put("update",CollectionUtils.EMPTY_COLLECTION);
				dataMap.put("output",CollectionUtils.EMPTY_COLLECTION);
    			return dataMap;
    		}
			String dbtype = JDBCDriverEnum.ORACLE.getKey();
			if(!dbname.equals(ApiDatasourceDto.DEFAULT_DATASOURCE)) {
				dbtype = apiDatabaseService.getModelByDbName(dbname).getDbtype();
			}
			// 防止SQL语句与数据交换类型不匹配,此处根据SQL语句来判断真实的交换类型，将真实数据交换类型返回前端，由前端动态调整元素的下拉选项
			InputColumn inputColumn = getApiDatabaseService().getSQLParserInputColumnList(dbname, StringEscapeUtils.unescapeHtml(sql), dbtype);
			dataMap.put("input", inputColumn.getInput());
			dataMap.put("exchType", null == inputColumn.getExchType() ? exchType : inputColumn.getExchType().getKey()); 
			
			if(DataExchangeTypeEnum.UPDATE.equals(inputColumn.getExchType())) {
				dataMap.put("update", getApiDatabaseService().getSQLParserUpdateColumnList(dbname, StringEscapeUtils.unescapeHtml(sql), dbtype));
				dataMap.put("output",CollectionUtils.EMPTY_COLLECTION);
			}
			else if(DataExchangeTypeEnum.QUERY.equals(inputColumn.getExchType())) {
				dataMap.put("update",CollectionUtils.EMPTY_COLLECTION);
				dataMap.put("output", getApiDatabaseService().getSQLParserOutputColumnList(dbname, StringEscapeUtils.unescapeHtml(sql), dbtype));
			}
			else {
				dataMap.put("update",CollectionUtils.EMPTY_COLLECTION);
				dataMap.put("output",CollectionUtils.EMPTY_COLLECTION);
			}
			return dataMap;
		} catch (Exception e) {
			if(e instanceof NonNumericalException || e instanceof ParseException || e instanceof InvalidReferenceException) {
				return ResultUtils.statusMap(STATUS_ERROR, getMessage("API-I000001", new Object[] {e.getMessage()}));
			}
			if(e instanceof JSQLParserException) {
				return ResultUtils.statusMap(STATUS_ERROR, getMessage("API-I000002", new Object[] {e.getCause().getMessage()}));
			}
			logException(e);
			return errorStatus();
		}
	}
    
	public IApiDatabaseService getApiDatabaseService() {
		return apiDatabaseService;
	}

	public void setApiDatabaseService(IApiDatabaseService apiDatabaseService) {
		this.apiDatabaseService = apiDatabaseService;
	}
	 
	
}