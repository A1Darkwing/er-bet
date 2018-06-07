package com.csc.betapp.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csc.betapp.model.Match;
import com.csc.betapp.model.MatchType;
import com.csc.betapp.service.AdminService;

/**
 * @author PH
 * 
 */
@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "data/user/add", method = RequestMethod.POST)
	@ResponseBody
	public Object[] addUser(@RequestBody Map params) {
		try {
			List<String> users = (List<String>) params.get("users");
			List<Integer> ids = adminService.insertUser(users);
			return new Object[] { "done", ids };
		} catch (Exception ex) {
			return new Object[] { ex.getMessage() };
		}
	}

	@RequestMapping(value = "data/user/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(@PathVariable("id") int id) {
		try {
			adminService.deleteUser(id);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "done";
	}

	@RequestMapping(value = "data/match/get/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Match getMatchById(@PathVariable("id") int id) {
		return adminService.getMatchById(id);
	}

	@RequestMapping(value = "data/match/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String updateMatch(@PathVariable("id") int id,
			@RequestBody Map params) {
		try {
			Double rate = Double.valueOf((String) params.get("rate"));
			Integer scoreTeam1 = null;
			String score1 = (String) params.get("scoreTeam1");
			if (!StringUtils.isEmpty(score1)) {
				scoreTeam1 = new Integer(score1);
			}
			// Integer scoreTeam2 = Integer.valueOf((String)
			// params.get("scoreTeam2"));

			Integer scoreTeam2 = null;
			String score2 = (String) params.get("scoreTeam2");
			if (!StringUtils.isEmpty(score2)) {
				scoreTeam2 = new Integer(score2);
			}

			Integer teamSelected = (Integer) params.get("teamSelected");
			adminService.updateMatch(id, rate, scoreTeam1, scoreTeam2,
					teamSelected);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "done";
	}

	@RequestMapping(value = "data/match/create", method = RequestMethod.POST)
	@ResponseBody
	public String createMatch(@RequestBody Map params) {
		try {
			String team1 = (String) params.get("team1");
			String team2 = (String) params.get("team2");
			String type = (String) params.get("type");
			String datetime = (String) params.get("datetime");

			int team1Id;
			int team2Id;
			int typeId;
			Date matchDate = new Date();
			if (!StringUtils.isEmpty(team1)) {
				team1Id = new Integer(team1);
			} else {
				return "Team 1 is not valid";
			}
			if (!StringUtils.isEmpty(team2)) {
				team2Id = new Integer(team2);
			} else {
				return "Team 2 is not valid";
			}
			if (!StringUtils.isEmpty(type)) {
				typeId = new Integer(type);
			} else {
				return "Team 1 is not valid";
			}
			try {
				matchDate = DateUtils.parseDate(datetime, "MM/dd/yyyy hh:mm a");
			} catch (Exception e) {
				return "Match date is in wrong format!";
			}

			adminService.createMatch(team1Id, team2Id, typeId, matchDate);
			return "done";
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}

	@RequestMapping(value = "data/type/get/{id}", method = RequestMethod.POST)
	@ResponseBody
	public MatchType getMatchTypeById(@PathVariable("id") int id) {
		return adminService.getMatchTypeById(id);
	}

	@ResponseBody
	@RequestMapping(value = "data/type/update/{id}", method = RequestMethod.POST)
	public String updateMatchType(@PathVariable("id") int id,
			@RequestBody Map params) {
		try {
			int stake = Integer.valueOf((String) params.get("stake"));
			adminService.updateMatchType(id, stake);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "done";
	}

	@RequestMapping(value = "c/customer/add", method = RequestMethod.POST)
	@ResponseBody
	public String[] addCustomer(@RequestBody Map params) {
		try {
			String customer = (String) params.get("customer");
			String user = (String) params.get("user");
			Integer nop = NumberUtils.toInt((String) params.get("nop"));
			int id = adminService.insertCustomer(customer, user, nop);
			return new String[] { "done", String.valueOf(id) };
		} catch (Exception ex) {
			System.out.println(ex);
			return new String[] { ex.getMessage() };
		}
	}

	@RequestMapping(value = "c/customer/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCustomer(@PathVariable("id") int id) {
		try {
			adminService.deleteCustomer(id);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "done";
	}
}
