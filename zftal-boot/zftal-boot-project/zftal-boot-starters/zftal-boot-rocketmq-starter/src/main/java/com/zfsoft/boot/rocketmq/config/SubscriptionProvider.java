/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.rocketmq.config;

import java.util.Map;

public interface SubscriptionProvider {

	Map<String /* topic */, String /* selectorExpress */> subscription();
	
}
