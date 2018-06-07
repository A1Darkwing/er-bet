package com.csc.betapp.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.csc.betapp.dao.ReportDao;
import com.csc.betapp.dao.UserDao;
import com.csc.betapp.exception.BetAppException;
import com.csc.betapp.model.MatchRecord;
import com.csc.betapp.model.MatchRecordLine;
import com.csc.betapp.model.User;
import com.csc.betapp.model.UserRecord;
import com.csc.betapp.model.UserRecordDetail;
import com.csc.betapp.session.SessionHolder;

@Service
public class BetAppService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ReportDao reportDao;

	public void changePass(String oldPassword, String password)
			throws BetAppException {
		if (StringUtils.isEmpty(oldPassword)) {
			throw new BetAppException("oldPassword.empty");
		}
		if (StringUtils.isEmpty(password)) {
			throw new BetAppException("password.empty");
		}
		if (userDao.checkPassword(SessionHolder.getInstance().getUserId(),
				getMd5String(oldPassword))) {
			throw new BetAppException("oldPassword.false");
		}
		userDao.updatePassword(SessionHolder.getInstance().getUserId(),
				getMd5String(password));
	}

	public List<MatchRecord> getRecords() {
		List<MatchRecord> records = reportDao.getMatchTypes(SessionHolder
				.getInstance().getCustomerId());
		for (MatchRecord record : records) {
			List<MatchRecordLine> lines = reportDao
					.getMatchRecordLines(SessionHolder.getInstance()
							.getCustomerId(), record.getId());
			for (MatchRecordLine line : lines) {
				if (line.getRate() > 0) {
					line.setTeam1Rate(line.getRate());
				} else {
					line.setTeam2Rate(Math.abs(line.getRate()));
				}
				line.setTeam1Bet(reportDao.getCountBet(SessionHolder
						.getInstance().getCustomerId(), line.getId(), 1));
				line.setTeam2Bet(reportDao.getCountBet(SessionHolder
						.getInstance().getCustomerId(), line.getId(), 2));
			}
			record.setLines(lines);
		}
		return records;
	}

	public List<List<String>> getUserBet(int matchId) {
		List<List<String>> results = new ArrayList<List<String>>();
		results.add(reportDao.getUserBet(SessionHolder.getInstance()
				.getCustomerId(), matchId, 1));
		results.add(reportDao.getUserBet(SessionHolder.getInstance()
				.getCustomerId(), matchId, 2));
		results.add(reportDao.getUserBet(SessionHolder.getInstance()
				.getCustomerId(), matchId, 0));
		return results;
	}

	public List<UserRecord> getUserRecords() {
		List<User> users = userDao.getUserList(SessionHolder.getInstance()
				.getCustomerId());
		List<UserRecord> records = new ArrayList<UserRecord>();
		for (User user : users) {
			UserRecord record = new UserRecord();
			record.setId(user.getId());
			record.setUsername(user.getUsername());
			record.setCountWin(reportDao.getCountUserResult(SessionHolder
					.getInstance().getCustomerId(), user.getId(), "W"));
			record.setCountDraw(reportDao.getCountUserResult(SessionHolder
					.getInstance().getCustomerId(), user.getId(), "D"));
			record.setCountLose(reportDao.getCountUserResult(SessionHolder
					.getInstance().getCustomerId(), user.getId(), "L"));
			record.setLostMoney(reportDao.getCountUserLosingMoney(
					SessionHolder.getInstance().getCustomerId(), user.getId()));
			records.add(record);
		}
		Collections.sort(records);
		Collections.reverse(records);
		return records;
	}

	public List<UserRecordDetail> getUserRecordDetails(int userId) {
		return reportDao.getUserRecordDetails(SessionHolder.getInstance()
				.getCustomerId(), userId);
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
