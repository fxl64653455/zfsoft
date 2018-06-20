/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.pf4j.ext;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import ro.fortsoft.pf4j.DefaultPluginManager;

public class Pf4jPluginManager extends DefaultPluginManager {

	public Pf4jPluginManager(File path) {
		super(path.toPath());
	}

	public Pf4jPluginManager(String path) {
		super(Paths.get(path));
	}

	public Pf4jPluginManager(Path pluginsRoot) {
		super(pluginsRoot);
	}

}
