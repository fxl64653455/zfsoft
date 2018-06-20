/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.pac4j;

import javax.servlet.Filter;

import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;

public class ShiroPac4jFilterFactoryBean extends ShiroBizFilterFactoryBean {

	@Override
	protected boolean supports(Filter filter) {
		return filter instanceof SecurityFilter || filter instanceof CallbackFilter || filter instanceof LogoutFilter
				|| super.supports(filter);
	}
	 
}

