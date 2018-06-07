package com.csc.betapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csc.betapp.dao.GroupDao;
import com.csc.betapp.dao.MatchDao;
import com.csc.betapp.dao.TeamDao;
import com.csc.betapp.model.Group;
import com.csc.betapp.model.Match;
import com.csc.betapp.model.MatchRecordLine;
import com.csc.betapp.model.Team;
import com.csc.betapp.model.TeamStatistic;
import com.csc.betapp.model.Type;
import com.csc.betapp.session.SessionHolder;

@Service("loadingService")
@Transactional(readOnly = true)
public class LoadingService {
	@Autowired
	private MatchDao matchDao;
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private GroupDao groupDao;

	/**
	 * Get all today matches
	 * 
	 * @return
	 */
	public List<MatchRecordLine> loadTodayMatches() {
		List<Match> allMatches = matchDao.getAllMatches(SessionHolder
				.getInstance().getCustomerId());
		List<MatchRecordLine> todayMatches = new ArrayList<MatchRecordLine>();
		int count = 0;
		for (Match match : allMatches) {
			MatchRecordLine line = null;
			int diffDays = countDiffDay(match.getMatchTime(), new Date());
			if (diffDays >= 0 && diffDays <= 2) {
				Team team1 = (teamDao.getTeamByTeamId(match.getTeam1Id()))
						.get(0);
				Team team2 = (teamDao.getTeamByTeamId(match.getTeam2Id()))
						.get(0);
				Type type = match.getMatchType().getType();
				double team1Rate = 0, team2Rate = 0;
				if(match.getTeamSelected() == 0){
					team1Rate = Math.abs(match.getMatchRate());
					
				} else {
					team2Rate = Math.abs(match.getMatchRate());
				}
				line = new MatchRecordLine(match.getId(), team1.getName(),
						team2.getName(), team1.getFlag(), team2.getFlag(),
						match.getScoreTeam1(), match.getScoreTeam2(),
						match.getMatchRate(), team1Rate, team2Rate, match.getMatchTime(),
						type.getName());
				todayMatches.add(line);
			}
		}
		return todayMatches;
	}

	/**
	 * Get next matches
	 * 
	 * @return
	 * @throws ParseException 
	 */
	public List<MatchRecordLine> loadIncomingMatches() throws ParseException {
		List<Match> allMatches = matchDao.getAllMatches(SessionHolder
				.getInstance().getCustomerId());
		List<MatchRecordLine> incomingMatches = new ArrayList<MatchRecordLine>();
		int count = 0;
		for (Match match : allMatches) {
			MatchRecordLine line = null;
			int diffDays = countDiffDay(match.getMatchTime(), new Date());
			if (diffDays > 2) {
				Team team1 = (teamDao.getTeamByTeamId(match.getTeam1Id()))
						.get(0);
				Team team2 = (teamDao.getTeamByTeamId(match.getTeam2Id()))
						.get(0);
				Type type = match.getMatchType().getType();
				line = new MatchRecordLine(match.getId(), team1.getName(),
						team2.getName(), team1.getFlag(), team2.getFlag(),
						match.getScoreTeam1(), match.getScoreTeam2(),
						match.getMatchRate(), 0, 0, match.getMatchTime(),
						type.getName());
				incomingMatches.add(line);
			}
		}
		return incomingMatches;
	}

	/**
	 * Get all happened matches
	 * 
	 * @return
	 * @throws ParseException 
	 */
	public List<MatchRecordLine> loadRecentMatches() throws ParseException {

		List<Match> allMatches = matchDao.getAllMatches(SessionHolder
				.getInstance().getCustomerId());
		List<MatchRecordLine> recentMatches = new ArrayList<MatchRecordLine>();
		int count = 0;
		for (Match match : allMatches) {
			count++;
			MatchRecordLine line = null;
			if (compareTwoDates(match.getMatchTime(), new Date()) == 2) {
				Team team1 = (teamDao.getTeamByTeamId(match.getTeam1Id()))
						.get(0);
				Team team2 = (teamDao.getTeamByTeamId(match.getTeam2Id()))
						.get(0);
				Type type = match.getMatchType().getType();
				line = new MatchRecordLine(count, team1.getName(),
						team2.getName(), team1.getFlag(), team2.getFlag(),
						match.getScoreTeam1(), match.getScoreTeam2(),
						match.getMatchRate(), 0, 0, match.getMatchTime(),
						type.getName());
				recentMatches.add(line);
			}
		}
		return recentMatches;
	}

	/**
	 * Get detail about team information of specific group
	 * 
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Collection<TeamStatistic>> loadGroupDetail() throws ParseException {
		Map<String, Collection<TeamStatistic>> groupStatistics = new  TreeMap<String, Collection<TeamStatistic>>();
		List<MatchRecordLine> recentMatches = loadRecentMatches();
		List<TeamStatistic> teamStatistics = new ArrayList<TeamStatistic>();
		if (recentMatches.size() > 0) {
			List<TeamStatistic> teamDetail = new ArrayList<TeamStatistic>();
			for (MatchRecordLine match : recentMatches) {
				
				teamDetail.addAll(getTeamScores(match));
			}

			teamStatistics = calculateScoreInTotal(teamDetail);
		}
		List<Team> lstTeam = teamDao.getAllTeam();
		List<Group> lstGroup = groupDao.getAllGroup();
		for (Group group : lstGroup) {
			List<TeamStatistic> temp = new ArrayList<TeamStatistic>();
			for (Team team : lstTeam) {
				if (group.getId() == team.getGroupId()) {
					boolean isExist = false;
					for (TeamStatistic ts : teamStatistics) {
						if (team.getName().equals(ts.getTeamName())) {
							temp.add(ts);
							isExist = true;
						}
					}
					if (!isExist) {
						List<Group> groups = groupDao.getGroupById(team.getGroupId());
						temp.add(new TeamStatistic(team.getName(), groups.get(0).getName(), 0, 0, 0, 0, 0));
					}
				}
			}
			Collections.sort(temp);
			groupStatistics.put(group.getName(), temp);
		}
		
		return groupStatistics;
	}

	private List<TeamStatistic> getTeamScores(MatchRecordLine match) {
		List<TeamStatistic> teamStatistics = new ArrayList<TeamStatistic>();
		Team team1 = teamDao.getTeamByName(match.getTeam1Name()).get(0);
		Team team2 = teamDao.getTeamByName(match.getTeam2Name()).get(0);
		Group group = groupDao.getGroupById(team1.getGroupId()).get(0);
		if ("Qualification".equals(match.getMatchType())) {
			if(match.getTeam1Score() != null && match.getTeam2Score() != null){
				if (match.getTeam1Score() > match.getTeam2Score()) {
					teamStatistics.add(new TeamStatistic(team1.getName(), group
							.getName(), 1, 1, 0, 0, 3));
					teamStatistics.add(new TeamStatistic(team2.getName(), group
							.getName(), 1, 0, 0, 1, 0));
				} else if (match.getTeam1Score() == match.getTeam2Score()) {
					teamStatistics.add(new TeamStatistic(team1.getName(), group
							.getName(), 1, 0, 1, 0, 1));
					teamStatistics.add(new TeamStatistic(team2.getName(), group
							.getName(), 1, 0, 1, 0, 1));
				} else {
					teamStatistics.add(new TeamStatistic(team2.getName(), group
							.getName(), 1, 1, 0, 0, 3));
					teamStatistics.add(new TeamStatistic(team1.getName(), group
							.getName(), 1, 0, 0, 1, 0));
				}
			}
		}
		return teamStatistics;
	}

	private List<TeamStatistic> calculateScoreInTotal(
			List<TeamStatistic> lstTeamDetail) {
		List<TeamStatistic> teamStatistics = new ArrayList<TeamStatistic>();
		
		for (int i = 0; i < lstTeamDetail.size(); i++) {
			TeamStatistic ts = lstTeamDetail.get(i);
			TeamStatistic result = new TeamStatistic(ts.getTeamName(), ts.getGroupName(), 0, 0, 0, 0, 0);
			if (!teamStatistics.contains(ts)) {
				for (int j = 0; j < lstTeamDetail.size(); j++) {
					if (ts.getTeamName().equals(lstTeamDetail.get(j).getTeamName())) {
						result.setPlayed(result.getPlayed()
								+ lstTeamDetail.get(j).getPlayed());
						result.setDraws(result.getDraws()
								+ lstTeamDetail.get(j).getDraws());
						result.setLosts(result.getLosts()
								+ lstTeamDetail.get(j).getLosts());
						result.setPoints(result.getPoints()
								+ lstTeamDetail.get(j).getPoints());
						result.setWins(result.getWins()
								+ lstTeamDetail.get(j).getWins());
					}
				}
				teamStatistics.add(result);
			}
		}
		Collections.sort(teamStatistics);
		return teamStatistics;
	}
	private int compareTwoDates(Date date1, Date date2) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date1 = sdf.parse(sdf.format(date1));
		date2 = sdf.parse(sdf.format(date2));
		if (date1.after(date2)) {
			return 1;
		} else if(date1.before(date2)){
			return 2;
		} else {
			return 3;
		}
	}
	
	private int countDiffDay(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();

	    c1.setTime(date1);
	    c2.setTime(date2);
	    
	    int returnInt = 0;
	    while (!c2.after(c1)) {
	      c2.add(Calendar.DAY_OF_MONTH, 1);
	      returnInt++;
	    }

	    if (returnInt > 0) {
	      returnInt = returnInt - 1;
	    }

	    return returnInt;
	    
	}
}
