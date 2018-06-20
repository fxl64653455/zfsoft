/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.pf4j.ext;

import ro.fortsoft.pf4j.DefaultPluginClasspath;
import ro.fortsoft.pf4j.DevelopmentPluginClasspath;
import ro.fortsoft.pf4j.JarPluginManager;
import ro.fortsoft.pf4j.PluginClasspath;

public class Pf4jJarPluginManager extends JarPluginManager {

	public Pf4jJarPluginManager() {
	}
	
	public Pf4jJarPluginManager(PluginClasspath pluginClasspath) {
		this.pluginClasspath = pluginClasspath;
	}
	
	@Override
	protected PluginClasspath createPluginClasspath() {
		if(this.pluginClasspath != null) {
			return pluginClasspath;
		}
		return isDevelopment() ? new DevelopmentPluginClasspath() : new DefaultPluginClasspath();
    }
}
