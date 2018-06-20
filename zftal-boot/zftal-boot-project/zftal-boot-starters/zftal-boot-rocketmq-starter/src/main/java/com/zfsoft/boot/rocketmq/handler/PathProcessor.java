package com.zfsoft.boot.rocketmq.handler;

import com.zfsoft.boot.rocketmq.event.RocketmqEvent;

/**
 * 给Handler设置路径
 */
public interface PathProcessor<T extends RocketmqEvent> {
	
	EventHandler<T> processPath(String path);

}
