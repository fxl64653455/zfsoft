[#if deployDto.type == 'Rest']
<pre class="api-code">
package com.zfsoft.api.client.http;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.zfsoft.api.client.http.oauth2.ApiClientConfig;
<br />
public class ApiHttpClientTest {
	<br />
	/**不带认证的调用方式*/
	@Test
	public void invokeNotToken() throws Exception {
		String baseURL = "${baseUrl}${deployDto.addr?substring(1)}";
		Map&lt;String, Object&gt; paramsMap = new HashMap&lt;String, Object&gt;();
		[#if deployDto.paramList?exists && deployDto.paramList?size > 0]
	    [#list deployDto.paramList as par]
	    	paramsMap.put("${par.key?default('')}", "${par.name?default('')}");
		[/#list]
		[/#if]
		[#if deployDto.method == 'GET']
		String res = ApiHttpClientUtils.get().invokeApiWithGetNotToken(baseURL, paramsMap);
		[/#if]
		[#if deployDto.method == 'POST']
		String res = ApiHttpClientUtils.get().invokeApiWithPostNotToken(baseURL, paramsMap);
		[/#if]
		System.out.println(res);
	}
	<br />
	/**带认证的调用方式*/
	@Test
	public void invoke() throws Exception {
		ApiClientConfig config = new ApiClientConfig();
		config.setAppKey("appKey");
		config.setAppSecret("appSecret");
		config.setAccessTokenUrl("${baseUrl}oauth2/accessToken");
		String baseURL = "${baseUrl}${deployDto.addr?substring(1)}";<br />
		Map&lt;String, Object&gt; paramsMap = new HashMap&lt;String, Object&gt;();
		[#if deployDto.paramList?exists && deployDto.paramList?size > 0]  
	    [#list deployDto.paramList as par]
	    	paramsMap.put("${par.key?default('')}", "${par.name?default('')}");
		[/#list]
		[/#if]
		[#if deployDto.method == 'GET']
		String res = ApiHttpClientUtils.get(config).invokeApiWithGet(baseURL, paramsMap);
		[/#if]
		[#if deployDto.method == 'POST']
		String res = ApiHttpClientUtils.get(config).invokeApiWithPost(baseURL, paramsMap);
		[/#if]
		System.out.println(res);
	}
}
</pre>
[#else]
<pre class="api-code" >
/**Axis客户端指定参数名称的调用方式*/
package com.zfsoft.api.client.axis;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import org.apache.axis.AxisFault;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.utils.XMLUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;
import org.w3c.dom.Element;
<br />
public class ApiAxisUtils {
	<br />
	/**获取token*/
	@Test
	public void getToken() throws MalformedURLException, RemoteException, ServiceException {
		String nameSpace="http://dsb.zfsoft.com";
		String endpoint = "${baseUrl}ws/services/oauth2/accessToken";
		String appKey = "appKey";
		String appSecret = "appSecret";
		Param[] par = new Param[] {
			new Param("appKey", XMLType.XSD_STRING, appKey),
			new Param("appSecret", XMLType.XSD_STRING, appSecret)
		};
		Object res = AxisClientUtils.invoke(endpoint, new QName(nameSpace, "accessToken"), true, XMLType.XSD_STRING, par);
		System.out.println(res);
	}
	<br />
	/**带认证调用方式*/
	@Test
	public void invokeWithToken() throws MalformedURLException, RemoteException, ServiceException {
		String token = "token";
		String nameSpace="http://${deployDto.namespace}";
		String endpoint = "${baseUrl}ws/${deployDto.addr?substring(1)}";
		[#if deployDto.paramList?exists && deployDto.paramList?size > 0]
		Param[] par = new Param[] {
		[#assign i=0 /]
    	[#list deployDto.paramList as par]
    		[#assign i=i+1 /]
			new Param("${par.key?default('')}", XMLType.XSD_STRING, "${par.name?default('')}")[#if i < deployDto.paramList?size],[/#if]
		[/#list]
		};
		Object res = AxisClientUtils.invokeWithToken(endpoint, "OperationByParamName", true, XMLType.XSD_STRING, nameSpace, token, par);
		[#else]
		Object res = AxisClientUtils.invokeWithToken(endpoint, "OperationByParamName", true, XMLType.XSD_STRING, nameSpace, token);
		[/#if]
		System.out.println(res);
	}
	<br />
	/**不带认证调用方式*/
	@Test
	public void invoke() throws MalformedURLException, RemoteException, ServiceException {
		String nameSpace="http://${deployDto.namespace}";
		String endpoint = "${baseUrl}ws/${deployDto.addr?substring(1)}";
		[#if deployDto.paramList?exists && deployDto.paramList?size > 0]
		Param[] par = new Param[] {
		[#assign i=0 /]
    	[#list deployDto.paramList as par]
    		[#assign i=i+1 /]
			new Param("${par.key?default('')}", XMLType.XSD_STRING, "${par.name?default('')}")[#if i < deployDto.paramList?size],[/#if]
		[/#list]
		};
		Object res = AxisClientUtils.invoke(endpoint, new QName(nameSpace, "OperationByParamName"), true, XMLType.XSD_STRING, par);
		[#else]
		Object res = AxisClientUtils.invoke(endpoint, new QName(nameSpace, "OperationByParamName"), true, XMLType.XSD_STRING);
		[/#if]
		System.out.println(res);
	}
}
</pre>
<br />
<pre class="api-code">
/**Axis2根据参数顺序调用方式*/
package com.zfsoft.api.client.axis2;
import org.junit.Test;
<br />
public class ApiAxis2ClientUtils {
	<br />
	/**获取Token*/
	@Test
	public void getToken() throws Exception {
		String nameSpace="http://dsb.zfsoft.com";
		String endpoint = "${baseUrl}ws/services/oauth2/accessToken";
		String appKey = "appKey";
		String appSecret = "appSecret";
		Object[] args = new Object[] {appKey, appSecret};
		Class&lt;?&gt;[] returnTypes = new Class&lt;?&gt;[] { String.class };
		Object[] res = Axis2ClientUtils.invoke(endpoint, nameSpace,"accessTokenByParamOrder", args, returnTypes);
		System.out.println(res[0]);
	}
	<br />
	/**带认证的调用方式*/
	@Test
	public void invokeWithToken() throws Exception {
		String nameSpace="http://${deployDto.namespace}";
		String endpoint = "${baseUrl}ws/${deployDto.addr?substring(1)}";
		Class&lt;?&gt;[] returnTypes = new Class&lt;?&gt;[] { String.class };
		[#if deployDto.paramList?exists && deployDto.paramList?size > 0]
		Object[] par = new Object[] {
		[#assign i=0 /]
    	[#list deployDto.paramList as par]
    		[#assign i=i+1 /]
			"${par.name?default('')}"[#if i < deployDto.paramList?size],[/#if]
		[/#list]
		,"token"};
		Object[] res = Axis2ClientUtils.invoke(endpoint, nameSpace,"OperationByParamOrder", args, returnTypes);
		[#else]
		Object[] res = Axis2ClientUtils.invoke(endpoint, nameSpace,"OperationByParamOrder", args, returnTypes);
		[/#if]
		System.out.println(res[0]);
	}
	<br />
	/**不带认证的调用方式*/
	@Test
	public void invoke() throws Exception {
		String nameSpace="http://${deployDto.namespace}";
		String endpoint = "${baseUrl}ws/${deployDto.addr?substring(1)}";
		[#if deployDto.paramList?exists && deployDto.paramList?size > 0]
		Object[] par = new Object[] {
		[#assign i=0 /]
    	[#list deployDto.paramList as par]
    		[#assign i=i+1 /]
			"${par.name?default('')}"[#if i < deployDto.paramList?size],[/#if]
		[/#list]
		,""};
		Object[] res = Axis2ClientUtils.invoke(endpoint, nameSpace,"OperationByParamOrder", args, returnTypes);
		[#else]
		Object[] res = Axis2ClientUtils.invoke(endpoint, nameSpace,"OperationByParamOrder", args, returnTypes);
		[/#if]
		System.out.println(res[0]);
	}
}
</pre>
<br />
<pre class="api-code">
/**CXF动态调用方式*/
package com.zfsoft.api.client.cxf;
import org.junit.Test;
public class ApiCxfClientUtils {
	<br />
	/**获取Token*/
	@Test
	public void getToken() throws Exception{
		String wsdlUrl = "${baseUrl}ws/services/oauth2/accessToken?wsdl";
		String appKey = "appKey";
		String appSecret = "appSecret";
		Object[] res = CxfClientUtils.invoke(wsdlUrl, "accessTokenByParamOrder",new Object[] {appKey,appSecret});
		System.out.println(res[0]);
	}
	<br />
	/**带认证的调用方式*/
	@Test
	public void invokeWithToken() throws Exception{
		String token = "token";<br />
		String wsdlUrl = "${baseUrl}ws/${deployDto.addr?substring(1)}?wsdl";
        [#if deployDto.paramList?exists && deployDto.paramList?size > 0]
		Object[] par = new Object[] {
		[#assign i=0 /]
    	[#list deployDto.paramList as par]
    		[#assign i=i+1 /]
			"${par.name?default('')}"[#if i < deployDto.paramList?size],[/#if]
		[/#list]
		};
		Object res = CxfClientUtils.invoke(wsdlUrl, "OperationByParamOrder", token, par);
		[#else]
		Object res = CxfClientUtils.invoke(wsdlUrl, "OperationByParamOrder", token);
		[/#if]
		System.out.println(res[0]);
	}
	<br />
	/**不带认证的调用方式*/
	@Test
	public void invoke() throws Exception {
		String wsdlUrl = "${baseUrl}ws/${deployDto.addr?substring(1)}?wsdl";
        [#if deployDto.paramList?exists && deployDto.paramList?size > 0]
		Object[] par = new Object[] {
		[#assign i=0 /]
    	[#list deployDto.paramList as par]
    		[#assign i=i+1 /]
			"${par.name?default('')}"[#if i < deployDto.paramList?size],[/#if]
		[/#list]
		};
		Object res = CxfClientUtils.invoke(wsdlUrl, "OperationByParamOrder", par);
		[#else]
		Object res = CxfClientUtils.invoke(wsdlUrl, "OperationByParamOrder");
		[/#if]
		System.out.println(res[0]);
	}
}
</pre>
[/#if]