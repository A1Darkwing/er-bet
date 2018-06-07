package com.csc.betapp.model;

public class TeamStatistic implements Comparable<TeamStatistic> {
	private String teamName;
	private String groupName;
	private int played;
	private int wins;
	private int draws;
	private int losts;
	private int points;
	
	public TeamStatistic(String teamName, String groupName, int played,
			int wins, int draws, int losts, int points) {
		super();
		this.teamName = teamName;
		this.groupName = groupName;
		this.played = played;
		this.wins = wins;
		this.draws = draws;
		this.losts = losts;
		this.points = points;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getPlayed() {
		return played;
	}
	public void setPlayed(int played) {
		this.played = played;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getDraws() {
		return draws;
	}
	public void setDraws(int draws) {
		this.draws = draws;
	}
	public int getLosts() {
		return losts;
	}
	public void setLosts(int losts) {
		this.losts = losts;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof TeamStatistic){
			TeamStatistic x = (TeamStatistic) o;
			if (this.teamName.equals(x.teamName)) {
				return true;
			}
		}
		return false;
	}
	
	public int compareTo(TeamStatistic o) {
		int comparePoint = ((TeamStatistic) o).getPoints();
		return comparePoint - this.points;
	}
}
