package com.zfsoft.boot.rocketmq.handler;

import com.zfsoft.boot.rocketmq.event.RocketmqEvent;

public abstract class AbstractNameableMessageHandler<T extends RocketmqEvent> implements EventHandler<T>, Nameable {

	/**
	 * 过滤器名称
	 */
	protected String name;

	protected String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
