/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import com.zfsoft.boot.apimgr.web.validator.ApplyFile;

public class ApplyFileValidator implements ConstraintValidator<ApplyFile, MultipartFile>{
	
	@Override
	public void initialize(ApplyFile constraintAnnotation) {
	}

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		
		return value.getContentType().equals("application/msword");
	}
	
}
