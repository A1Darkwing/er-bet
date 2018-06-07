package com.csc.betapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.betapp.model.MatchRecord;

public class MatchRecordMapper implements RowMapper<MatchRecord> {

	public MatchRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
		MatchRecord record = new MatchRecord();
		record.setId(rs.getInt("Id"));
		record.setName(rs.getString("Name"));
		return record;
	}

}
