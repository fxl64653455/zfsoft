package com.zfsoft.boot.runenv.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

import com.zfsoft.cache.ehcache.EhcacheCacheStatistics;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.config.CacheConfiguration;

//@Component("ehcacheInfoTast")
public class EhcacheInfoWatchTask  {

	protected final Logger LOG = LoggerFactory.getLogger(EhcacheInfoWatchTask.class);
	// 声明一个容量为100的缓存队列
	public static final BlockingQueue<Map<String, Object>> EHCACHE_SNAPSHOT = new ArrayBlockingQueue<Map<String, Object>>(50); 

	@Autowired
	protected EhCacheCacheManager ehCacheManager;
	
	/**
	 * 
	 *@描述		：缓存平均命中率、丢失率,堆内、堆外、磁盘命中率
	 *@创建人		: wandalong
	 *@创建时间	: 2017年7月4日上午8:51:06
	 *@修改人	: 
	 *@修改时间	: 
	 *@修改描述	:
	 */
	public void percentage(){
		try {
			
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
			CacheManager cacheManager = getEhCacheManager().getCacheManager();
			String[] cacheNames = cacheManager.getCacheNames();
			double in_memory_hit_percentage_sum = 0, off_heap_hit_percentage_sum = 0, on_disk_hit_percentage_sum = 0,
					cache_hit_percentage_sum = 0, cache_misse_percentage_sum = 0;
			Integer maxBytesLocalDiskPercentage_sum = 0, maxBytesLocalHeapPercentage_sum = 0,
					maxBytesLocalOffHeapPercentage_sum = 0;
			
			for (String cacheName : cacheNames) {
				
				Map<String,Object> map = new HashMap<String, Object>();
				
				Ehcache cache = cacheManager.getEhcache(cacheName);

				//缓存配置信息
				CacheConfiguration cacheConfiguration = cache.getCacheConfiguration();
				
				map.put("name", cacheConfiguration.getName());
				
				//缓存统计信息
				EhcacheCacheStatistics statistics = new EhcacheCacheStatistics(cache);
				
				double in_memory_hit_percentage = statistics.getInMemoryHitPercentage();
				double off_heap_hit_percentage = statistics.getOffHeapHitPercentage();
				double on_disk_hit_percentage = statistics.getOnDiskHitPercentage();
				double cache_hit_percentage = statistics.getCacheHitPercentage();
				double cache_misse_percentage = statistics.getCacheMissPercentage();
				
				//堆内命中率 
				map.put("in-memory-hit-pct", in_memory_hit_percentage);
				//堆外命中率
				map.put("off-heap-hit-pct", off_heap_hit_percentage);
				//磁盘命中率
				map.put("on-disk-hit-pct", on_disk_hit_percentage);
				//总缓存命中率
				map.put("cache-hit-pct", cache_hit_percentage);
				//总缓存丢失率
				map.put("cache-misse-pct", cache_misse_percentage);
				
				in_memory_hit_percentage_sum += in_memory_hit_percentage;
				off_heap_hit_percentage_sum += off_heap_hit_percentage;
				on_disk_hit_percentage_sum += on_disk_hit_percentage;
				cache_hit_percentage_sum += cache_hit_percentage;
				cache_misse_percentage_sum += cache_misse_percentage;
				
				//已用磁盘空间大小（单位：字节）
				//map.put("maxBytesLocalDisk", cacheConfiguration.getMaxBytesLocalDiskAsString());
				//已用磁盘空间百分比
				Integer maxBytesLocalDiskPercentage = cacheConfiguration.getMaxBytesLocalDiskPercentage();
						maxBytesLocalDiskPercentage = maxBytesLocalDiskPercentage == null ? 0 : maxBytesLocalDiskPercentage ;
				map.put("disk-usage", maxBytesLocalDiskPercentage);
				
				//已用堆内内存大小（单位：字节）
				//map.put("maxBytesLocalHeap", cacheConfiguration.getMaxBytesLocalHeapAsString());
				//已用堆内内存百分比
				Integer maxBytesLocalHeapPercentage = cacheConfiguration.getMaxBytesLocalHeapPercentage();
						maxBytesLocalHeapPercentage = maxBytesLocalHeapPercentage == null ? 0 : maxBytesLocalHeapPercentage ;
				map.put("heap-usage", maxBytesLocalHeapPercentage);
				
				//已用堆外内存大小（单位：字节）
				//map.put("maxBytesLocalOffHeap", cacheConfiguration.getMaxBytesLocalOffHeapAsString());
				//已用堆外内存百分比
				Integer maxBytesLocalOffHeapPercentage = cacheConfiguration.getMaxBytesLocalOffHeapPercentage();
						maxBytesLocalOffHeapPercentage = maxBytesLocalOffHeapPercentage == null ? 0 : maxBytesLocalOffHeapPercentage ;
				map.put("off-heap-usage", maxBytesLocalOffHeapPercentage);
				
				maxBytesLocalDiskPercentage_sum += maxBytesLocalDiskPercentage;
				maxBytesLocalHeapPercentage_sum += maxBytesLocalHeapPercentage;
				maxBytesLocalOffHeapPercentage_sum += maxBytesLocalOffHeapPercentage;
				
				dataList.add(map);
			}
			
			dataMap.put("caches", dataList);
			int divisor = dataList.isEmpty() ? 1 : dataList.size();
			
			//计算平均命中率（百分比）
			dataMap.put("mean-in-memory-hit-pct", in_memory_hit_percentage_sum / divisor);
			dataMap.put("mean-off-heap-hit-pct",  off_heap_hit_percentage_sum / divisor);
			dataMap.put("mean-on-disk-hit-pct",  on_disk_hit_percentage_sum / divisor);
			dataMap.put("mean-cache-hit-pct",  cache_hit_percentage_sum / divisor);
			dataMap.put("mean-cache-misse-pct",  cache_misse_percentage_sum / divisor);
			//计算平均使用率（百分比）
			dataMap.put("mean-disk-usage",  maxBytesLocalDiskPercentage_sum / divisor);
			dataMap.put("mean-heap-usage",  maxBytesLocalHeapPercentage_sum / divisor);
			dataMap.put("mean-off-heap-usage",  maxBytesLocalOffHeapPercentage_sum / divisor);
			
			EHCACHE_SNAPSHOT.offer(dataMap);
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	public EhCacheCacheManager getEhCacheManager() {
		return ehCacheManager;
	}

	public void setEhCacheManager(EhCacheCacheManager ehCacheManager) {
		this.ehCacheManager = ehCacheManager;
	}
}
