package com.csc.betapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csc.betapp.dao.mapper.TeamMapper;
import com.csc.betapp.model.Team;

@Repository("teamDao")
public class TeamDao extends BaseDao{
	public List<Team> getTeamByTeamId(int teamId){
		String sql = "Select Id, Name, Flag, GroupId from teams where Id = ?";
		return getJdbcTemplate().query(sql, new TeamMapper(), teamId);
	}
	public List<Team> getTeamByName(String name){
		String sql = "Select Id, Name, Flag, GroupId from teams where Name = ?";
		return getJdbcTemplate().query(sql, new TeamMapper(), name);
	}
	public List<Team> getAllTeam(){
		String sql = "Select Id, Name, Flag, GroupId from teams";
		return getJdbcTemplate().query(sql, new TeamMapper());
	}
}
