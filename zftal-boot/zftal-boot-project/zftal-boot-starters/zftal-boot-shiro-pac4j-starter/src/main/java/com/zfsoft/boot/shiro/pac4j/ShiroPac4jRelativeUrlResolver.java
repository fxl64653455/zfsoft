/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.pac4j;

import org.pac4j.core.context.ContextHelper;
import org.pac4j.core.context.HttpConstants;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.http.UrlResolver;
import org.springframework.util.StringUtils;

public class ShiroPac4jRelativeUrlResolver implements UrlResolver {

	private String contextPath;
	
	public ShiroPac4jRelativeUrlResolver(String contextPath) {
		this.contextPath = StringUtils.hasText(contextPath) ? contextPath : "/";
		if (this.contextPath.endsWith("/")) {
			this.contextPath = this.contextPath.substring(0, this.contextPath.length() - 1);
		}
	}
	
	@Override
    public String compute(final String url, WebContext context) {
        if (context != null && url != null && !url.startsWith("http://") && !url.startsWith("https://")) {
            final StringBuilder sb = new StringBuilder();

            sb.append(context.getScheme()).append("://").append(context.getServerName());

            final boolean notDefaultHttpPort = ContextHelper.isHttp(context) && context.getServerPort() != HttpConstants.DEFAULT_HTTP_PORT;
            final boolean notDefaultHttpsPort = ContextHelper.isHttps(context) && context.getServerPort() != HttpConstants.DEFAULT_HTTPS_PORT;
            if (notDefaultHttpPort || notDefaultHttpsPort) {
                sb.append(":").append(context.getServerPort());
            }

            sb.append(contextPath).append(url.startsWith("/") ? url : "/" + url);

            return sb.toString();
        }

        return url;
    }
}
