package com.zfsoft.boot.cxf;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Before;
import org.junit.Test;

import com.zfsoft.boot.cxf.utils.JaxrsApiCtClassBuilder;
import com.zfsoft.io.utils.IOUtils;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.NotFoundException;

public class CtClassJaxwsApiBuilder_Test {

	CtClass ctClass = null;
	
	@Before
	public void setup(){
		try {
			ctClass = new JaxrsApiCtClassBuilder("com.zfsoft.jaxws.FirstCase").method("sayHello").build();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testAPIClass() throws Exception{
		
		
		/**
		 * 1、api名称
		 * 2、参数名称
		 * 
		 */
		byte[] byteArr = ctClass.toBytecode();
		FileOutputStream out = new FileOutputStream(new File("D://FirstCase.class"));
		IOUtils.copy(byteArr, out);
		IOUtils.closeOutput(out);
		/**
		 * 1、api名称
		 * 2、参数名称
		 * 
		Class clazz = ctClass.toClass();
		Method mainMethod = clazz.getMethod("sayHello", String.class);
		mainMethod.invoke(BeanUtils.instantiateClass(clazz),  " Hello " );
		 */
	}

}
