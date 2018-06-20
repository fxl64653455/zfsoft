/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.cxf.utils;

@SuppressWarnings("serial")
public class APIBuildException extends RuntimeException {

    public APIBuildException() {
        super();
    }

    public APIBuildException(String message) {
        super(message);
    }

    public APIBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIBuildException(Throwable cause) {
        super(cause);
    }

    protected APIBuildException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
}
