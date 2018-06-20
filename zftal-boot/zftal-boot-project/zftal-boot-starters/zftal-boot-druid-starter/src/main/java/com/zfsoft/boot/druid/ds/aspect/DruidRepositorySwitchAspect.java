/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.druid.ds.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zfsoft.boot.druid.ds.DataSourceContextHolder;
import com.zfsoft.boot.druid.ds.annotation.SwitchRepository;

@Aspect
@Component
public class DruidRepositorySwitchAspect {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
    //环绕通知   
	@Around("@annotation(com.zfsoft.boot.druid.ds.annotation.SwitchRepository) and @annotation(repository)")
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
