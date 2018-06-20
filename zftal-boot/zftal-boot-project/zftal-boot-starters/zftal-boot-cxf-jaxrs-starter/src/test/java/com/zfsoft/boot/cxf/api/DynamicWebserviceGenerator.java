package com.zfsoft.boot.cxf.api;

import java.io.File;
import java.io.FileOutputStream;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
 
public class DynamicWebserviceGenerator
{
    public Class<?> createDynamicClazz() throws Exception
    {
        ClassPool pool = ClassPool.getDefault();
 
        // 创建类
        CtClass ctclass = pool.makeClass("com.coshaho.learn.DynamicHelloWorld");
 
        // 创建方法  
        CtClass ccStringType = pool.get("java.lang.String");
        // 参数：  1：返回类型  2：方法名称  3：传入参数类型  4：所属类CtClass 
        CtMethod ctMethod=new CtMethod(ccStringType,"sayHello",new CtClass[]{ccStringType},ctclass); 
        ctMethod.setModifiers(Modifier.PUBLIC); 
        StringBuffer body=new StringBuffer(); 
        body.append("{");
        body.append("\n    System.out.println($1);"); 
        body.append("\n    return \"Hello, \" + $1;"); 
        body.append("\n}"); 
        ctMethod.setBody(body.toString());
        ctclass.addMethod(ctMethod); 
         
        ClassFile ccFile = ctclass.getClassFile();
        ConstPool constPool = ccFile.getConstPool();
         
        // 添加类注解
        AnnotationsAttribute bodyAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation bodyAnnot = new Annotation("javax.jws.WebService", constPool);
        
        bodyAnnot.addMemberValue("name", new StringMemberValue("HelloWoldService", constPool));
       // 包名倒叙，并且和接口定义保持一致
        bodyAnnot.addMemberValue("targetNamespace", new StringMemberValue("http://service.jaxws.api.zfsoft.com", constPool));
        //bodyAnnot.addMemberValue("serviceName", new StringMemberValue("HelloWoldService", constPool));
        //bodyAnnot.addMemberValue("portName", new StringMemberValue("HelloWoldService", constPool));
        //bodyAnnot.addMemberValue("wsdlLocation", new StringMemberValue("HelloWoldService", constPool));
        //bodyAnnot.addMemberValue("endpointInterface", new StringMemberValue("HelloWoldService", constPool));
        
        bodyAttr.addAnnotation(bodyAnnot);
         
        ccFile.addAttribute(bodyAttr);
 
        // 添加方法注解
        AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation methodAnnot = new Annotation("javax.jws.WebMethod", constPool);
        
        //methodAnnot.addMemberValue("operationName", new StringMemberValue("sayHelloWorld", constPool));
        methodAnnot.addMemberValue("action", new StringMemberValue("sayHelloWorld", constPool));
        methodAnnot.addMemberValue("exclude", new BooleanMemberValue(false, constPool));
        
        methodAttr.addAnnotation(methodAnnot);
         
        Annotation resultAnnot = new Annotation("javax.jws.WebResult", constPool);
        resultAnnot.addMemberValue("name", new StringMemberValue("result", constPool));
        resultAnnot.addMemberValue("partName", new StringMemberValue("result", constPool));
        // 包名倒叙，并且和接口定义保持一致
        resultAnnot.addMemberValue("targetNamespace", new StringMemberValue("http://service.jaxws.api.zfsoft.com", constPool));
        resultAnnot.addMemberValue("header", new BooleanMemberValue(false, constPool));
       
        methodAttr.addAnnotation(resultAnnot);
         
        ctMethod.getMethodInfo().addAttribute(methodAttr);
         
        // 添加参数注解
        ParameterAnnotationsAttribute parameterAtrribute = new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
        Annotation paramAnnot = new Annotation("javax.jws.WebParam", constPool);
        paramAnnot.addMemberValue("name", new StringMemberValue("name",constPool));
        
        
        Annotation[][] paramArrays = new Annotation[1][1];
        paramArrays[0][0] = paramAnnot;
        parameterAtrribute.setAnnotations(paramArrays);
         
        ctMethod.getMethodInfo().addAttribute(parameterAtrribute);
         
        //把生成的class文件写入文件
        byte[] byteArr = ctclass.toBytecode();
        FileOutputStream fos = new FileOutputStream(new File("D://DynamicHelloWorld.class"));
        fos.write(byteArr);
        fos.close();
         
        return ctclass.toClass();
    }
}