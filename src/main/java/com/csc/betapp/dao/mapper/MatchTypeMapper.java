package com.csc.betapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.betapp.model.MatchType;
import com.csc.betapp.model.Type;

public class MatchTypeMapper implements RowMapper<MatchType> {

	public MatchType mapRow(ResultSet rs, int rowNum) throws SQLException {
		MatchType matchType = new MatchType(rs.getInt("Id"),
				rs.getInt("Stake"), new Type());
		return matchType;
	}

}
