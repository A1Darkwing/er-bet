package com.csc.betapp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.csc.betapp.dao.mapper.BetInfoMapper;
import com.csc.betapp.model.BetInfo;
import com.csc.betapp.session.SessionHolder;

@Repository("betInfoDao")
public class BetInfoDao extends BaseDao {
	public List<BetInfo> getAllBetFromMatchId(int matchId, int customerId) {
		String sql = "select Id, UserId, MatchId, Result, BetTime, Selection,"
				+ "LoseMoney from bets where MatchId=? and CustomerId=?";
		return getJdbcTemplate().query(sql, new BetInfoMapper(), matchId,
				customerId);
	}

	public void setSelection(int betInfoId, int result, int customerId) {
		String sql = "update bets set selection = ? where Id = ? and CustomerId=?";
		getJdbcTemplate().update(sql, result, betInfoId, customerId);
	}

	public void setResult(int betInfoId, int selection, int customerId) {
		String sql = "update bets set Result = ? where Id = ? and CustomerId=?";
		getJdbcTemplate().update(sql, selection, betInfoId, customerId);
	}

	public void insertBetInfo(BetInfo betInfo, int customerId) {
		String sql = "insert into bets (UserId, MatchId, Result, BetTime, Selection, LoseMoney, CustomerId)"
				+ "values (?,?,?,?,?,?,?)";
		getJdbcTemplate().update(
				sql,
				new Object[] { betInfo.getUserId(), betInfo.getMatchId(),
						betInfo.getResult(), betInfo.getBetTime(),
						betInfo.getSelection(), betInfo.getLoseMoney(),
						SessionHolder.getInstance().getCustomerId() });
	}

	public void updateAfterMatch(String result, Integer money, int customerId,
			int id) {
		String sql = "update bets set Result = ?, LoseMoney = ? where Id = ? and CustomerId=?";
		getJdbcTemplate().update(sql, result, money, id, customerId);
	}
}
