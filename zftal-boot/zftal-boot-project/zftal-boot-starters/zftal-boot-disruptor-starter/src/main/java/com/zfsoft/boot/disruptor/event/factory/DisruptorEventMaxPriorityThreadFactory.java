
package com.zfsoft.boot.disruptor.event.factory;

import java.util.concurrent.ThreadFactory;

public class DisruptorEventMaxPriorityThreadFactory implements ThreadFactory {
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setPriority(Thread.MAX_PRIORITY);
		return t;
	}
	
}
