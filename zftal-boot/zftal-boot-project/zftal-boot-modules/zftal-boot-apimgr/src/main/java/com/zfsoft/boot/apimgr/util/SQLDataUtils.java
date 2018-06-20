package com.zfsoft.boot.apimgr.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.Predicate;

import com.zfsoft.basicutils.ArrayUtils;
import com.zfsoft.basicutils.BlankUtils;
import com.zfsoft.basicutils.CollectionUtils;
import com.zfsoft.db.core.utils.JSQLParserUtils;

import net.sf.jsqlparser.JSQLParserException;

public abstract class SQLDataUtils {

	public static List<Map<String,String>> getDataColumnList(String data_sql,String ...filters ) throws JSQLParserException {
		List<Map<String,String>> dataColumnList = new ArrayList<Map<String,String>>();
		//解析查询列
		List<String> selectItems =  JSQLParserUtils.getSelectItems(data_sql);
		if(!BlankUtils.isBlank(selectItems)){
			Map<String,String> row = null;
			int index = 0;
			
			for (String field_name : selectItems) {
				//进行字段过滤
				if( filters != null && ArrayUtils.contains(filters, field_name)){
					continue;
				}
				row =  new HashMap<String, String>();
				row.put("field_guid", "field" + index);
				row.put("field_name", field_name);
				row.put("field_index", field_name);
				dataColumnList.add(row);
				index ++;
			}
		}
		return dataColumnList;
	}
	
	public static List<Map<String,String>> getDataColumnList(final List<String> selectItems,List<Map<String,String>> tableColumnList,String ...filters ) {
		List<Map<String,String>> dataColumnList = new ArrayList<Map<String,String>>();
		try {
			if(!BlankUtils.isBlank(selectItems)){
				Map<String,String> row = null;
				int index = 0;
				//筛选查询字段匹配的表字段
				List<Map<String,String>> tableColumnList_filter = CollectionUtils.findAll(tableColumnList, new Predicate() {
					@Override
					public boolean evaluate(Object object) {
						@SuppressWarnings("unchecked")
						Map<String,String> columnMap = (Map<String,String>)object;
						return CollectionUtils.containsIgnoreCase(selectItems, columnMap.get("COLUMN_NAME"));
					}
				});
				//循环查询字段
				for (String field_name : selectItems) {
					//进行字段过滤
					if( filters != null && ArrayUtils.containsIgnoreCase(filters, field_name)){
						continue;
					}
					row =  new HashMap<String, String>();
					row.put("COLUMN_GUID", "field" + index);
					row.put("COLUMN_NAME", field_name);
					row.put("COLUMN_INDEX", field_name);
					row.put("COMMENTS", "无");
					for (Map<String,String> columnMap : tableColumnList_filter) {
						if(field_name.equalsIgnoreCase(columnMap.get("COLUMN_NAME") != null ? columnMap.get("COLUMN_NAME").toString() : "")){
							row.put("COMMENTS", columnMap.get("COMMENTS") != null ? columnMap.get("COMMENTS").toString() : "" );
							break;
						}
					}
					dataColumnList.add(row);
					index ++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dataColumnList;
	}
	
	public static int getFileCount(int count,int batch_size){
		//得到当前sheet 的总行数，并计算当前sheet需要多少个线程进行导入操作
		int thread_num = 1;
		if(count > batch_size){
			thread_num = (count - count%batch_size)/batch_size;
			if(count%batch_size>0){
				thread_num = thread_num+1;
			}
		}
		return thread_num;//Double.valueOf(Math.ceil(count/batch_size)).intValue();
	}
}
