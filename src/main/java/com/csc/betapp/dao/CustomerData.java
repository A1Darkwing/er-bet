package com.csc.betapp.dao;

public class CustomerData {

	public static final String MATCH_TYPES = "INSERT INTO MatchTypes ( Stake, CustomerId, TypeId) VALUES "
			+ "(10000, @cust@, 1),"
			+ "(20000, @cust@, 2),"
			+ "(50000, @cust@, 3)," + "(50000, @cust@, 4);";

	public static final String MATCHES = "INSERT INTO Matchs (Team1Id, Team2Id, MatchRate, MatchTime, ScoreTeam1, ScoreTeam2, CustomerId, MatchTypeId) VALUES"
			+ "(2,5,null,'2015-05-29 15:00:00', null, null, @cust@, @mt@),"
			+ "(4,1,null,'2015-05-29 19:30:00', null, null, @cust@, @mt@),"
			+ "(3,6,null,'2015-05-30 19:30:00', null, null, @cust@, @mt@),"
			+ "(5,4,null,'2015-05-30 19:30:00', null, null, @cust@, @mt@),"
			+ "(11,7,null,'2015-06-01 19:30:00', null, null, @cust@, @mt@),	"
			+ "(1,6,null,'2015-06-01 19:30:00', null, null, @cust@, @mt@),	"
			+ "(2,3,null,'2015-06-02 19:30:00', null, null, @cust@, @mt@),	"
			+ "(9,8,null,'2015-06-02 19:30:00', null, null, @cust@, @mt@),	"
			+ "(6,5,null,'2015-06-03 19:30:00', null, null, @cust@, @mt@),	"
			+ "(10,11,null,'2015-06-03 19:30:00', null, null, @cust@, @mt@),	"
			+ "(3,1,null,'2015-06-04 15:00:00', null, null, @cust@, @mt@),	"
			+ "(2,4,null,'2015-06-04 19:30:00', null, null, @cust@, @mt@),	"
			+ "(7,9,null,'2015-06-04 19:30:00', null, null, @cust@, @mt@),	"
			+ "(8,10,null,'2015-06-06 19:30:00', null, null, @cust@, @mt@),	"
			+ "(5,1,null,'2015-06-06 19:30:00', null, null, @cust@, @mt@),	"
			+ "(2,6,null,'2015-06-07 19:30:00', null, null, @cust@, @mt@),	"
			+ "(11,9,null,'2015-06-07 19:30:00', null, null, @cust@, @mt@),	"
			+ "(5,3,null,'2015-06-08 19:30:00', null, null, @cust@, @mt@),	"
			+ "(7,10,null,'2015-06-08 19:30:00', null, null, @cust@, @mt@),	"
			+ "(4,6 ,null,'2015-06-09 19:30:00', null, null, @cust@, @mt@),	"
			+ "(8,11,null,'2015-06-09 19:30:00', null, null, @cust@, @mt@),	"
			+ "(2,1,null,'2015-06-10 19:30:00', null, null, @cust@, @mt@),	"
			+ "(9,10,null,'2015-06-10 19:30:00', null, null, @cust@, @mt@),	"
			+ "(4,3,null,'2015-06-11 19:30:00', null, null, @cust@, @mt@),	"
			+ "(7,8,null,'2015-06-11 19:30:00', null, null, @cust@, @mt@);";
}
