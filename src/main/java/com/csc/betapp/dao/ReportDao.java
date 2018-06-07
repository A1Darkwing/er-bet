package com.csc.betapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.csc.betapp.dao.mapper.MatchRecordLineMapper;
import com.csc.betapp.dao.mapper.MatchRecordMapper;
import com.csc.betapp.dao.mapper.UserRecordDetailMapper;
import com.csc.betapp.model.MatchRecord;
import com.csc.betapp.model.MatchRecordLine;
import com.csc.betapp.model.UserRecordDetail;

@Repository("reportDao")
public class ReportDao extends BaseDao {
	public List<MatchRecordLine> getMatchRecordLines(int customerId,
			int matchTypeId) {
		String sql = "select m.id, t1.name as team1name, t2.name as team2name,"
				+ " t1.flag as team1flag, t2.flag as team2flag, scoreteam1, scoreteam2, "
				+ "matchrate, matchtime from matchs m inner join teams t1 on m.team1id = t1.id "
				+ "inner join teams t2 on m.team2id = t2.id where m.customerId = ? and m.matchtypeid = ? order by matchtime";
		return getJdbcTemplate().query(sql, new MatchRecordLineMapper(),
				customerId, matchTypeId);
	}

	public List<MatchRecord> getMatchTypes(int customerId) {
		String sql = "select m.Id, t.Name from matchtypes m inner join types t on m.typeId = t.id where m.customerId = ?";
		return getJdbcTemplate()
				.query(sql, new MatchRecordMapper(), customerId);
	}

	public Integer getCountBet(int customerId, int matchId, int selection) {
		String sql = "select count(Id) from bets where customerId = ? and matchId = ? and selection = ?";
		return getJdbcTemplate().queryForObject(sql, Integer.class, customerId,
				matchId, selection);
	}

	public List<String> getUserBet(int customerId, int matchId, int selection) {
		String sql = "select u.username from bets b inner join users u on b.UserId = u.id"
				+ " where u.customerId = ? and b.matchId = ? and b.selection = ?";
		return getJdbcTemplate().queryForList(sql, String.class, customerId,
				matchId, selection);
	}

	public Integer getCountUserResult(int customerId, int userId, String result) {
		String sql = "select count(Id) from bets where customerId = ? and userId = ? and result = ?";
		return getJdbcTemplate().queryForObject(sql, Integer.class, customerId,
				userId, result);
	}

	public Integer getCountUserLosingMoney(int customerId, int userId) {
		String sql = "select sum(LoseMoney) from bets where customerId = ? and userId = ? group by userId";
		List<Integer> result = getJdbcTemplate().queryForList(sql, Integer.class, customerId,
				userId);

		return CollectionUtils.isEmpty(result) ? 0 : result.get(0) == null ? 0 : result.get(0);
	}

	public List<UserRecordDetail> getUserRecordDetails(int customerId,
			int userId) {
		String sql = "select UserName, b.BetTime, b.LoseMoney, b.Result, m.matchtime, "
				+ "t1.name as team1name, t2.name as team2name, "
				+ "t1.flag as team1flag, t2.flag as team2flag, scoreteam1, scoreteam2, "
				+ "CASE b.Selection  WHEN 1 THEN t1.name WHEN 0 THEN '' ELSE t2.name end as teamName, "
				+ "CASE b.Selection  WHEN 1 THEN t1.flag WHEN 0 THEN '' ELSE t2.flag end as teamFlag "
				+ "from bets b " + "inner join users u on b.userId = u.id "
				+ "inner join matchs m on b.matchId = m.id "
				+ "inner join teams t1 on m.team1id = t1.id "
				+ "inner join teams t2 on m.team2id = t2.id "
				+ "where u.customerId = ? and b.userid = ? "
				+ " and m.scoreteam1 is not NULL  and m.scoreteam2 is not NULL order by m.matchtime";
		return getJdbcTemplate().query(sql, new UserRecordDetailMapper(),
				customerId, userId);
	}
}
