/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.disruptor.event;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class DisruptorApplicationEvent extends ApplicationEvent {

	/**
	 * 当前事件绑定的数据对象
	 */
	protected Object bind;

	public DisruptorApplicationEvent(Object source, Object bind) {
		super(source);
		this.bind = bind;
	}
	
	public DisruptorApplicationEvent(Object source) {
		super(source);
	}

	public Object getBind() {
		return bind;
	}
	
	public void bind(Object bind) {
		this.bind = bind;
	}
	
	
}
