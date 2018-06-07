package com.csc.betapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csc.betapp.dao.mapper.GroupMapper;
import com.csc.betapp.model.Group;

@Repository("groupDao")
public class GroupDao extends BaseDao{
	public List<Group> getGroupById(int groupId){
		String sql = "select Id, Name from groups where Id = ?";
		return getJdbcTemplate().query(sql, new GroupMapper(), groupId);
	}
	
	public List<Group> getAllGroup() {
		String sql = "Select * from groups order by Id";
		return getJdbcTemplate().query(sql, new GroupMapper());
	}

}
