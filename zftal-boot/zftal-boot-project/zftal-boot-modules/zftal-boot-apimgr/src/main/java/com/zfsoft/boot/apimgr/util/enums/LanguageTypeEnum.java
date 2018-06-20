/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.util.enums;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public enum LanguageTypeEnum {
	
	//ABCDEFGHIJKLMNOPQRSTUVWXYZ
	
	CSHARP("C#", false, "C#是微软公司发布的一种面向对象的、运行于.NET Framework之上的高级程序设计语言。"), 
	GO("Go", false, "Go是一种新的语言，一种并发的、带垃圾回收的、快速编译的语言。"),
	GROOVY("Groovy", false, "Groovy是一种基于JVM（Java虚拟机）的敏捷开发语言，它结合了Python、Ruby和Smalltalk的许多强大的特性，Groovy 代码能够与 Java 代码很好地结合，也能用于扩展现有代码。"), 
	JAVA("Java", true, "Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。"), 
	JAVASCRIPT("JavaScript", true,"JavaScript一种直译式脚本语言，是一种动态类型、弱类型、基于原型的语言，内置支持类型。"),
	KOTLIN("Kotlin", false, "Kotlin 是一个基于 JVM 的新的编程语言，由 JetBrains 开发。Kotlin可以编译成Java字节码，也可以编译成JavaScript，方便在没有JVM的设备上运行。"), 
	PHP("PHP", true, "PHP（外文名:PHP: Hypertext Preprocessor，中文名：“超文本预处理器”）是一种通用开源脚本语言。语法吸收了C语言、Java和Perl的特点，利于学习，使用广泛，主要适用于Web开发领域。"), 
	PYTHON("Python",false, "Python是一种解释型、面向对象、动态数据类型的高级程序设计语言。"), 
	RUBY("Ruby", false, "Ruby，一种简单快捷的面向对象（面向对象程序设计）脚本语言。"), 
	SWIFT("Swift", false, "Swift 是一种支持多编程范式和编译式的开源编程语言,苹果于2014年WWDC（苹果开发者大会）发布，用于开发 iOS，OS X 和 watchOS 应用程序。");
	
	private String key;
	private boolean def;
	private String desc;

	private LanguageTypeEnum(String key, boolean def, String desc) {
		this.key = key;
		this.def = def;
		this.desc = desc;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isDef() {
		return def;
	}

	public void setDef(boolean def) {
		this.def = def;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static LanguageTypeEnum valueOfIgnoreCase(String key) {
		for (LanguageTypeEnum languageEnum : LanguageTypeEnum.values()) {
			if(languageEnum.getKey().equalsIgnoreCase(key)) {
				return languageEnum;
			}
		}
    	throw new NoSuchElementException("Cannot found Language Type with key '" + key + "'.");
    }
	
	public static List<Map<String, String>> languages() {
		List<Map<String, String>> driverList = new LinkedList<Map<String, String>>();
		for (LanguageTypeEnum typeEnum : LanguageTypeEnum.values()) {
			driverList.add(typeEnum.toMap());
		}
		return driverList;
	}

	public  Map<String, String> toMap() {
		Map<String, String> driverMap = new HashMap<String, String>();
		driverMap.put("key", this.getKey());
		driverMap.put("def", this.isDef() ? "1" : "0");
		driverMap.put("desc", this.getDesc());
		return driverMap;
	}
	
}
