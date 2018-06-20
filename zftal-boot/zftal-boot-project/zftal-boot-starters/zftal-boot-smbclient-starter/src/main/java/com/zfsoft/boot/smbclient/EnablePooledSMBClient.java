/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.smbclient;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * @className	： EnablePooledSMBClient
 * @description	：  基于 Apache Pool2的SMBClient资源服务客户端实现
 * @author 		：万大龙（743）
 * @date		： 2018年5月10日 上午11:05:21
 * @version 	V1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SMBClientPooledConfiguration.class })
public @interface EnablePooledSMBClient {
	
}