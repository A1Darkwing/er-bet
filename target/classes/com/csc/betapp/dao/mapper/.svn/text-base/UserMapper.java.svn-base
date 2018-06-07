package com.csc.betapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.betapp.model.User;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("Id"));
		user.setUsername(rs.getString("Username"));
		user.setPassword(rs.getString("Password"));
		user.setRole(rs.getString("Role"));
		user.setCustomerId(rs.getInt("CustomerId"));
		return user;
	}
}
