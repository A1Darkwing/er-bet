package com.csc.betapp.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public abstract class BaseDao extends JdbcDaoSupport {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@PostConstruct
	public void init() {
		setDataSource(dataSource);
	}
}
