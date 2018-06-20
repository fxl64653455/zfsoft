package com.zfsoft.boot.apimgr.setup.builder;

import java.util.Map;

import org.apache.commons.lang3.builder.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zfsoft.boot.apimgr.util.enums.RequestMethodEnum;
import com.zfsoft.boot.apimgr.web.handler.ApiProxyHandler;

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
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

public class ApiControllerCtClassBuilder implements Builder<CtClass> {
	
	/**请求方式(GET/POST)*/
	private String method;
	
	/**发布地址*/
	private String addr;
	
	/**响应类型及编码*/
	private String contentType;
	
	ClassPool pool = ClassPool.getDefault();
	// 构建动态类
	private CtClass ctclass  = null;
	
	public ApiControllerCtClassBuilder(final String classname) {
		this.ctclass = pool.getOrNull(classname);
		if( null == this.ctclass) {
			this.ctclass = pool.makeClass(classname);
		}
		ctclass.defrost();
	}
	
	@Override
	public CtClass build() {
        return ctclass;
	}
	
	public ApiControllerCtClassBuilder method(String method) {
		this.method = method;
		return this;
	}
	
	public ApiControllerCtClassBuilder addr(String addr) {
		this.addr = addr;
		return this;
	}
	
	public ApiControllerCtClassBuilder contentType(String contentType) {
		this.contentType = contentType;
		return this;
	}
	
	/**
	 * 生成一个类，内容如下：
	 * [#..] 为传入的参数
	 * @RestController
	 * class [#classname] {
	 * 	
	 * 	@Autowired
	 * 	private ApiHttpHandler httpHandler;
	 * 	
	 * 	@RequestMapping(value="[#addr]")
	 * 	public Object apiMethod_[#deployId](@RequestBody $1){
	 * 		if(!httpHandler.before(deployId,$1)){return;}
	 * 		Object res = null;
	 * 		try{
	 * 			res = httpHandler.exec(deployId,$1);
	 * 		}finally{
	 * 			httpHandler.after(deployId,$1,res);
	 * 		}
	 * 		return res;
	 * 	}
	 * }
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月19日 上午10:56:21
	 * @param deployId
	 * @param addr
	 * @return
	 * @throws CannotCompileException
	 * @throws NotFoundException
	 */
	public CtClass build(String deployId) throws CannotCompileException, NotFoundException {
		
		ClassFile ccFile = ctclass.getClassFile();
        ConstPool constPool = ccFile.getConstPool();
		
        /**添加类注解(RestController)*/
        AnnotationsAttribute classAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation restcontroller = new Annotation(RestController.class.getName(),constPool);
        classAttr.addAnnotation(restcontroller);
        ccFile.addAttribute(classAttr);
        
		/**添加属性字段 private ApiHttpHandler httpHandler;*/
        CtField field = new CtField(pool.get(ApiProxyHandler.class.getName()),"httpHandler",ctclass);
        field.setModifiers(Modifier.PRIVATE);

        /**在属性上添加注解(Autowired)*/
        FieldInfo fieldInfo = field.getFieldInfo();
        AnnotationsAttribute fieldAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation autowired = new Annotation(Autowired.class.getName(),constPool);
        fieldAttr.addAnnotation(autowired);
        Annotation qualifier = new Annotation(Qualifier.class.getName(),constPool);
        qualifier.addMemberValue("value", new StringMemberValue("defaultApiProxyHandler", constPool));
        fieldAttr.addAnnotation(qualifier);
        fieldInfo.addAttribute(fieldAttr);
        
        ctclass.addField(field);
        
        /**添加属性字段 private String deployId;*/
        field = new CtField(pool.get(String.class.getName()),"deployId",ctclass);
        field.setModifiers(Modifier.PRIVATE);
        
        ctclass.addField(field,"\""+deployId+"\"");
        
        /**方法主体内容*/
		CtMethod m = new CtMethod(pool.get(Object.class.getName()), "apiMethod_" + deployId, new CtClass[] {pool.get(Map.class.getName())}, ctclass);
		m.setModifiers(Modifier.PUBLIC); 
        StringBuffer body=new StringBuffer(); 
        body.append("{");
        body.append("Object res = null;");
        body.append("Exception ex = null;");
        body.append("long startTime = System.currentTimeMillis();");
        body.append("try{");
        body.append("if(!httpHandler.before(deployId,$1)){return null;}");
        body.append("res = httpHandler.exec(deployId,$1);");
        body.append("}catch(Exception e){");
        body.append("ex = e;");
        body.append("throw e;");
        body.append("}finally{");
        body.append("httpHandler.after(deployId,$1,res,ex,startTime);");
        body.append("}");
        body.append("return res;");
        body.append("}");
        m.setBody(body.toString());
        
        /**方法注解--requestMapping(value=addr)*/
        AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation mapping = null;
        if(method.equalsIgnoreCase(RequestMethodEnum.POST.getKey())) {
        	mapping = new Annotation(PostMapping.class.getName(),constPool);
        }else{
        	mapping = new Annotation(GetMapping.class.getName(),constPool);
        }
        ArrayMemberValue arr = new ArrayMemberValue(constPool);
        arr.setValue(new StringMemberValue[] {new StringMemberValue(addr,constPool)});
        mapping.addMemberValue("value", arr);
        arr = new ArrayMemberValue(constPool);
        arr.setValue(new StringMemberValue[] {new StringMemberValue(contentType,constPool)});
        mapping.addMemberValue("produces", arr);
        methodAttr.addAnnotation(mapping);
        m.getMethodInfo().addAttribute(methodAttr);
        
        /**方法参数注解*/
        ParameterAnnotationsAttribute parameterAtrribute = new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
        Annotation paramAnnot = null;
//        if(method.equalsIgnoreCase(RequestMethodEnum.POST.getKey())) {
//        	paramAnnot = new Annotation(RequestBody.class.getName(), constPool);
//        }else {
        	paramAnnot = new Annotation(RequestParam.class.getName(), constPool);
//        }
        Annotation[][] paramArrays = new Annotation[1][1];
        paramArrays[0][0] = paramAnnot;
        parameterAtrribute.setAnnotations(paramArrays);
        m.getMethodInfo().addAttribute(parameterAtrribute);
        
        ctclass.addMethod(m);
        
        return ctclass;
	}
	
}
