package com.zfsoft.boot.authz.shiro.filter;

import com.zfsoft.api.web.session.User;
import com.zfsoft.shiro.filter.session.SessionControlFilter;

/**
 * <p>
 *   <h3>zftal框架<h3>
 *   <br>说明：TODO
 *	 <br>class：com.zfsoft.globalweb.shiro.DefaultSessionControlFilter.java
 * <p>
 * @author <a href="#">Zhangxiaobin[1036]<a>
 * @version 2016年8月15日上午8:50:29
 */
public class DefaultSessionControlFilter extends SessionControlFilter {

	/* (non-Javadoc)
	 * @see com.zfsoft.shiro.filter.session.SessionControlFilter#getSessionControlCacheKey(java.lang.Object)
	 */
	@Override
	protected String getSessionControlCacheKey(Object principal) {
		return ((User)principal).getYhm();
	}

}
