/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLUtils {

	public static String escape(String name, String encode) {
		String ret = "";

		try {
			ret = URLEncoder.encode(name, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return ret;
	}

}
