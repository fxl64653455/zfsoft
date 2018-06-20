package com.zfsoft.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.basemodel.BaseModel;

/**
 * 
 * @className	： BaseServiceImpl
 * @description	： 通用Service实现<br>
 * 说明：daoBase自动注入，不能存在多个实例
 * @author 		：万大龙（743）
 * @date		： 2017年10月11日 下午2:35:19
 * @version 	V1.0 
 * @param <T>
 * @param <E>
 */
public class BaseServiceImpl<T, E extends BaseDao<T>> extends BaseAwareService implements BaseService<T> {

	protected static Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	@Autowired
	protected E dao;
	
	/**
	 * 用于spring注入
	 * 
	 * @param dao
	 */
	public void setDao(E dao) {
		this.dao = dao;
	}
	
	/**
	 * 增加记录
	 * @param t
	 * @return
	 */
	public boolean insert(T t){
		int result = dao.insert(t);
		return result > 0;
	}

	/**
	 * 修改记录
	 * @param t
	 * @return
	 */
	public boolean update(T t){
		int result = dao.update(t);
		return result > 0;
	}

	public boolean delete(T t){
		int result = dao.delete(t);
		return result > 0 ? true : false;
	}
	
	/**
	 * 查询单条数据
	 * @param id
	 * @return
	 */
	public T getModel(String id){
		return dao.getModel(id);
	}

	/**
	 * 查询单条数据
	 * @param t
	 * @return
	 */	
	public T getModel(T t){
		return dao.getModel(t);
	}

	/**
	 * 批量删除
	 * @param map
	 * @return
	 */	
	public boolean batchDelete(Map<String, Object> map){
		int result = dao.batchDelete(map);
		return result > 0;
	}
	
	/**
	 * 批量删除
	 * @param list
	 * @return
	 */
	public boolean batchDelete(List<?> list){
		int result = dao.batchDelete(list);
		return result > 0;
	}

	/**
	 * 批量删除
	 * @param map
	 * @return
	 */
	public boolean batchUpdate(Map<String, Object> map){
		int result = dao.batchUpdate(map);
		return result > 0;
	}
	
	@Override
	public boolean batchUpdate(List<T> list) {
		int result = dao.batchUpdate(list);
		return result > 0 ? true : false;
	}
	
	@Override
	public boolean batchUpdateWrap(T model) {
		int result = dao.batchUpdateWrapByScope(model);
		return result > 0 ? true : false;
	}
	
	/**
	 * 分页查询
	 * @param t
	 * @return
	 */
	public List<T> getPagedList(T t){
		return dao.getPagedList(t);
	}

	/**
	 * 无分页查询
	 * @param t
	 * @return
	 */
	public List<T> getModelList(T t){

		return dao.getModelList(t);
	}

	/**
	 * 无分页查询
	 * @param str
	 * @return
	 */
	public List<T> getModelList(String... str){

		return dao.getModelList(str);
	}

	/**
	 * 统计记录数
	 * @param t
	 * @return
	 */
	public int getCount(T t){
		return dao.getCount(t);
	}

	/**
	 * 按数据范围分页查询
	 * @param t
	 * @return
	 */
	public List<T> getModelListByScope(T t) {
		 
		return dao.getModelListByScope(t);
	}

	/**
	 * 按数据范围无分页查询
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<T> getPagedByScope(T t) {
		
		return dao.getPagedByScope(t);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.zfsoft.common.service.BaseService#getList(java.lang.Object)
	 */
	public List<T> getList(T t) {
		if (t instanceof BaseModel){
			((BaseModel) t).getQueryModel().setPageNo(0);
			((BaseModel) t).getQueryModel().setPageSize(Integer.MAX_VALUE);
			return getPagedList(t);
		}
		return getModelList(t);
	}
	
	public String getValue(String key){
		return dao.getValue(key);
	}
	
	public Map<String, String> getValues(String key){
		return dao.getValues(key);
	}

	public E getDao() {
		return dao;
	}
	
}
