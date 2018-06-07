package com.csc.betapp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.csc.betapp.dao.mapper.MatchMapper;
import com.csc.betapp.model.Match;

@Repository("matchDao")
public class MatchDao extends BaseDao {
	public List<Match> getAllMatches(int customerId) {
		String sql = "select m.Id, m.Team1Id, m.Team2Id, m.MatchRate, m.MatchTime, m.ScoreTeam1,"
				+ "t1.name as team1name, t2.name as team2name,"
				+ " m.ScoreTeam2, m.MatchTypeId, m.CustomerId, mt.Stake, mt.TypeId, t.Name as typeName from matchs m "
				+ " inner join matchtypes mt on m.MatchTypeId = mt.Id inner join types t on mt.TypeId = t.Id "
				+ " inner join teams t1 on m.team1id = t1.id "
				+ " inner join teams t2 on m.team2id = t2.id "
				+ "WHERE m.customerId = ? order by m.MatchTime";
		return getJdbcTemplate().query(sql, new Object[] { customerId },
				new MatchMapper());
	}

	public Match getById(int customerId, int id) {
		String sql = "select m.Id, m.Team1Id, m.Team2Id, m.MatchRate, m.MatchTime, m.ScoreTeam1,"
				+ "t1.name as team1name, t2.name as team2name,"
				+ " m.ScoreTeam2, m.MatchTypeId, m.CustomerId, mt.Stake, mt.TypeId, t.Name as typeName from matchs m "
				+ " inner join matchtypes mt on m.MatchTypeId = mt.Id inner join types t on mt.TypeId = t.Id "
				+ " inner join teams t1 on m.team1id = t1.id "
				+ " inner join teams t2 on m.team2id = t2.id "
				+ "WHERE m.customerId = ? AND m.Id = ?";
		return getJdbcTemplate().query(sql, new Object[] { customerId, id },
				new MatchMapper()).get(0);
	}

	public void update(int customerId, int id, double rate, Integer scoreTeam1,
			Integer scoreTeam2) {
		String sql = "UPDATE matchs SET MatchRate = ?, ScoreTeam1 = ?, ScoreTeam2 = ? WHERE customerId = ? AND Id = ?";
		getJdbcTemplate().update(sql, rate, scoreTeam1, scoreTeam2, customerId,
				id);
	}

	public void insert(int team1, int team2, int type, int customerId,
			Date matchDate) {
		String sql = "INSERT INTO matchs (Team1Id, Team2Id, CustomerId, MatchTypeId, MatchTime) VALUES (?,?,?,?,?)";
		getJdbcTemplate()
				.update(sql, team1, team2, customerId, type, matchDate);
	}
}
