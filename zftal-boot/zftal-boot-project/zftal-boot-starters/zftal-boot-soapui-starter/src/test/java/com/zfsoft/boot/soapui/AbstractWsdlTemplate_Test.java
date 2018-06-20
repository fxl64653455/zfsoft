package com.zfsoft.boot.soapui;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.junit.Before;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.environment.EnvironmentListener;
import com.eviware.soapui.model.environment.Property;
import com.eviware.soapui.model.project.Project;
import com.eviware.soapui.model.support.ProjectListenerAdapter;
import com.eviware.soapui.support.SoapUIException;

public abstract class AbstractWsdlTemplate_Test {

	protected SoapuiWsdlTemplate wsdlTemplate;
	protected String wsdlUrl = "http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl";
	
	@Before
	public void setup() throws XmlException, IOException, SoapUIException {
		
		// create new project
		WsdlProject project = new WsdlProject();
		project.addEnvironmentListener(new EnvironmentListener() {
			
			@Override
			public void propertyValueChanged(Property property) {
				System.out.println(property.getName() + ":" + property.getValue());
			}
		});
		project.addProjectListener(new ProjectListenerAdapter() {
			
			@Override
			public void afterLoad(Project project) {
				super.afterLoad(project);
			}
			
		});
		
		wsdlTemplate = new SoapuiWsdlTemplate(project);
		
	}
	
	
}
