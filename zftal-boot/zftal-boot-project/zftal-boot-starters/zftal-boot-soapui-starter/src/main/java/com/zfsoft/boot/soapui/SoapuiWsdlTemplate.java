package com.zfsoft.boot.soapui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.XmlException;
import org.assertj.core.util.Lists;

import com.eviware.soapui.impl.WsdlInterfaceFactory;
import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.wsdl.UrlWsdlLoader;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;
import com.eviware.soapui.model.iface.Operation;
import com.eviware.soapui.support.SoapUIException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.zfsoft.boot.soapui.wsdl.WsdlInfo;

/**
 * https://www.soapui.org/developers-corner/integrating-with-soapui.html
 */
public class SoapuiWsdlTemplate {

	private WsdlProject project;
	private boolean createRequests = true;
	private LoadingCache<String, WsdlInterface[]> caches;
	
	public SoapuiWsdlTemplate() throws SoapUIException, XmlException, IOException {
		this(new WsdlProject(), new SoapuiProperties());
	}
	
	public SoapuiWsdlTemplate(WsdlProject project) {
		this(project, new SoapuiProperties());
	}
	
	public SoapuiWsdlTemplate(WsdlProject project, SoapuiProperties properties) {
		this.project = project;
		this.createRequests = properties.isCreateRequests();
		this.caches = CacheBuilder.newBuilder()
				.maximumSize(properties.getMaximumCacheSize())
				.refreshAfterWrite(properties.getCacheDuration(), TimeUnit.MINUTES)
				.build(new CacheLoader<String, WsdlInterface[]>() {
					@Override
					public WsdlInterface[] load(String wsdlUrl) throws Exception {
						//WsdlInterface[] wsdlInterfaces = WsdlImporter.importWsdl(project, wsdlUrl);
						
						UrlWsdlLoader loader = new UrlWsdlLoader(wsdlUrl, project);
						return WsdlInterfaceFactory.importWsdl(project, wsdlUrl, createRequests, loader);
					}
				});
	}

	public WsdlOperation getOperationAt(String wsdlUrl, int index) throws SoapUIException {
		WsdlInterface[] wsdlInterfaces = getWsdlInterfaces(wsdlUrl);
		WsdlOperation operationInst;
		for (WsdlInterface wsdlInterface : wsdlInterfaces) {
			operationInst = wsdlInterface.getOperationAt(index);
			if (operationInst != null) {
				return operationInst;
			}
		}
		throw new UnsupportedOperationException("Operation not found by WSDL '" + wsdlUrl + "'.");
	}
	

	public WsdlOperation getOperationByName(String wsdlUrl, String operationName) throws SoapUIException {
		WsdlInterface[] wsdlInterfaces = getWsdlInterfaces(wsdlUrl);
		WsdlOperation  operationInst;
		for (WsdlInterface wsdlInterface : wsdlInterfaces) {
			operationInst = wsdlInterface.getOperationByName(operationName);
			if (operationInst != null) {
				return operationInst;
			}
		}
		throw new UnsupportedOperationException(
				"Operation '" + operationName + "' not supported by WSDL '" + wsdlUrl + "'.");
	}
	
	public List<Operation> getOperationList(String wsdlUrl) throws SoapUIException {
		List<Operation> operationList = Lists.newArrayList();
		WsdlInterface[] wsdlInterfaces = getWsdlInterfaces(wsdlUrl);
		for (WsdlInterface wsdlInterface : wsdlInterfaces) {
			operationList.addAll(wsdlInterface.getOperationList());
		}
		return operationList;
	}
	
	public Map<String, Operation> getOperations(String wsdlUrl) throws SoapUIException {
		Map<String, Operation> result = new HashMap<String, Operation>();
		WsdlInterface[] wsdlInterfaces = getWsdlInterfaces(wsdlUrl);
		for (WsdlInterface wsdlInterface : wsdlInterfaces) {
			result.putAll(wsdlInterface.getOperations());
		}
		return result;
	}
	
	public WsdlInfo getWsdlInfo(String wsdlUrl) throws SoapUIException {
		return new WsdlInfo(wsdlUrl, this.getWsdlInterfaces(wsdlUrl));
	}
	
	public WsdlInterface[] getWsdlInterfaces(String wsdlUrl) throws SoapUIException {
		try {
			return caches.get(wsdlUrl);
		} catch (Exception e) {
			try {
				// import wsdl
				return WsdlImporter.importWsdl(project, wsdlUrl);
			} catch (Exception cause) {
				throw new SoapUIException("Failed to import WSDL '" + wsdlUrl + "'.", cause);
			}
		}
	}
	
}
