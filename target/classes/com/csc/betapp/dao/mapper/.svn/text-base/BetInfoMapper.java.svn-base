package com.csc.betapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.betapp.model.BetInfo;

public class BetInfoMapper implements RowMapper<BetInfo>{

	public BetInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		BetInfo betInfo = new BetInfo();
		betInfo.setId(rs.getInt("Id"));
		betInfo.setUserId(rs.getInt("UserId"));
		betInfo.setMatchId(rs.getInt("MatchId"));
		betInfo.setResult(rs.getString("Result"));
		betInfo.setBetTime(rs.getTimestamp("BetTime"));
		betInfo.setSelection(rs.getInt("Selection"));
		betInfo.setLoseMoney(rs.getInt("LoseMoney"));
		return betInfo;
	}

}
