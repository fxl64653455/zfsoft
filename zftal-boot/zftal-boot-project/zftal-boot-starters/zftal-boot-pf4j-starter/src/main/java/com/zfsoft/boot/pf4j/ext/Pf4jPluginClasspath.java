/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.pf4j.ext;

import java.util.List;

import ro.fortsoft.pf4j.DefaultPluginClasspath;

public class Pf4jPluginClasspath extends DefaultPluginClasspath {

	public Pf4jPluginClasspath(String... classesDirectories) {
		super();
		addClassesDirectories(classesDirectories);
	}

	public Pf4jPluginClasspath(List<String> classesDirectories, List<String> libDirectories) {
		this(classesDirectories.toArray(new String[classesDirectories.size()]), libDirectories.toArray(new String[libDirectories.size()]));
	}
	
	public Pf4jPluginClasspath(String[] libDirectories, String... classesDirectories) {
		super();
		addClassesDirectories(classesDirectories);
		addLibDirectories(libDirectories);
	}


}
