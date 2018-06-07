package com.csc.betapp.model;

import java.util.Date;

public class MatchRecordLine {

	private int id;
	private String team1Name;
	private String team2Name;
	private String team1Flag;
	private String team2Flag;
	private Integer team1Score;
	private Integer team2Score;
	private int team1Bet;
	private int team2Bet;
	private double rate;
	private double team1Rate;
	private double team2Rate;
	private Date matchTime;
	private String matchType;
	
	public MatchRecordLine(int id, String team1Name, String team2Name,
			String team1Flag, String team2Flag, Integer team1Score, Integer team2Score,
			double rate, double team1Rate, double team2Rate, Date matchTime, String matchType) {
		super();
		this.id = id;
		this.team1Name = team1Name;
		this.team2Name = team2Name;
		this.team1Flag = team1Flag;
		this.team2Flag = team2Flag;
		this.team1Score = team1Score;
		this.team2Score = team2Score;
		this.rate = rate;
		this.team1Rate = team1Rate;
		this.team2Rate = team2Rate;
		this.matchTime = matchTime;
		this.matchType = matchType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getTeam1Rate() {
		return team1Rate;
	}

	public void setTeam1Rate(double team1Rate) {
		this.team1Rate = team1Rate;
	}

	public double getTeam2Rate() {
		return team2Rate;
	}

	public void setTeam2Rate(double team2Rate) {
		this.team2Rate = team2Rate;
	}

	public Date getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}
	
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public int getTeam1Bet() {
		return team1Bet;
	}

	public void setTeam1Bet(int team1Bet) {
		this.team1Bet = team1Bet;
	}

	public int getTeam2Bet() {
		return team2Bet;
	}

	public void setTeam2Bet(int team2Bet) {
		this.team2Bet = team2Bet;
	}
	
	
}
