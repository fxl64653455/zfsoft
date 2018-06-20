/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.pac4j.cas.credentials.authenticator;

import org.jasig.cas.client.validation.TicketValidationException;
import org.pac4j.cas.credentials.authenticator.CasAuthenticator;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.exception.HttpAction;

public class ShiroPac4jCasAuthenticator extends CasAuthenticator {

	@Override
	public void validate(TokenCredentials credentials, WebContext context) throws HttpAction {
		
		try {
			super.validate(credentials, context);
		} catch (Exception e) {
			if(e.getCause() instanceof TicketValidationException) {
			}
			throw e;
		}
	}

}
