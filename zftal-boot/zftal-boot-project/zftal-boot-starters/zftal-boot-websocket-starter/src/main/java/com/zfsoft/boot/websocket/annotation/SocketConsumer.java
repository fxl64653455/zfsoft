package com.zfsoft.boot.websocket.annotation;

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
public @interface SocketConsumer {
	
	/**
	 * Ant风格的事件分发规则表达式,格式为：/topic/tags/keys，如：/topic-a/tag-a/*
	 */
	String path();
	
}
