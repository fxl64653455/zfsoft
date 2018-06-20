/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.cxf.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


// http://blog.csdn.net/pmlpml/article/details/70185373

@Path("/customer")
public interface  JaxrsSample {

	@GET
    @Path("/id/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    String GetCustomerName(@PathParam("id") String id);
	
}
