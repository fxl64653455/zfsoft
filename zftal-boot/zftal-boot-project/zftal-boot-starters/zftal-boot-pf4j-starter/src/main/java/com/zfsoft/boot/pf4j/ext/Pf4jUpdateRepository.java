/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.pf4j.ext;

import java.net.URL;

public class Pf4jUpdateRepository {

	private String id;
	private URL url;
	private String pluginsJsonFileName = "plugins.json";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getPluginsJsonFileName() {
		return pluginsJsonFileName;
	}

	public void setPluginsJsonFileName(String pluginsJsonFileName) {
		this.pluginsJsonFileName = pluginsJsonFileName;
	}

}
