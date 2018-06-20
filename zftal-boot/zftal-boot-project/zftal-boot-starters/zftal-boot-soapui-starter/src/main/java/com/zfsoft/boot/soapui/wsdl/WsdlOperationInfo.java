package com.zfsoft.boot.soapui.wsdl;

import java.util.List;

import javax.wsdl.OperationType;

import org.apache.commons.lang3.StringUtils;

import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.support.Constants;
import com.eviware.soapui.impl.wsdl.support.soap.SoapVersion;
import com.eviware.soapui.support.SoapUIException;
import com.google.common.collect.Lists;
import com.zfsoft.boot.soapui.utils.SoapuiRequestUtils;

/**
 * Wsdl Operation Info
 */
public class WsdlOperationInfo {

	private String endPoint;
	private String operationName;
	private OperationType operationType;
	private String operationDesc;
	private String requestXml;
	private String responseXml;
	private String soapAction;
	private SoapVersion soapVersion;
	private String targetNameSpace;
	private String targetXsd;
	private List<String> inputNames;
	private List<String> inputTypes;
	private List<String> inputDesc;
	private String sep = "#";

	public WsdlOperationInfo(WsdlOperation operation) {

		WsdlInterface wsdlInterface = operation.getInterface();

		this.operationName = operation.getName();
		this.operationDesc = operation.getDescription();
		this.operationType = operation.getOperationType();

		this.requestXml = operation.createRequest(true);
		this.responseXml = operation.createResponse(true);
		this.soapVersion = wsdlInterface.getSoapVersion();
		this.soapAction = this.soapVersion.getSoapActionHeader(operation.getAction());

		// 处理targetNameSpace
		this.targetNameSpace = requestXml.substring(requestXml.lastIndexOf("\"http://") + 1,
				requestXml.lastIndexOf("\">"));

		if (this.soapVersion.getEnvelopeNamespace().equalsIgnoreCase(Constants.SOAP11_ENVELOPE_NS)) {
			targetXsd = "11";
		} else if (this.soapVersion.getEnvelopeNamespace().equalsIgnoreCase(Constants.SOAP12_ENVELOPE_NS)) {
			targetXsd = "12";
		}

		try {

			this.inputNames = Lists.newArrayList();
			this.inputTypes = Lists.newArrayList();

			SoapuiRequestUtils.extractRequest(this.requestXml, this.soapVersion, this.inputNames, this.inputTypes);

		} catch (SoapUIException e) {
			e.printStackTrace();
		}

	}

	public String getEndPoint() {
		return endPoint;
	}

	public String getOperationName() {
		return operationName;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public String getOperationDesc() {
		return operationDesc;
	}

	public String getRequestXml() {
		return requestXml;
	}

	public String getResponseXml() {
		return responseXml;
	}

	public String getSoapAction() {
		return soapAction;
	}

	public SoapVersion getSoapVersion() {
		return soapVersion;
	}

	public String getTargetNameSpace() {
		return targetNameSpace;
	}

	public String getTargetXsd() {
		return targetXsd;
	}

	public List<String> getInputNames() {
		return inputNames;
	}

	public List<String> getInputTypes() {
		return inputTypes;
	}

	public List<String> getInputDesc() {
		return inputDesc;
	}

	@Override
	public String toString() {
		StringBuffer su = new StringBuffer();
		su.append(this.operationName);
		su.append(this.sep);
		su.append(this.inputTypes == null ? "" : StringUtils.join(this.inputTypes.toArray(), "@"));
		su.append(this.sep);
		su.append(this.inputNames == null ? "" : StringUtils.join(this.inputNames.toArray(), "@"));
		su.append(this.sep);
		su.append(this.operationDesc == null ? "" : this.operationDesc);
		su.append(this.sep);
		su.append(this.sep);
		su.append(this.soapAction == null ? "" : this.soapAction);
		su.append(this.sep);
		su.append(this.targetXsd == null ? "" : this.targetXsd);
		return su.toString();
	}

}