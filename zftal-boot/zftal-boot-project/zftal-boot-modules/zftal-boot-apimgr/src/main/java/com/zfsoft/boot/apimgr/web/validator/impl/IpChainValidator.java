/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.validator.impl;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zfsoft.boot.apimgr.web.validator.IpChain;

public class IpChainValidator implements ConstraintValidator<IpChain, String>{
	
	@Override
	public void initialize(IpChain constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		String[] ips = value.split(",");
		for (String ip : ips) {
			if(!Pattern.matches("((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))|\\*)", ip)) {
				return false;
			}
			if(Integer.parseInt(ip.split(".")[3]) > 255) {
				return false;
			}
		}
		
		return true;
	}
	
}
