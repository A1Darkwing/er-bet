package com.csc.betapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.betapp.model.Team;


public class TeamMapper implements RowMapper<Team>{

	public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
		Team team = new Team();
		team.setId(rs.getInt("Id"));
		team.setName(rs.getString("Name"));
		team.setFlag(rs.getString("Flag"));
		team.setGroupId(rs.getInt("GroupId"));
		return team;
	}
	
}
