package com.zfsoft.boot.soapui;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlRequest;
import com.eviware.soapui.impl.wsdl.WsdlSubmit;
import com.eviware.soapui.impl.wsdl.WsdlSubmitContext;
import com.eviware.soapui.model.iface.Request.SubmitException;
import com.eviware.soapui.model.iface.Response;
import com.eviware.soapui.support.SoapUIException;
import com.zfsoft.boot.soapui.handler.SoapResponseHandler;
import com.zfsoft.boot.soapui.handler.def.SoapRequestArrayParamHandler;
import com.zfsoft.boot.soapui.handler.def.SoapRequestMapParamHandler;

/**
 * 
 */
public class SoapuiRequestTemplate {

	private SoapuiWsdlTemplate wsdlTemplate;
	private SoapRequestArrayParamHandler arrayParamHandler = new SoapRequestArrayParamHandler();
	private SoapRequestMapParamHandler mapParamHandler = new SoapRequestMapParamHandler();
	
	public SoapuiRequestTemplate(SoapuiWsdlTemplate wsdlTemplate) {
		this.wsdlTemplate = wsdlTemplate;
	}
	
	public Response invokeAt(String wsdlUrl, int index, Map<String, Object> params) throws SoapUIException, SubmitException {
		
		// get desired operation
		WsdlOperation operationInst = wsdlTemplate.getOperationAt(wsdlUrl ,index);
		
		// create a new empty request for that operation
		String requestName = "Request" + DigestUtils.md5Hex(wsdlUrl + "$operation-" + index);
		WsdlRequest request = operationInst.getRequestByName(requestName);
		if(null == request) {
			request = operationInst.addNewRequest(requestName);
		}
		
		//MessageExchange exchange = new WsdlResponseMessageExchange(request);
        //MessageExchange exchange = new RestResponseMessageExchange(request);
        //MessageExchange exchange = new HttpResponseMessageExchange(request);
        
		// generate the request content from the schema
		String requestXML = mapParamHandler.handleRequest(operationInst, request, params );
		request.setRequestContent( requestXML );
		
		WsdlSubmitContext context = new WsdlSubmitContext(request);
		
		// submit the request
		WsdlSubmit<WsdlRequest> submit = request.submit( context, false );

		// wait for the response
		return submit.getResponse();
		
	}
	
	public <T> T invokeAt(String wsdlUrl, int index, Map<String, Object> params, SoapResponseHandler<T> handler) throws SoapUIException, SubmitException {
		// get desired operation
		WsdlOperation operationInst = wsdlTemplate.getOperationAt(wsdlUrl ,index);
		// wait for the response
		Response response =  this.invokeAt(wsdlUrl, index, params);
		// handle the response
		return handler.handleResponse(operationInst, response);
	}
	
	public Response invokeAt(String wsdlUrl, int index, String... params) throws SoapUIException, SubmitException {
		
		// get desired operation
		WsdlOperation operationInst = wsdlTemplate.getOperationAt(wsdlUrl ,index);
		// create a new empty request for that operation
		String requestName = "Request" + DigestUtils.md5Hex(wsdlUrl + "$operation-" + index);
		WsdlRequest request = operationInst.getRequestByName(requestName);
		if(null == request) {
			request = operationInst.addNewRequest(requestName);
		}
		
		//MessageExchange exchange = new WsdlResponseMessageExchange(request);
        //MessageExchange exchange = new RestResponseMessageExchange(request);
        //MessageExchange exchange = new HttpResponseMessageExchange(request);
        
		// generate the request content from the schema
		String requestXML = arrayParamHandler.handleRequest(operationInst, request, params );
		request.setRequestContent( requestXML );
		
		WsdlSubmitContext context = new WsdlSubmitContext(request);
		
		// submit the request
		WsdlSubmit<WsdlRequest> submit = request.submit( context, false );

		// wait for the response
		return submit.getResponse();
		
	}
	
	public <T> T invokeAt(String wsdlUrl, int index, SoapResponseHandler<T> handler, String... params) throws SoapUIException, SubmitException {
		// get desired operation
		WsdlOperation operationInst = wsdlTemplate.getOperationAt(wsdlUrl ,index);
		// wait for the response
		Response response =  this.invokeAt(wsdlUrl, index, params);
		// handle the response
		return handler.handleResponse(operationInst, response);
	}
		
	public Response invokeByName(String wsdlUrl, String operationName, Map<String, Object> params) throws SoapUIException, SubmitException {
		
		// get desired operation
		WsdlOperation operationInst = wsdlTemplate.getOperationByName(wsdlUrl, operationName);
		
		// create a new empty request for that operation
		String requestName = "Request" + DigestUtils.md5Hex(wsdlUrl + "$" + operationName);
		WsdlRequest request = operationInst.getRequestByName(requestName);
		if(null == request) {
			request = operationInst.addNewRequest(requestName);
		}
		
		// generate the request content from the schema
		String requestXML = mapParamHandler.handleRequest(operationInst, request, params );
		request.setRequestContent( requestXML );
 		
		//WsdlRequestConfig config = new WsdlRequestConfigImpl(null);
		//request.setConfig(config);
		
		WsdlSubmitContext context = new WsdlSubmitContext(request);

		// submit the request
		WsdlSubmit<WsdlRequest> submit = request.submit( context, false );

		// wait for the response
		return submit.getResponse();
		
	}
	
	public <T> T invokeByName(String wsdlUrl, String operationName, Map<String, Object> params, SoapResponseHandler<T> handler) throws SoapUIException, SubmitException {
		// get desired operation
		WsdlOperation operationInst = wsdlTemplate.getOperationByName(wsdlUrl , operationName);
		// wait for the response
		Response response =  this.invokeByName(wsdlUrl, operationName, params);
		// handle the response
		return handler.handleResponse(operationInst, response);
	}
	
	public Response invokeByName(String wsdlUrl, String operationName, String... params) throws SoapUIException, SubmitException {
		
		// get desired operation
		WsdlOperation operationInst = wsdlTemplate.getOperationByName(wsdlUrl, operationName);
		
		// create a new empty request for that operation
		String requestName = "Request" + DigestUtils.md5Hex(wsdlUrl + "$" + operationName);
		WsdlRequest request = operationInst.getRequestByName(requestName);
		if(null == request) {
			request = operationInst.addNewRequest(requestName);
		}
		
		// generate the request content from the schema
		String requestXML = arrayParamHandler.handleRequest(operationInst, request, params );
		request.setRequestContent( requestXML );
 		
		//WsdlRequestConfig config = new WsdlRequestConfigImpl(null);
		//request.setConfig(config);
		
		WsdlSubmitContext context = new WsdlSubmitContext(request);

		// submit the request
		WsdlSubmit<WsdlRequest> submit = request.submit( context, false );

		// wait for the response
		return submit.getResponse();
		
	}
	
	public <T> T invokeByName(String wsdlUrl, String operationName, SoapResponseHandler<T> handler, String... params) throws SoapUIException, SubmitException {
		// get desired operation
		WsdlOperation operationInst = wsdlTemplate.getOperationByName(wsdlUrl , operationName);
		// wait for the response
		Response response =  this.invokeByName(wsdlUrl, operationName, params);
		// handle the response
		return handler.handleResponse(operationInst, response);
	}
	
}
