package com.csc.betapp.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.csc.betapp.dao.mapper.UserMapper;
import com.csc.betapp.model.User;

/**
 * @author PH
 *
 */
@Repository("userDao")
public class UserDao extends BaseDao {

	public List<User> getUserList(int customerId) {
		String sql = "SELECT Id, Username, Password, Role, CustomerId FROM users WHERE CustomerId = ?";
		return getJdbcTemplate().query(sql, new Object[] { customerId },
				new UserMapper());
	}

	public User getUserById(int customerId, int userId) {
		String sql = "SELECT Id, Username FROM users WHERE CustomerId = ? AND Id = ?";
		return getJdbcTemplate().queryForObject(sql,
				new Object[] { customerId, userId }, new UserMapper());
	}

	public long insert(final int customerId, final User user) {
		final String sql = "INSERT INTO users (UserName, PassWord, CustomerId, Role) VALUES (?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator statementCreator = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, getMd5String(User.DEFAUL_PASSWORD));
				pstmt.setInt(3, customerId);
				pstmt.setString(4, user.getRole());
				return pstmt;
			}
		};
		getJdbcTemplate().update(statementCreator, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	public Integer getCountUser(int customerId) {
		String sql = "select count(Id) from users where customerId = ?";
		return getJdbcTemplate().queryForObject(sql, Integer.class, customerId);
	}

	public void insertBatch(final int customerId, final List<User> users) {
		String sql = "INSERT INTO users(UserName, Password, CustomerId, Role) "
				+ "VALUES (?,?,?,?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				User user = users.get(i);
				ps.setString(1, user.getUsername());
				ps.setString(2, getMd5String(User.DEFAUL_PASSWORD));
				ps.setInt(3, customerId);
				ps.setString(4, user.getRole());
			}

			public int getBatchSize() {
				return users.size();
			}
		});
	}

	public void update(int customerId, User user) {
		String sql = "UPDATE users SET " + "UserName = ?, Password = ? "
				+ "WHERE Id = ? AND CustomerId = ?";
		getJdbcTemplate().update(sql, user.getUsername(), user.getPassword(),
				user.getId(), customerId);
	}

	public void delete(int customerId, int userId) {
		String sql = "DELETE FROM users " + "WHERE Id = ? AND CustomerId = ?";
		getJdbcTemplate().update(sql, userId, customerId);
	}

	public boolean checkPassword(Integer userId, String password) {
		String sql = "Select Id from users where Id = ? and Password = ?";
		return CollectionUtils.isEmpty(getJdbcTemplate().queryForList(sql,
				userId, password));
	}

	public void updatePassword(Integer userId, String password) {
		String sql = "Update users set Password = ? where Id = ?";
		getJdbcTemplate().update(sql, password, userId);
	}

	public static String getMd5String(String input) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(input.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
