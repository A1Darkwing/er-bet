package com.csc.betapp.model;

public class UserRecord implements Comparable<UserRecord> {

	private int id;
	private String username;
	private int countWin;
	private int countDraw;
	private int countLose;
	private int lostMoney;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCountWin() {
		return countWin;
	}

	public void setCountWin(int countWin) {
		this.countWin = countWin;
	}

	public int getCountDraw() {
		return countDraw;
	}

	public void setCountDraw(int countDraw) {
		this.countDraw = countDraw;
	}

	public int getCountLose() {
		return countLose;
	}

	public void setCountLose(int countLose) {
		this.countLose = countLose;
	}

	public int getLostMoney() {
		return lostMoney;
	}

	public void setLostMoney(int lostMoney) {
		this.lostMoney = lostMoney;
	}

	public int compareTo(UserRecord arg0) {
		if (this.getLostMoney()<arg0.getLostMoney()){
            return -1;
        }else if (this.getLostMoney()<arg0.getLostMoney()){
            return 0;
        } else {
        	return 1;
        }
	}

}
