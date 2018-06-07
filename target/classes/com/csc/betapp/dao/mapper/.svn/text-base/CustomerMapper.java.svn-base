package com.csc.betapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.betapp.model.Customer;

public class CustomerMapper implements RowMapper<Customer> {

	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getInt("Id"));
		customer.setName(rs.getString("Name"));
		try {
			customer.setNop(rs.getInt("NoP"));
		} catch (Exception e) {
			
		}
		return customer;
	}
}
