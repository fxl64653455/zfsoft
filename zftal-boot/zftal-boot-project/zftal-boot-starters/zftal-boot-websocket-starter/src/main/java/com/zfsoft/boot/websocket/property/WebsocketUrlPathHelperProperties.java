package com.zfsoft.boot.websocket.property;

import org.springframework.web.util.WebUtils;

public class WebsocketUrlPathHelperProperties {

	private boolean alwaysUseFullPath = false;

	private boolean urlDecode = true;

	private boolean removeSemicolonContent = true;

	private String defaultEncoding = WebUtils.DEFAULT_CHARACTER_ENCODING;

	public boolean isAlwaysUseFullPath() {
		return alwaysUseFullPath;
	}

	public void setAlwaysUseFullPath(boolean alwaysUseFullPath) {
		this.alwaysUseFullPath = alwaysUseFullPath;
	}

	public boolean isUrlDecode() {
		return urlDecode;
	}

	public void setUrlDecode(boolean urlDecode) {
		this.urlDecode = urlDecode;
	}

	public boolean isRemoveSemicolonContent() {
		return removeSemicolonContent;
	}

	public void setRemoveSemicolonContent(boolean removeSemicolonContent) {
		this.removeSemicolonContent = removeSemicolonContent;
	}

	public String getDefaultEncoding() {
		return defaultEncoding;
	}

	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}

}
