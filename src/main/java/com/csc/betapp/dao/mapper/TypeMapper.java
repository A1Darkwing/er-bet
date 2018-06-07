package com.csc.betapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.betapp.model.Type;

public class TypeMapper implements RowMapper<Type>{

	public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
		Type type = new Type();
		type.setId(rs.getInt("Id"));
		type.setName(rs.getString("Name"));
		return type;
	}

}
