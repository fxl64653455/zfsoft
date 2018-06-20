package com.zfsoft.boot.runenv.task;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOClient;
import com.zfsoft.boot.socketio.handler.AbstractSocketEventHandler;
import com.zfsoft.metrics.jmx.JVMInfo;
import com.zfsoft.metrics.jmx.JVMProperty;
import com.zfsoft.metrics.utils.CapacityUtils.Unit;

//@Component
public class JVMWatchSocketEventHandler extends AbstractSocketEventHandler {
	
	protected final Logger LOG = LoggerFactory.getLogger(JVMWatchSocketEventHandler.class);
	
	public JVMWatchSocketEventHandler() {
	}
	
	/**
	 * 缓存平均命中率、丢失率,堆内、堆外、磁盘命中率
	 */
	@Scheduled(fixedDelay = 3000) // 每3s执行1次
	public void jvmTask(){
		try {
			
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			Map<String, Object> jmxMap = new HashMap<String, Object>();
			
			//Java运行时信息
			jmxMap.putAll(JVMInfo.info());
			long estimatedTime = System.currentTimeMillis() - Long.parseLong(String.valueOf(jmxMap.get(JVMProperty.JAVA_RUNTIME_STARTTIME.getKey())));
			jmxMap.put(JVMProperty.JAVA_RUNTIME_STARTTIME.getKey(), estimatedTime / 1000 / 60);
			
			dataMap.put("jvm", jmxMap);
			//Java回收器信息
			dataMap.put("gcs", JVMInfo.gc());
			//Java内存信息
			dataMap.put("memory", JVMInfo.memory(Unit.MB));
			//Java内存池信息
			dataMap.put("memoryPool", JVMInfo.memoryPool(Unit.MB));
			
			/*// 给所有在线客户端发送数据
			for (SocketIOClient client : getClients().values()) {
				client.set("onJVMStats", dataMap);
			}*/
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

}
