package com.zfsoft.boot.soapui.wsdl;

import java.util.ArrayList;
import java.util.List;

import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;
import com.eviware.soapui.support.SoapUIException;

/**
 */
public class WsdlInfo {

	private String wsdlUrl;

	private List<WsdlInterfaceInfo> interfaces;

	public WsdlInfo(String wsdlUrl) throws SoapUIException {
		try {
			this.wsdlUrl = wsdlUrl;
			// create new project
			WsdlProject project = new WsdlProject();
			WsdlInterface[] wsdlInterfaces = WsdlImporter.importWsdl(project, wsdlUrl);
			if (null != wsdlInterfaces) {
				List<WsdlInterfaceInfo> interfaces = new ArrayList<WsdlInterfaceInfo>();
				for (WsdlInterface wsdlInterface : wsdlInterfaces) {
					WsdlInterfaceInfo interfaceInfo = new WsdlInterfaceInfo(wsdlInterface);
					interfaces.add(interfaceInfo);
				}
				this.interfaces = interfaces;
			}
		} catch (Exception e) {
			throw new SoapUIException("Failed to import WSDL '" + wsdlUrl + "'.", e);
		}
	}

	public WsdlInfo(String wsdlUrl, WsdlProject project) throws SoapUIException {
		try {
			this.wsdlUrl = wsdlUrl;
			WsdlInterface[] wsdlInterfaces = WsdlImporter.importWsdl(project, wsdlUrl);
			if (null != wsdlInterfaces) {
				List<WsdlInterfaceInfo> interfaces = new ArrayList<WsdlInterfaceInfo>();
				for (WsdlInterface wsdlInterface : wsdlInterfaces) {
					WsdlInterfaceInfo interfaceInfo = new WsdlInterfaceInfo(wsdlInterface);
					interfaces.add(interfaceInfo);
				}
				this.interfaces = interfaces;
			}
		} catch (Exception e) {
			throw new SoapUIException("Failed to import WSDL '" + wsdlUrl + "'.", e);
		}
	}
	
	/**
	 * @param wsdlUrl wsdl地址
	 * @throws Exception
	 */
	public WsdlInfo(String wsdlUrl, WsdlInterface[] wsdlInterfaces) throws SoapUIException {
		this.wsdlUrl = wsdlUrl;
		if (null != wsdlInterfaces) {
			List<WsdlInterfaceInfo> interfaces = new ArrayList<WsdlInterfaceInfo>();
			for (WsdlInterface wsdlInterface : wsdlInterfaces) {
				WsdlInterfaceInfo interfaceInfo = new WsdlInterfaceInfo(wsdlInterface);
				interfaces.add(interfaceInfo);
			}
			this.interfaces = interfaces;
		}
	}

	public String getWsdlUrl() {
		return wsdlUrl;
	}

	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}

	public List<WsdlInterfaceInfo> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<WsdlInterfaceInfo> interfaces) {
		this.interfaces = interfaces;
	}
}