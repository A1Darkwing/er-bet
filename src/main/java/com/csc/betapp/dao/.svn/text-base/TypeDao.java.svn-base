package com.csc.betapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csc.betapp.dao.mapper.MatchTypeMapper;
import com.csc.betapp.dao.mapper.TypeMapper;
import com.csc.betapp.model.MatchType;
import com.csc.betapp.model.Type;

@Repository("typeDao")
public class TypeDao extends BaseDao {
	public List<MatchType> getMatchTypeById(int id) {
		String sql = "select m.Id, Stake from matchtypes m, types t where m.typeId = t.Id AND t.Id = ?";
		return getJdbcTemplate().query(sql, new MatchTypeMapper(), id);
	}

	public List<Type> getTypes() {
		String sql = "select Id, Name from types";
		return getJdbcTemplate().query(sql, new TypeMapper());
	}

	public void update(int customerId, int id, int stake) {
		String sql = "UPDATE matchtypes SET Stake = ? WHERE customerId = ? AND Id = ?";
		getJdbcTemplate().update(sql, stake, customerId, id);
	}
}
