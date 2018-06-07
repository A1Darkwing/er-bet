package com.csc.betapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.betapp.model.Match;
import com.csc.betapp.model.MatchType;
import com.csc.betapp.model.Type;

public class MatchMapper implements RowMapper<Match> {

	public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
		Match match = new Match();
		match.setId(rs.getInt("Id"));
		match.setTeam1Id(rs.getInt("Team1Id"));
		match.setTeam2Id(rs.getInt("Team2Id"));
		match.setTeam1Name(rs.getString("team1name"));
		match.setTeam2Name(rs.getString("team2name"));
		match.setMatchRate(rs.getDouble("MatchRate"));
		match.setMatchTime(rs.getTimestamp("MatchTime"));
		match.setScoreTeam1((Integer) rs.getObject("scoreteam1"));
		match.setScoreTeam2((Integer) rs.getObject("scoreteam2"));
		Type type = new Type();
		type.setId(rs.getInt("TypeId"));
		type.setName(rs.getString("TypeName"));
		MatchType matchType = new MatchType(rs.getInt("MatchTypeId"),
				rs.getInt("Stake"), type);
		match.setMatchType(matchType);

		match.setTeamSelected((match.getMatchRate() > 0) ? 0 : 1);
		match.setMatchRate(Math.abs(match.getMatchRate()));
		return match;
	}

}
