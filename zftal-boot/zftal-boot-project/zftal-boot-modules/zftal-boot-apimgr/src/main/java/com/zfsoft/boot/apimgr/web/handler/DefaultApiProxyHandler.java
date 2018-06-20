/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.handler;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.zfsoft.api.service.BaseAwareService;
import com.zfsoft.api.utils.WebUtils;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.boot.apimgr.setup.event.ApiInvokedEvent;
import com.zfsoft.boot.apimgr.util.JwtToken;
import com.zfsoft.web.utils.RemoteAddrUtils;
import com.zfsoft.boot.webv5.setup.constant.RequestAttributes;

@Component("defaultApiProxyHandler")
public class DefaultApiProxyHandler extends BaseAwareService implements ApiProxyHandler{
	
	@Value("${api.ip.blacklist}")
	private String blacklist;
	@Autowired
	private ApiHandlerExec handler;
	
	@Override
	public boolean before(String deployId, Map<String, Object> map) throws Exception{
		
		/**黑名单(内部用)*/
		String[] blacklists = blacklist.split(",");
		for (String regex : blacklists) {
			if(Pattern.matches(regex, RemoteAddrUtils.getRemoteAddr(WebUtils.toHttp(WebContext.getRequest())))) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public Object exec(String deployId, Map<String, Object> map) throws Exception {
		
		return handler.exec(deployId, map);
	}

	@Override
	public void after(String deployId, Map<String, Object> map, Object res, Exception ex, long startTime) {
		
		/** 发送调用完成后的事件实现异步处理,提供接口访问速度 */
		Object token = WebContext.getRequest().getAttribute(RequestAttributes.ATTR_ACCESS_TOKEN);
		String appkey = "proxy";
		if(!StringUtils.isEmpty(token)) {
			try {
				appkey = JwtToken.getClientId(token.toString());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		getContext().publishEvent(new ApiInvokedEvent(appkey, WebContext.getRequest(), deployId, map, res, ex, startTime));
	}

}
