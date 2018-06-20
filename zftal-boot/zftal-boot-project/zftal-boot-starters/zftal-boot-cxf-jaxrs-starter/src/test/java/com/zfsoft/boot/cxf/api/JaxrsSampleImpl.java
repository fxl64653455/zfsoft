/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.cxf.api;

//@Api("/customer")
public class JaxrsSampleImpl implements JaxrsSample {
    
	public String GetCustomerName(String id) {
        return "hello" + id;
    }
    
}