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

import com.zfsoft.boot.apimgr.web.validator.impl.ApplyFileValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ApplyFileValidator.class)
public @interface ApplyFile {
	String message() default "applyfile error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
