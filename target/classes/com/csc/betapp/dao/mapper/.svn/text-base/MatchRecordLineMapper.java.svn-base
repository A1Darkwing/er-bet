package com.csc.betapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.betapp.model.MatchRecordLine;

public class MatchRecordLineMapper implements RowMapper<MatchRecordLine> {

	public MatchRecordLine mapRow(ResultSet rs, int rowNum) throws SQLException {
		String matchType = null;
		try {
			matchType = rs.getString("matchType");
		} catch (SQLException e) {
		}
		return new MatchRecordLine(rs.getInt("Id"), rs.getString("team1name"),
				rs.getString("team2name"), rs.getString("team1flag"),
				rs.getString("team2flag"), (Integer) rs.getObject("scoreteam1"),
				(Integer) rs.getObject("scoreteam2"), rs.getDouble("matchrate"), 0, 0,
				rs.getTimestamp("matchtime"), matchType);
	}
}
