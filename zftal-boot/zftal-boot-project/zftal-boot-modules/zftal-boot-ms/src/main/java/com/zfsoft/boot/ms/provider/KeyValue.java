package com.zfsoft.boot.ms.provider;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.api.conf.KeyValueProvider;

public class KeyValue implements KeyValueProvider<Object>{

	private Map<String, Object> map = new HashMap<>();
	
	@Override
	public Object get(String key) {
		return map.get(key);
	}

	@Override
	public boolean set(String key, String value) {
		map.put(key, value);
		return true;
	}

}
