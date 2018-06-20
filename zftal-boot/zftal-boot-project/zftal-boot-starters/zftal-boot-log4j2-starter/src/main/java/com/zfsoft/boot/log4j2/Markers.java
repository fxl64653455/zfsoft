package com.zfsoft.boot.log4j2;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * 
 * @className	： Markers
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年10月16日 下午12:20:46
 * @version 	V1.0
 */
public class Markers {

	public static final Marker DB = MarkerFactory.getMarker("dblog"); // dblog就是上面MarkerFilter里的标记

}
