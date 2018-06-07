package com.csc.betapp.model;

import java.util.Date;

public class UserRecordDetail {
	private String username;
	private String teamName;
	private String teamFlag;
	private String team1Name;
	private String team2Name;
	private String team1Flag;
	private String team2Flag;
	private Integer team1Score;
	private Integer team2Score;
	private Date matchDate;
	private Date betTime;
	private String resultType;
	private int losingMoney;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamFlag() {
		return teamFlag;
	}

	public void setTeamFlag(String teamFlag) {
		this.teamFlag = teamFlag;
	}

	public String getTeam1Name() {
		return team1Name;
	}

	public void setTeam1Name(String team1Name) {
		this.team1Name = team1Name;
	}

	public String getTeam2Name() {
		return team2Name;
	}

	public void setTeam2Name(String team2Name) {
		this.team2Name = team2Name;
	}

	public String getTeam1Flag() {
		return team1Flag;
	}

	public void setTeam1Flag(String team1Flag) {
		this.team1Flag = team1Flag;
	}

	public String getTeam2Flag() {
		return team2Flag;
	}

	public void setTeam2Flag(String team2Flag) {
		this.team2Flag = team2Flag;
	}

	public Integer getTeam1Score() {
		return team1Score;
	}

	public void setTeam1Score(Integer team1Score) {
		this.team1Score = team1Score;
	}

	public Integer getTeam2Score() {
		return team2Score;
	}

	public void setTeam2Score(Integer team2Score) {
		this.team2Score = team2Score;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public Date getBetTime() {
		return betTime;
	}

	public void setBetTime(Date betTime) {
		this.betTime = betTime;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public int getLosingMoney() {
		return losingMoney;
	}

	public void setLosingMoney(int losingMoney) {
		this.losingMoney = losingMoney;
	}

}
