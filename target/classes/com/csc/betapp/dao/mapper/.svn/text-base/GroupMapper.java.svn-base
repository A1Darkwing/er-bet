package com.csc.betapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.betapp.model.Group;

public class GroupMapper implements RowMapper<Group>{

	public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
		Group group = new Group();
		group.setId(rs.getInt("Id"));
		group.setName(rs.getString("Name"));
		return group;
	}

}
