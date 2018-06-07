package com.csc.betapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.csc.betapp.dao.BetInfoDao;
import com.csc.betapp.dao.CustomerDao;
import com.csc.betapp.dao.MatchDao;
import com.csc.betapp.dao.TeamDao;
import com.csc.betapp.dao.TypeDao;
import com.csc.betapp.dao.UserDao;
import com.csc.betapp.exception.BetAppException;
import com.csc.betapp.model.BetInfo;
import com.csc.betapp.model.Customer;
import com.csc.betapp.model.Match;
import com.csc.betapp.model.MatchType;
import com.csc.betapp.model.Team;
import com.csc.betapp.model.Type;
import com.csc.betapp.model.User;
import com.csc.betapp.session.SessionHolder;

@Service("adminService")
public class AdminService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TypeDao typeDao;
	@Autowired
	private MatchDao matchDao;
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private BetInfoDao betInfoDao;

	public Collection<User> getAllUsers() {
		return userDao.getUserList(SessionHolder.getInstance().getCustomerId());
	}

	@Transactional(rollbackFor = Exception.class)
	public List<Integer> insertUser(List<String> usernames)
			throws BetAppException {
		for (String username : usernames) {
			if (username.isEmpty()) {
				throw new BetAppException("user.empty");
			}
			if (!org.apache.commons.lang3.StringUtils.isAlphanumeric(username)) {
				throw new BetAppException("user.badword");
			}
		}

		if (customerDao
				.getCustomer(SessionHolder.getInstance().getCustomerId())
				.getNop() < userDao.getCountUser(SessionHolder.getInstance()
				.getCustomerId())) {
			throw new BetAppException("user.limit");
		}

		try {
			List<Integer> insertedId = new ArrayList<Integer>();
			for (String username : usernames) {
				User user = new User(username);
				user.setRole("ROLE_USER");
				long longId = userDao.insert(SessionHolder.getInstance()
						.getCustomerId(), user);
				Integer id = new Integer((new Long(longId)).intValue());
				insertedId.add(id);
			}
			return insertedId;
		} catch (DuplicateKeyException de) {
			throw new BetAppException("user.duplicate");
		}
	}

	public void insertAdminUser(int newCustomerId, String username)
			throws BetAppException {
		if (username.isEmpty()) {
			throw new BetAppException("user.empty");
		}
		if (!org.apache.commons.lang3.StringUtils.isAlphanumeric(username)) {
			throw new BetAppException("user.badword");
		}
		try {
			User user = new User(username);
			user.setRole("ROLE_ADMIN");
			userDao.insert(newCustomerId, user);
		} catch (DuplicateKeyException de) {
			throw new BetAppException("user.duplicate");
		}
	}

	public void deleteUser(int userId) {
		userDao.delete(SessionHolder.getInstance().getCustomerId(), userId);
	}

	public List<Type> getAllTypes() {
		return typeDao.getTypes();
	}

	public MatchType getMatchTypeById(int id) {
		return typeDao.getMatchTypeById(id).get(0);
	}

	public void updateMatchType(int id, int stake) throws BetAppException {
		if (stake < 0) {
			throw new BetAppException("type.negative");
		}
		typeDao.update(SessionHolder.getInstance().getCustomerId(), id, stake);
	}

	public List<Match> getAllMatches() {
		List<Match> allMatches = matchDao.getAllMatches(SessionHolder
				.getInstance().getCustomerId());
		return allMatches;
	}

	public Match getMatchById(int id) {
		Match match = matchDao.getById(SessionHolder.getInstance()
				.getCustomerId(), id);
		return match;
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateMatch(int id, double rate, Integer scoreTeam1,
			Integer scoreTeam2, Integer teamSelected) throws BetAppException {

		if (rate < 0) {
			throw new BetAppException("rate.negative");
		}

		if (teamSelected == 1) {
			rate = -rate;
		}

		Match currMatch = getMatchById(id);
		Date endMatchTime = currMatch.getMatchTime();
		endMatchTime = DateUtils.addMinutes(endMatchTime, 120);
		Date current = new Date();
		if (current.before(endMatchTime)) {
			scoreTeam1 = null;
			scoreTeam2 = null;
		} else {
			if (scoreTeam1 == null) {
				throw new BetAppException("Score of "
						+ currMatch.getTeam1Name() + " must not be empty");
			}
			if (scoreTeam2 == null) {
				throw new BetAppException("Score of "
						+ currMatch.getTeam2Name() + " must not be empty");
			}
			if (scoreTeam1 < 0) {
				throw new BetAppException("Score of "
						+ currMatch.getTeam1Name() + " must not be less than 0");
			}
			if (scoreTeam2 < 0) {
				throw new BetAppException("Score of "
						+ currMatch.getTeam2Name() + " must not be less than 0");
			}

		}

		matchDao.update(SessionHolder.getInstance().getCustomerId(), id, rate,
				scoreTeam1, scoreTeam2);

		if (scoreTeam1 != null && scoreTeam2 != null) {
			updateBet(currMatch, scoreTeam1, scoreTeam2);
		}
	}

	public Collection<Customer> getAllCustomers() {
		return customerDao.getCustomerList();
	}

	@Transactional(rollbackFor = Exception.class)
	public int insertCustomer(String customerName, String user, Integer nop)
			throws BetAppException {
		if (StringUtils.isEmpty(customerName)) {
			throw new BetAppException("customer.emptyName");
		}

		Long id = new Long(customerDao.insert(customerName, nop));
		int matchTypeId = typeDao.getMatchTypeById(1).get(0).getId();
		insertAdminUser(id.intValue(), user);
		customerDao.initData(id.intValue(), matchTypeId);
		return id.intValue();
	}

	public void deleteCustomer(int id) {
		customerDao.deleteCustomer(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateBet(Match match, Integer scoreTeam1, Integer scoreTeam2) {
		Double newScoreTeam1 = new Double(scoreTeam1);
		Double newScoreTeam2 = new Double(scoreTeam2);
		if (match.getTeamSelected() == 0) {
			newScoreTeam1 = scoreTeam1 + Math.abs(match.getMatchRate());
		} else {
			newScoreTeam2 = scoreTeam2 + Math.abs(match.getMatchRate());
		}

		List<BetInfo> betInfos = betInfoDao.getAllBetFromMatchId(match.getId(),
				SessionHolder.getInstance().getCustomerId());

		List<User> users = userDao.getUserList(SessionHolder.getInstance()
				.getCustomerId());

		MatchType matchType = typeDao.getMatchTypeById(
				match.getMatchType().getId()).get(0);

		for (BetInfo betInfo : betInfos) {
			String result = "L";
			Integer money = 0;

			if (betInfo.getSelection() == 0) {
				result = "L";
			} else if (Double.compare(newScoreTeam1, newScoreTeam2) == 0) {
				result = "D";
			} else if (Double.compare(newScoreTeam1, newScoreTeam2) < 0) {
				if (betInfo.getSelection() == 1) {
					result = "L";
				} else {
					result = "W";
				}
			} else {
				if (betInfo.getSelection() == 1) {
					result = "W";
				} else {
					result = "L";
				}
			}

			if (result.equalsIgnoreCase("L")) {
				money = matchType.getStake();
			} else if (result.equalsIgnoreCase("D")) {
				money = matchType.getStake() / 2;
			} else {
				money = 0;
			}

			betInfoDao.updateAfterMatch(result, money, SessionHolder
					.getInstance().getCustomerId(), betInfo.getId());

			// remove updated user out of list after update Bet Info
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getId() == betInfo.getUserId()) {
					users.remove(i);
					break;
				}
			}
		}

		// Loop all the user that did not bet
		for (User user : users) {
			BetInfo betInfo = new BetInfo();
			betInfo.setUserId(user.getId());
			betInfo.setMatchId(match.getId());
			betInfo.setResult("L");
			betInfo.setBetTime(match.getMatchTime());
			betInfo.setSelection(0);
			betInfo.setLoseMoney(matchType.getStake());
			betInfoDao.insertBetInfo(betInfo, SessionHolder.getInstance()
					.getCustomerId());
		}
	}

	public List<Team> getAllTeams() {
		return teamDao.getAllTeam();
	}

	public void createMatch(Integer team1, Integer team2, Integer type,
			Date matchDate) throws BetAppException {
		int matchTypeId = typeDao.getMatchTypeById(type).get(0).getId();
		matchDao.insert(team1, team2, matchTypeId, SessionHolder.getInstance()
				.getCustomerId(), matchDate);
	}
}
