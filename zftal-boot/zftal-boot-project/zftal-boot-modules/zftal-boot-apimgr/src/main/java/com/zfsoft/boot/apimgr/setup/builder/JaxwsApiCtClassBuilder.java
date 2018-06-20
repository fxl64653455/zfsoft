/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.builder;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebService;

import org.apache.commons.lang3.builder.Builder;
import org.springframework.beans.factory.annotation.Autowired;

import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.apimgr.web.handler.WsApiHandler;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.EnumMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

public class JaxwsApiCtClassBuilder implements Builder<CtClass> {
	
	private static final String JAXWS_API_PREFIX = "com.zfsoft.boot.api.ws.JaxwsApi";
	
	/**targetNamespace后缀*/
	private String suffix;
	
	/**参数键值*/
	private String[] pKeys;
	
	/**接口名称*/
	private String serviceName;
	
	/**操作名称*/
	private String operationName;
	
	ClassPool pool = ClassPool.getDefault();
	// 构建动态类
	private CtClass ctclass  = null;
	
	public JaxwsApiCtClassBuilder(final String classname) {
		String fullname = JAXWS_API_PREFIX + classname;
		this.ctclass = pool.getOrNull(fullname);
		if( null == this.ctclass) {
			this.ctclass = pool.makeClass(fullname);
		}
		serviceName = fullname.substring(fullname.lastIndexOf(".") + 1);
	}
	
	/**
	 * targetNamespace后缀
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月26日 下午3:13:34
	 * @param suffix
	 * @return
	 */
	public JaxwsApiCtClassBuilder suffix(String suffix) {
		this.suffix = suffix;
		return this;
	}
	
	/**
	 * 接口参数键值数组
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月26日 下午3:13:48
	 * @param pKeys
	 * @return
	 */
	public JaxwsApiCtClassBuilder pKeys(String[] pKeys) {
		this.pKeys = pKeys;
		return this;
	}
	
	/***
	 * WS接口名称
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月26日 下午3:13:10
	 * @param serviceName
	 * @return
	 */
	public JaxwsApiCtClassBuilder serviceName(String serviceName) {
		this.serviceName = serviceName;
		return this;
	}
	
	/**
	 * WS操作名称(一般为接口方法名称)
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月26日 下午3:23:35
	 * @param operationName
	 * @return
	 */
	public JaxwsApiCtClassBuilder operationName(String operationName) {
		this.operationName = operationName;
		return this;
	}
	
	@Override
	public CtClass build() {
        return ctclass;
	}

	public CtClass build(String deployId) throws CannotCompileException, NotFoundException {
		
		/**命名空间*/
		final String targetNamespace = "http://" + suffix;
		final String actionTargetNamespace = targetNamespace.endsWith("/") ? targetNamespace : targetNamespace + "/";
		
		ClassFile ccFile = ctclass.getClassFile();
        ConstPool constPool = ccFile.getConstPool();
        
        /**添加类注解(WebService)*/
        AnnotationsAttribute classAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation ws = new Annotation(WebService.class.getName(),constPool);
        ws.addMemberValue("name", new StringMemberValue(serviceName, constPool));
        ws.addMemberValue("targetNamespace", new StringMemberValue(targetNamespace, constPool));
        classAttr.addAnnotation(ws);
        ccFile.addAttribute(classAttr);
		
        /**添加属性字段 private WsApiHandler wsApiHandler;*/
        CtField field = new CtField(pool.get(WsApiHandler.class.getName()),"wsApiHandler",ctclass);
        field.setModifiers(Modifier.PRIVATE);

        /**在属性上添加注解(Autowired)*/
        FieldInfo fieldInfo = field.getFieldInfo();
        AnnotationsAttribute fieldAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation autowired = new Annotation(Autowired.class.getName(),constPool);
        fieldAttr.addAnnotation(autowired);
        fieldInfo.addAttribute(fieldAttr);
        
        ctclass.addField(field);
        
        /**添加属性字段 private String deployId;*/
        field = new CtField(pool.get(String.class.getName()),"deployId",ctclass);
        field.setModifiers(Modifier.PRIVATE);
        
        ctclass.addField(field,"\""+deployId+"\"");
        
        /**添加属性字段 private String paramNames;*/
        field = new CtField(pool.get(String.class.getName()),"paramNames",ctclass);
        field.setModifiers(Modifier.PRIVATE);

//        /**在属性上添加注解(Value)*/
//        fieldInfo = field.getFieldInfo();
//        fieldAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
//        value = new Annotation(Value.class.getName(),constPool);
//        value.addMemberValue("value", new StringMemberValue(StringUtils.join(pKeys,","), constPool));
//        fieldAttr.addAnnotation(value);
//        fieldInfo.addAttribute(fieldAttr);
        
        ctclass.addField(field,"\""+StringUtils.join(pKeys,",")+"\"");
        
        /**Axis方法*/
		CtClass[] paramTypes = new CtClass[pKeys.length + 1];
		for(int i = 0;i < paramTypes.length; i++) {paramTypes[i] = pool.get(String.class.getName());}
		CtMethod m = new CtMethod(pool.get(Object.class.getName()), operationName + "ByParamName", paramTypes, ctclass);
		m.setModifiers(Modifier.PUBLIC); 
        StringBuffer body=new StringBuffer(); 
        body.append("{");
        body.append("String[] arr = paramNames.split(\",\");");
        body.append("Object res = null;");
        body.append("Exception ex = null;");
        body.append("long startTime = System.currentTimeMillis();");
        body.append("try{");
        body.append("if(!wsApiHandler.before(deployId,\"Axis\",arr,$args)){return null;}");
        body.append("res = wsApiHandler.exec(deployId,\"Axis\",arr,$args);");
        body.append("}catch(Exception e){");
        body.append("ex = e;");
        body.append("throw e;");
        body.append("}finally{");
        body.append("wsApiHandler.after(deployId,\"Axis\",arr,$args,res,ex,startTime);");
        body.append("}");
        body.append("return res;");
        body.append("}");
        m.setBody(body.toString());
        
        /**方法注解*/
        AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation webMethod = new Annotation(WebMethod.class.getName(),constPool);
        webMethod.addMemberValue("action", new StringMemberValue(actionTargetNamespace + operationName + "ByParamName", constPool));
        webMethod.addMemberValue("operationName", new StringMemberValue(operationName + "ByParamName", constPool));
        methodAttr.addAnnotation(webMethod);
        m.getMethodInfo().addAttribute(methodAttr);
        
        /**Axis方法参数注解*/
        ParameterAnnotationsAttribute parameterAtrribute = new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
        Annotation[][] paramArrays = new Annotation[pKeys.length + 1][1];
        EnumMemberValue mode = new EnumMemberValue(constPool);
        mode.setType(Mode.class.getName());mode.setValue(Mode.IN.name());
        
        Annotation paramAnnot = new Annotation(WebParam.class.getName(), constPool);
        paramAnnot.addMemberValue("name", new StringMemberValue("accessToken", constPool));
        paramAnnot.addMemberValue("targetNamespace", new StringMemberValue(targetNamespace, constPool));
        paramAnnot.addMemberValue("mode", mode);
//        paramAnnot.addMemberValue("header", new BooleanMemberValue(true, constPool));
        paramArrays[pKeys.length][0] = paramAnnot;
        
        for(int i = 0;i < pKeys.length; i++) {
        	paramAnnot = new Annotation(WebParam.class.getName(), constPool);
            paramAnnot.addMemberValue("name", new StringMemberValue(pKeys[i], constPool));
            paramAnnot.addMemberValue("targetNamespace", new StringMemberValue(targetNamespace, constPool));
            paramAnnot.addMemberValue("mode", mode);
            paramArrays[i][0] = paramAnnot;
        }
        
        parameterAtrribute.setAnnotations(paramArrays);
        m.getMethodInfo().addAttribute(parameterAtrribute);
        
        ctclass.addMethod(m);
        
        /**没有参数名称的方法*/
        paramTypes = new CtClass[pKeys.length + 1];
		for(int i = 0;i < paramTypes.length; i++) {paramTypes[i] = pool.get(String.class.getName());}
		m = new CtMethod(pool.get(Object.class.getName()), operationName + "ByParamOrder", paramTypes, ctclass);
		m.setModifiers(Modifier.PUBLIC); 
        body=new StringBuffer(); 
        body.append("{");
        body.append("String[] arr = paramNames.split(\",\");");
        body.append("Object res = null;");
        body.append("Exception ex = null;");
        body.append("long startTime = System.currentTimeMillis();");
        body.append("try{");
        body.append("if(!wsApiHandler.before(deployId,\"Axis\",arr,$args)){return null;}");
        body.append("res = wsApiHandler.exec(deployId,\"Axis\",arr,$args);");
        body.append("}catch(Exception e){");
        body.append("ex = e;");
        body.append("throw e;");
        body.append("}finally{");
        body.append("wsApiHandler.after(deployId,\"Axis\",arr,$args,res,ex,startTime);");
        body.append("}");
        body.append("return res;");
        body.append("}");
        m.setBody(body.toString());
        
        /**方法注解*/
        methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        webMethod = new Annotation(WebMethod.class.getName(),constPool);
        webMethod.addMemberValue("action", new StringMemberValue(actionTargetNamespace + operationName + "ByParamOrder", constPool));
        webMethod.addMemberValue("operationName", new StringMemberValue(operationName + "ByParamOrder", constPool));
        methodAttr.addAnnotation(webMethod);
        m.getMethodInfo().addAttribute(methodAttr);
        
        ctclass.addMethod(m);
        
//        /**CXF方法*/
//        paramTypes = new CtClass[pKeys.length];
//		for(int i = 0;i < paramTypes.length; i++) {paramTypes[i] = pool.get(String.class.getName());}
//		m = new CtMethod(pool.get(Object.class.getName()), operationName, paramTypes, ctclass);
//		m.setModifiers(Modifier.PUBLIC); 
//        body=new StringBuffer(); 
//        body.append("{");
//        body.append("String[] arr = paramNames.split(\",\");");
//        body.append("Object res = null;");
//        body.append("try{");
//        body.append("if(!wsApiHandler.before(deployId,\"Cxf\",arr,$args)){return null;}");
//        body.append("res = wsApiHandler.exec(deployId,\"Cxf\",arr,$args);");
//        body.append("}finally{");
//        body.append("wsApiHandler.after(deployId,\"Cxf\",arr,$args,res);");
//        body.append("}");
//        body.append("return res;");
//        body.append("}");
//        m.setBody(body.toString());
//        
//        /**CXF方法参数注解*/
//        parameterAtrribute = new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
//        paramArrays = new Annotation[pKeys.length][1];
//        mode = new EnumMemberValue(constPool);
//        mode.setType(Mode.class.getName());mode.setValue(Mode.IN.name());
//        
//        for(int i = 0;i < pKeys.length; i++) {
//        	paramAnnot = new Annotation(WebParam.class.getName(), constPool);
//            paramAnnot.addMemberValue("name", new StringMemberValue(pKeys[i], constPool));
//            paramAnnot.addMemberValue("targetNamespace", new StringMemberValue(targetNamespace, constPool));
//            paramAnnot.addMemberValue("mode", mode);
//            paramArrays[i][0] = paramAnnot;
//        }
//        
//        parameterAtrribute.setAnnotations(paramArrays);
//        m.getMethodInfo().addAttribute(parameterAtrribute);
//        
//        ctclass.addMethod(m);
        
        return ctclass;
	}
	
}
