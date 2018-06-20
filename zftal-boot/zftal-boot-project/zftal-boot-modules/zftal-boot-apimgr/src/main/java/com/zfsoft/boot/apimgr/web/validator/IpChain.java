/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.zfsoft.boot.apimgr.web.validator.impl.IpChainValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IpChainValidator.class)
public @interface IpChain {
	String message() default "ip error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
