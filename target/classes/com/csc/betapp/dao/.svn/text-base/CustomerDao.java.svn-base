package com.csc.betapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.csc.betapp.dao.mapper.CustomerMapper;
import com.csc.betapp.model.Customer;

/**
 * @author PH
 *
 */
@Repository("customerDao")
public class CustomerDao extends BaseDao {
	public List<Customer> getCustomerList() {
		String sql = "SELECT Id, Name FROM customers where Name!='SYSTEM'";
		return getJdbcTemplate().query(sql, new CustomerMapper());
	}
	
	public Customer getCustomer(int customerId) {
		String sql = "SELECT Id, Name, NoP FROM customers where Id = ?";
		return getJdbcTemplate().queryForObject(sql, new CustomerMapper(), customerId);
	}

	public long insert(final String customerName, final int nop) {
		final String sql = "INSERT INTO customers (Name, NoP) VALUES (?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator statementCreator = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, customerName);
				pstmt.setInt(2, nop);
				return pstmt;
			}
		};
		getJdbcTemplate().update(statementCreator, keyHolder);
		return keyHolder.getKey().longValue();
	}

	public void deleteCustomer(int customerId) {
		String sql = "DELETE FROM customers " + "WHERE Id = ?";
		getJdbcTemplate().update(sql, customerId);
	}

	public void initData(int customerId, int matchTypeId) {
		String sql = "";

		// insert MatchTypes
		sql = CustomerData.MATCH_TYPES;
		sql = sql.replaceAll("@cust@", String.valueOf(customerId));
		getJdbcTemplate().update(sql);

		// insert MATCHES
		sql = CustomerData.MATCHES;
		sql = sql.replaceAll("@cust@", String.valueOf(customerId));
		sql = sql.replaceAll("@mt@", String.valueOf(matchTypeId));
		getJdbcTemplate().update(sql);
	}
}
