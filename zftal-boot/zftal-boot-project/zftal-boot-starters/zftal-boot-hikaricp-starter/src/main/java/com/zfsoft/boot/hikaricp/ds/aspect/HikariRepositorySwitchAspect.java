package com.zfsoft.boot.hikaricp.ds.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zfsoft.boot.hikaricp.ds.DataSourceContextHolder;
import com.zfsoft.boot.hikaricp.ds.annotation.SwitchRepository;

/**
 * 
 * @className	： HikariRepositorySwitchAspect
 * @description	： 数据源自动切换切面
 * @author 		：万大龙（743）
 * @date		： 2017年11月28日 上午10:05:34
 * @version 	V1.0
 */
@Aspect
@Component
public class HikariRepositorySwitchAspect {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	//环绕通知   
	@Around("@annotation(com.zfsoft.boot.hikaricp.ds.annotation.SwitchRepository) and @annotation(repository)")
	public Object around(ProceedingJoinPoint joinPoint, SwitchRepository repository) throws Throwable {
		String oldRepository = DataSourceContextHolder.getDatabaseName();
    	try {
    		DataSourceContextHolder.setDatabaseName(repository.value());
    		return joinPoint.proceed();
        } finally {
        	if (logger.isDebugEnabled()) {
        		logger.debug("invoke(ProceedingJoinPoint) - end"); //$NON-NLS-1$
            }
    		DataSourceContextHolder.setDatabaseName(oldRepository);
        }
    }  
	
}
