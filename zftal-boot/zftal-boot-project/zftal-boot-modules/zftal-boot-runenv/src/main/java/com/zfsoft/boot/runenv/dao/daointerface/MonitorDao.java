package com.zfsoft.boot.runenv.dao.daointerface;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.runenv.dao.entities.MonitorModel;

@Mapper
public interface MonitorDao extends BaseDao<MonitorModel> {

	void insertUsage(List<MonitorModel> list);

	void insertMemory(List<MonitorModel> list);

	List<Map<String, Object>> getHistoryMemory(@Param("begin") String begin);
	
	List<Map<String, Double>> getHistoryUsage(@Param("begin") String begin);
	
}
