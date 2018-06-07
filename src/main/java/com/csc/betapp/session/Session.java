package com.csc.betapp.session;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Session implements Serializable {

	private static final long serialVersionUID = -6755553946543122324L;

	private Map<SessionType, Object> map = new ConcurrentHashMap<SessionType, Object>();

	public Session() {
	}

	public void add(SessionType type, Object value) {
		map.put(type, value);
	}

	public Object get(SessionType type) {
		return map.get(type);
	}

	public void clear() {
		map.clear();
	}

}
