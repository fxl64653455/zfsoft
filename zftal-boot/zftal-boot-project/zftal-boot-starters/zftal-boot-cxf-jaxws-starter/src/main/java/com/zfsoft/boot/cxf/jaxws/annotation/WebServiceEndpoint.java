/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.cxf.jaxws.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WebServiceEndpoint {
	
	String addr();
	
	String[] inInterceptors() default {""};

	String[] outInterceptors() default {""};
	
	String[] inFaultInterceptors() default {""};

	String[] outFaultInterceptors() default {""};
	
	String[] features() default {""};
	
}
