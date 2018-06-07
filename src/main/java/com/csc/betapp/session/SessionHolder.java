package com.csc.betapp.session;

import org.springframework.security.core.context.SecurityContextHolder;

import com.csc.betapp.model.AuthenticationUser;

public class SessionHolder {

	private Session session;

	private static SessionHolder instance;

	private SessionHolder(final Session session) {
		this.session = session;
	}

	public static SessionHolder createInstance(final Session session) {
		if (instance == null) {
			synchronized (SessionHolder.class) {
				if (instance == null) {
					instance = new SessionHolder(session);
				}
			}
		}
		return instance;
	}

	public static SessionHolder getInstance() {
		return instance;
	}

	public Integer getCustomerId() {
		try {
			AuthenticationUser user = (AuthenticationUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			if (user instanceof AuthenticationUser)
				return user.getUser().getCustomerId();
			else
				return null;
		} catch (Exception e) {
			return null;
		}
	}

	public Integer getUserId() {
		try {
			AuthenticationUser user = (AuthenticationUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			if (user instanceof AuthenticationUser)
				return user.getUser().getId();
			else
				return null;
		} catch (Exception e) {
			return null;
		}
	}

	public void clear() {
		session.clear();
	}
}
