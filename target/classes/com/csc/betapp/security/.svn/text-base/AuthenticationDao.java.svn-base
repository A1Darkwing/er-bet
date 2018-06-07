package com.csc.betapp.security;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csc.betapp.dao.BaseDao;
import com.csc.betapp.dao.mapper.UserMapper;
import com.csc.betapp.model.User;

@Repository("authenticationDao")
public class AuthenticationDao extends BaseDao {
	public List<User> getUserByUserName(String userName) {
		String sql = "select Id, Username, Password, Role, CustomerId from users where Username = ?";
		return getJdbcTemplate().query(sql, new UserMapper(), userName);
	}
}
