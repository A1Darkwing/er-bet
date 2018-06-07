package com.csc.betapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.betapp.model.UserRecordDetail;

public class UserRecordDetailMapper implements RowMapper<UserRecordDetail> {

	public UserRecordDetail mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		UserRecordDetail detail = new UserRecordDetail();
		detail.setUsername(rs.getString("UserName"));
		detail.setMatchDate(rs.getTimestamp("MatchTime"));
		detail.setBetTime(rs.getTimestamp("BetTime"));
		detail.setTeam1Name(rs.getString("team1name"));
		detail.setTeam2Name(rs.getString("team2name"));
		detail.setTeam1Flag(rs.getString("team1flag"));
		detail.setTeam2Flag(rs.getString("team2flag"));
		detail.setTeam1Score((Integer) rs.getObject("scoreteam1"));
		detail.setTeam2Score((Integer) rs.getObject("scoreteam2"));
		detail.setLosingMoney(rs.getInt("LoseMoney"));
		detail.setResultType(rs.getString("Result"));
		detail.setTeamName(rs.getString("TeamName"));
		detail.setTeamFlag(rs.getString("teamflag"));
		return detail;
	}

}
