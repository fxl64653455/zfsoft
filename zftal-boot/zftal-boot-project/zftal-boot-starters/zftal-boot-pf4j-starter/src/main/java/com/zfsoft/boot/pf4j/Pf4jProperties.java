package com.zfsoft.boot.pf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.zfsoft.boot.pf4j.ext.Pf4jUpdateRepository;

import ro.fortsoft.pf4j.RuntimeMode;

/**
 * 
 * @className ： Pf4jProperties
 * @description ： TODO(描述这个类的作用)
 * @author ：万大龙（743）
 * @date ： 2017年11月1日 上午10:07:14
 * @version V1.0
 */
@ConfigurationProperties(prefix = Pf4jProperties.PREFIX)
public class Pf4jProperties {

	public static final String PREFIX = "pf4j";

	/** 是否启用 **/
	protected boolean enabled = false;
	/** 数据库列与表达式对应关系 **/
	protected List<String> classesDirectories = new ArrayList<String>();
	protected List<String> libDirectories = new ArrayList<String>();
	/** 运行模式：development、 deployment **/
	protected String mode = RuntimeMode.DEPLOYMENT.toString();
	/** 插件目录：默认 plugins;非jar模式的插件时，该值应该是绝对目录地址  **/
	protected String pluginsDir = "plugins";
	/** 是否注册插件到Spring上下文 **/
	protected boolean spring = false;
	/** 插件是否jar包 **/
	protected boolean jarPackages = true;
	
	/** 插件更新库JSON配置文件 **/
	protected String reposJsonPath = "repositories.json";
	/** 插件远程更新库配置列表 **/
	protected List<Pf4jUpdateRepository> repos = new ArrayList<Pf4jUpdateRepository>();
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getClassesDirectories() {
		return classesDirectories;
	}

	public void setClassesDirectories(List<String> classesDirectories) {
		this.classesDirectories = classesDirectories;
	}

	public List<String> getLibDirectories() {
		return libDirectories;
	}

	public void setLibDirectories(List<String> libDirectories) {
		this.libDirectories = libDirectories;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getPluginsDir() {
		return pluginsDir;
	}

	public void setPluginsDir(String pluginsDir) {
		this.pluginsDir = pluginsDir;
	}

	public boolean isSpring() {
		return spring;
	}

	public void setSpring(boolean spring) {
		this.spring = spring;
	}

	public boolean isJarPackages() {
		return jarPackages;
	}

	public void setJarPackages(boolean jarPackages) {
		this.jarPackages = jarPackages;
	}
	
	public String getReposJsonPath() {
		return reposJsonPath;
	}

	public void setReposJsonPath(String reposJsonPath) {
		this.reposJsonPath = reposJsonPath;
	}

	public List<Pf4jUpdateRepository> getRepos() {
		return repos;
	}

	public void setRepos(List<Pf4jUpdateRepository> repos) {
		this.repos = repos;
	}
	
}
