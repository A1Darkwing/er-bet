package com.csc.betapp.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csc.betapp.model.BetInfo;
import com.csc.betapp.model.MatchRecordLine;
import com.csc.betapp.model.UserRecordDetail;
import com.csc.betapp.service.AdminService;
import com.csc.betapp.service.BetAppService;
import com.csc.betapp.service.BetService;
import com.csc.betapp.service.LoadingService;
import com.csc.betapp.session.SessionHolder;

/**
 * @author PH
 *
 */
@Controller
public class ViewController {
	
	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+7"));
	}

	@Autowired
	private AdminService adminService;
	@Autowired
	private BetAppService betAppService;
	@Autowired
	private LoadingService loadingService;
	@Autowired
	private BetService betService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws ParseException, IOException {
		if (request.isUserInRole("ROLE_SITE_ADMIN")) {
			response.sendRedirect("site");
			return null;
		}
		model.addAttribute("todayMatches", loadingService.loadTodayMatches());
		model.addAttribute("incomingMatches",
				loadingService.loadIncomingMatches());
		model.addAttribute("recentMatches", loadingService.loadRecentMatches());
		model.addAttribute("groupInfoDetail", loadingService.loadGroupDetail());

		return "home";
	}

	@RequestMapping(value = "/report-team", method = RequestMethod.GET)
	public String reportTeam(ModelMap map) {
		map.addAttribute("records", betAppService.getRecords());
		return "report-team";
	}

	@RequestMapping(value = "/report-user", method = RequestMethod.GET)
	public String reportUser(ModelMap map) {
		map.addAttribute("records", betAppService.getUserRecords());
		return "report-user";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {
		return "admin";
	}

	@RequestMapping(value = "/admin-user", method = RequestMethod.GET)
	public String manageUser(ModelMap model) {
		model.addAttribute("users", adminService.getAllUsers());
		return "/admin/user";
	}

	@RequestMapping(value = "/admin-match", method = RequestMethod.GET)
	public String manageMatch(ModelMap model) {
		model.addAttribute("matches", adminService.getAllMatches());
		return "/admin/match";
	}

	@RequestMapping(value = "/admin-type", method = RequestMethod.GET)
	public String manageType(ModelMap model) {
		model.addAttribute("types", adminService.getAllTypes());
		return "/admin/type";
	}

	@RequestMapping(value = "/admin-match-create", method = RequestMethod.GET)
	public String createMatch(ModelMap model) {
		model.addAttribute("teams", adminService.getAllTeams());
		model.addAttribute("types", adminService.getAllTypes());
		return "/admin/match-create";
	}

	@ResponseBody
	@RequestMapping(value = "/p/user-bet", method = RequestMethod.POST)
	public List<List<String>> userBet(HttpServletRequest request) {
		try {
			List<List<String>> results = betAppService.getUserBet(NumberUtils
					.toInt(request.getParameter("matchId")));
			return results;
		} catch (Exception e) {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/p/user-record", method = RequestMethod.POST)
	public List<UserRecordDetail> userRecord(HttpServletRequest request) {
		try {
			List<UserRecordDetail> results = betAppService
					.getUserRecordDetails(NumberUtils.toInt(request
							.getParameter("userId")));
			return results;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/p/changepass", method = RequestMethod.POST)
	public String changePass(HttpServletRequest request) {
		try {
			betAppService.changePass(request.getParameter("oldPassword"),
					request.getParameter("newPassword"));
		} catch (Exception e) {
			return e.getMessage();
		}
		return "done";
	}

	@ResponseBody
	@RequestMapping(value = "/betting", method = RequestMethod.POST)
	public String getAll(HttpServletRequest request) {
		String matchId = (String) request.getParameter("matchId");
		String votedTeam = (String) request.getParameter("team");
		if ("".equals(matchId) || "".equals(votedTeam)) {
			return "Input Error";
		} else if (adminService.getMatchById(Integer.parseInt(matchId))
				.getMatchTime().before(new Date())) {
			return "Invalid Date";
		} else {
			BetInfo betInfo = betService
					.getBetInfoFromMatchIdAndUserId(Integer.parseInt(matchId),
							SessionHolder.getInstance().getUserId());
			if (betInfo == null) {
				betInfo = new BetInfo();
				betInfo.setUserId(SessionHolder.getInstance().getUserId());
				betInfo.setBetTime(new Date());
				betInfo.setMatchId(Integer.parseInt(matchId));
				betInfo.setSelection(Integer.parseInt(votedTeam));
				betService.insertBetInfo(betInfo, SessionHolder.getInstance()
						.getCustomerId());
			} else {
				betService.updateSelection(betInfo.getId(), Integer
						.parseInt(votedTeam), SessionHolder.getInstance()
						.getCustomerId());
			}
			return "success";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getCurrentBets", method = RequestMethod.POST)
	public List<BetInfo> getCurrentBets() {
		try {
			List<MatchRecordLine> todayMatches = loadingService
					.loadTodayMatches();
			List<MatchRecordLine> incomingMatches = loadingService
					.loadIncomingMatches();
			List<BetInfo> lstBetInfo = new ArrayList<BetInfo>();
			for (MatchRecordLine line : todayMatches) {
				BetInfo bi = betService.getBetInfoFromMatchIdAndUserId(
						line.getId(), SessionHolder.getInstance().getUserId());
				if (bi != null) {
					lstBetInfo.add(bi);
				}
			}
			for (MatchRecordLine line : incomingMatches) {
				BetInfo bi = betService.getBetInfoFromMatchIdAndUserId(
						line.getId(), SessionHolder.getInstance().getUserId());
				if (bi != null) {
					lstBetInfo.add(bi);
				}
			}
			return lstBetInfo;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/site", method = RequestMethod.GET)
	public String manageCustomer(ModelMap model) {
		model.addAttribute("customers", adminService.getAllCustomers());
		return "/admin/customer";
	}
}
