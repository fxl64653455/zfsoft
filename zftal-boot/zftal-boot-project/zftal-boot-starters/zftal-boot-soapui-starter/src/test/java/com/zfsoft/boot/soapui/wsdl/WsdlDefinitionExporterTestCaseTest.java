/*
 * Copyright 2004-2014 SmartBear Software
 *
 * Licensed under the EUPL, Version 1.1 or - as soon as they will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the Licence is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the Licence for the specific language governing permissions and limitations
 * under the Licence.
*/

package com.zfsoft.boot.soapui.wsdl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.eviware.soapui.impl.support.definition.export.WsdlDefinitionExporter;
import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;
import com.eviware.soapui.settings.WsdlSettings;

/**
 * SoapUI 官方示例
 */
public class WsdlDefinitionExporterTestCaseTest {
	
    private static final String OUTPUT_FOLDER_BASE_PATH = WsdlDefinitionExporterTestCaseTest.class.getResource("/").getPath();

    @Test
    public void shouldSaveDefinition() throws Exception {

        testLoader("http://localhost:80/wsdls/test1/TestService.wsdl");
        testLoader("http://localhost:80/wsdls/test2/TestService.wsdl");
        testLoader("http://localhost:80/wsdls/test3/TestService.wsdl");
        testLoader("http://localhost:80/wsdls/test4/TestService.wsdl");
        testLoader("http://localhost:80/wsdls/test5/TestService.wsdl");
        testLoader("http://localhost:80/wsdls/test6/TestService.wsdl");
        testLoader("http://localhost:80/wsdls/test7/TestService.wsdl");
        testLoader("http://localhost:80/wsdls/test8/TestService.wsdl");
        testLoader("http://localhost:80/wsdls/testonewayop/TestService.wsdl");
    }

    private void testLoader(String wsdlUrl) throws Exception {
        WsdlProject project = new WsdlProject();
        project.getSettings().setBoolean(WsdlSettings.CACHE_WSDLS, true);
        WsdlInterface wsdlInterface = WsdlImporter.importWsdl(project, wsdlUrl)[0];

        assertTrue(wsdlInterface.isCached());

        WsdlDefinitionExporter exporter = new WsdlDefinitionExporter(wsdlInterface);

        String root = exporter.export(OUTPUT_FOLDER_BASE_PATH + "test" + File.separatorChar + "output");

        WsdlProject project2 = new WsdlProject();
        WsdlInterface wsdl2 = WsdlImporter.importWsdl(project2, new File(root).toURI().toURL().toString())[0];

        assertEquals(wsdlInterface.getBindingName(), wsdl2.getBindingName());
        assertEquals(wsdlInterface.getOperationCount(), wsdl2.getOperationCount());
        assertEquals(wsdlInterface.getWsdlContext().getInterfaceDefinition().getDefinedNamespaces(), wsdl2
                .getWsdlContext().getInterfaceDefinition().getDefinedNamespaces());
    }
}
