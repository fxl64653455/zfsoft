package com.zfsoft.boot.runenv.web.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.conf.PropertiesProvider;
import com.zfsoft.boot.runenv.provider.MemoryInfoProvider;
import com.zfsoft.boot.runenv.provider.MemoryInfoProviderManager;
import com.zfsoft.metrics.jmx.JVMInfo;
import com.zfsoft.metrics.jmx.JVMProperty;
import com.zfsoft.metrics.utils.CapacityUtils;
import com.zfsoft.metrics.utils.CapacityUtils.Unit;
import com.zfsoft.webmvc.controller.BaseController;

@Controller
@RequestMapping(value = "/monitor/jvm/")
public class JVMManagerController extends BaseController {
	
	@Autowired
	protected Sigar sigar;
	@Autowired
	protected MemoryInfoProviderManager providerManager;
	@Autowired
	@Qualifier("wacthPropsProvider")
	protected PropertiesProvider propsProvider;
	
	@RequiresRoles({"admin"})
	@RequiresPermissions("monitor-watch:cx")
	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) throws Exception {
		
		Map<String, Object> jmxMap = new HashMap<String, Object>();
		
		//Java运行时信息
		jmxMap.putAll(JVMInfo.info());

		long estimatedTime = System.currentTimeMillis() - Long.parseLong(String.valueOf(jmxMap.get(JVMProperty.JAVA_RUNTIME_STARTTIME.getKey())));

		jmxMap.put(JVMProperty.JAVA_RUNTIME_STARTTIME.getKey(), estimatedTime / 1000 / 60);
		
		model.addAttribute("jvm", jmxMap);
		//Java回收器信息
		model.addAttribute("gcs", JVMInfo.gc());
		//Java内存信息
		model.addAttribute("memory", JVMInfo.memory(Unit.MB));
		//Java内存池信息
		model.addAttribute("memoryPool", JVMInfo.memoryPool(Unit.MB));
		
        return "/html/jvm/index";
    }
	
	@ResponseBody
	@RequiresRoles({"admin"})
	@RequiresPermissions("monitor-watch:cx")
	@RequestMapping(value = "info", method = RequestMethod.POST )
	public Map<String, Object> info(HttpServletRequest request) throws Exception {
		
		String provider = propsProvider.props().getProperty(MemoryInfoProvider.SMS_PROVIDER);
		
		MemoryInfoProvider  memoryInfoProvider  = providerManager.getMemoryInfoProvider(provider);
		
		Map<String, Object> historyMap = new HashMap<String, Object>();
		
		//使用率
		historyMap.put("usageHistory", memoryInfoProvider.getHistoryUsage(request));
		historyMap.put("usage", memoryInfoProvider.getUsage());
		
		//使用量
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		//迭代集合中的数据对象
		Iterator<Map<String, Object>> ite = memoryInfoProvider.getHistoryMemory(request).iterator();
		
		Map<String, Object> tmpMap = null;
		while (ite.hasNext()) {
			Map<String, Object> item = ite.next();
			//创建临时Map对象
			tmpMap = new HashMap<String, Object>();
			//迭代原始值
			for (String key : item.keySet()) {
				//使用率和时间戳不处理
				if( !(key.endsWith(".usage") || key.endsWith(".timestamp")) ){
					//JVM记录换算成MB
					if(key.startsWith("jvm.memory")){
						tmpMap.put(key, CapacityUtils.getCapacity( Long.valueOf(String.valueOf(item.get(key))), Unit.MB));
					}
					//内存换算成GB
					else {
						tmpMap.put(key, CapacityUtils.getCapacity( Long.valueOf(String.valueOf(item.get(key))), Unit.GB));
					}
					
				}else {
					tmpMap.put(key, item.get(key));
				}
			}
			mapList.add(tmpMap);
		}
		
		historyMap.put("memoryHistory", mapList);
		
		return historyMap;
	}
	
	@ResponseBody
	@RequiresRoles({"admin"})
	@RequiresPermissions("monitor-watch:cx")
	@RequestMapping(value = "status", method = RequestMethod.POST )
	public Map<String, Object> status(Model model) throws Exception {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		String provider = propsProvider.props().getProperty(MemoryInfoProvider.SMS_PROVIDER);
		
		MemoryInfoProvider  memoryInfoProvider  = providerManager.getMemoryInfoProvider(provider);
		
		//使用率
		dataMap.putAll(memoryInfoProvider.getUsage());
		//使用量
		Map<String, Object> memory = memoryInfoProvider.getMemory(Unit.NONE);
		//迭代原始值
		for (String key : memory.keySet()) {
			//使用率和时间戳不处理
			if( !(key.endsWith(".usage") || key.endsWith(".timestamp")) ){
				//JVM记录换算成MB
				if(key.startsWith("jvm.memory")){
					dataMap.put(key, CapacityUtils.getCapacity( Long.valueOf(String.valueOf(memory.get(key))), Unit.MB));
				}
				//内存换算成GB
				else {
					dataMap.put(key, CapacityUtils.getCapacity( Long.valueOf(String.valueOf(memory.get(key))), Unit.GB));
				}
				
			}else {
				dataMap.put(key, memory.get(key));
			}
		}
		
		return dataMap;
	}
	
	@ResponseBody
	@RequiresRoles({"admin"})
	@RequiresPermissions("monitor-watch:xg")
	@RequestMapping(value = "threshold", method = RequestMethod.POST )
	public Object threshold(@RequestParam String key,@RequestParam String value) throws Exception {
		try {
			propsProvider.set(key, value);
			return successStatus("I99003");
		} catch (Exception e) {
			logException(this, e);
			return failStatus("I99004");
		}
	}
	
	@ResponseBody
	@RequiresRoles({"admin"})
	@RequiresPermissions("monitor-watch:xg")
	@RequestMapping(value = "notice", method = RequestMethod.POST )
	public Object notice(@RequestParam Map<String, String> params) throws Exception {
		try {
			//删除临时值
			params.remove("noticeType");
			for (String key : params.keySet()) {
				propsProvider.set(key, params.get(key));
			}
			return successStatus("I99003");
		} catch (Exception e) {
			logException(this, e);
			return failStatus("I99004");
		}
	}
	
}