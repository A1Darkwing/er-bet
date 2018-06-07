<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="1000">
<link rel="stylesheet" href="<spring:message code="domain"/>/resources/css/home/table.css">
<title>Home</title>
<style type="text/css">
	@media ( max-width : 500px) {
		.flags img {
			width: 50px;
		}	
		.todayMatch, .incomingMatch {
			font-size: 90%;
		}
		.iM_Name {
			display: none;
		}
	}
	
	.mTime,.nText, .mTeam, .mRate, .mScore, .flags, .betbtn {
		text-align: center;
	}
	.nText span {
		color: red;
	}
	.table > tbody > tr:last-child > td, .table > tfoot > tr:last-child > td {
	    padding-bottom: 10px;
	}
	.table, .table th {
	text-align: center !important;
	vertical-align: middle !important;
	}
	
	.table td {
		padding-bottom: 0px !important;
		vertical-align: middle !important;
	}
	div .groupA, .mReview, .groupB {
		height: 250px !important;
	}

	.table td img {
		width: 30px;
	}
	div .todayMatch {
		margin-left: 2px;
	}
	.btn {
		text-color:white;
	}
	.check {
		margin-left: 5px;
		margin-right: 5px;
	}
	td .animated, .flags img {
		border: 1px solid black;
	}
	.mBlur {
	    background-color: #ffffff;
   		opacity: 0.6;
   		pointer-events: none;	
	}
	.imgBlur {
		background-color: #ffffff;
   		opacity: 0.5;
	}
	#incomingMatch {
		height: 49px;
		font-size: 150%;
	}
</style>
</head>
<c:import url="/WEB-INF/views/user-header.jsp" />
<body>
	<c:import url="/WEB-INF/views/user-navbar.jsp" />
	<section id="content">
	<div class="container">
		<div class="center-block card-header bgm-cyan z-depth-1" 
			style="font-family: 'Lobster', cursive; font-size: 30px;">
			<h2>Incoming Matches</h2>
		</div>
			<c:choose>
				<c:when test="${!empty todayMatches}">
					<c:forEach items="${todayMatches}" var="match">
						<p id="mTime_${match.id}" hidden>${match.matchTime}</p>
						<div class="row todayMatch table table-striped table-bordered table-condensed bs-item z-depth-1" id="todayMatch_${match.matchTime}">
							<div class="col-sm-2 mTime"  style="font-family: 'Lobster', cursive; font-size: 200%">
								<fmt:formatDate type="both" timeStyle="short" value="${match.matchTime}"  pattern="dd/MM/yyyy HH:mm"/>
								<p>
							</div>
							<div class="col-sm-5 mTeam" >
								<div class="row"  style="font-family: 'Lobster', cursive; font-size: 200%">
									<c:out value="${match.team1Name}" />
									-
									<c:out value="${match.team2Name}" />
								</div>
								<div class="row mRate" style="font-family: 'Lobster', cursive; ">
									Bet Rate:
									<c:out value="${match.team1Rate}" />
									-
									<c:out value="${match.team2Rate}" />
								</div>
								<div class="row mScore" style="font-family: 'Lobster', cursive;">Score:</div>
							</div>
							<div class="col-xs-12 col-sm-5">
								<div class="row checkbox checkbox-primary flags">
									
									<img alt="${match.team1Name}" src="resources/img/flags/${match.team1Flag}.png">
									<label style="color: #5e5e5e;">
										<input type="checkbox" id ="${match.id}_1" name="choosen_${match.id}" value="${match.id}_1_${match.team1Name}">
									</label>
									<label style="color: #5e5e5e;">
										<input type="checkbox" id="${match.id}_2" name="choosen_${match.id}" value="${match.id}_2_${match.team2Name}">
									</label>
									<img alt="${match.team2Name}" src="resources/img/flags/${match.team2Flag}.png">
		
								</div>
								<div class="row mCount"></div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="row">
						<div class="col-sm-12" style="font-family: 'Lobster', cursive; text-align:center; font-size: 200%">
							There is no match today. Please come back tomorrow. Have a nice day!
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		<br />
		<div class="center-block card-header bgm-cyan z-depth-1" 
			style="font-family: 'Lobster', cursive; text-align:center; font-size: 30px; ">
			<h2>All Remaining Matches</h2>
		</div>
		<br/>
			<c:choose>
				<c:when test="${!empty incomingMatches}">
					<c:forEach items="${incomingMatches}" var="match">
						<table id = "incomingMatch" class="table table-striped table-bordered table-condensed bs-item z-depth-1" style="margin-top: 5px;">
							<td style="width:10%"><fmt:formatDate value="${match.matchTime}" pattern="dd/MM/yyyy"/></td>
							<td style="width:10%"><fmt:formatDate value="${match.matchTime}" pattern="HH:mm"/></td>
							<td style="width:10%; tex-align: right !important;" class="iM_Name" id="iM_${match.id}_1"><c:out value="${match.team1Name}" /></td>
							<td style="width:5%"><a id ="${match.id}_${match.team1Name}_1" href="#" title="${match.team1Name}"><img id="img_${match.id}_1"class="animated" alt="${match.team1Name}" src="resources/img/flags/${match.team1Flag}.png"></a></td>
							<td style="width:2%;">vs</td>
							<td style="width:5%"><a id ="${match.id}_${match.team2Name}_2" href="#" title="${match.team2Name}"><img id="img_${match.id}_2"class="animated" alt="${match.team2Name}" src="resources/img/flags/${match.team2Flag}.png"></a></td>
							<td style="width:10%; tex-align: left !important;" class="iM_Name" id="iM_${match.id}_2"><c:out value="${match.team2Name}" /></td>
						</table>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div style="font-family: 'Lobster', cursive; text-align:center; font-size: 200%">
						There are no incoming matches. Please come back another time. Have a nice day!
					</div>
				</c:otherwise>
			</c:choose>
		<br />

		<div class="center-block card-header bgm-cyan z-depth-1"
			style="font-family: 'Lobster', cursive; font-size: 200%;">
			<h2>Matches Result</h2>
		</div>
		<br />
			<div class="card-body card-padding">
				<div style="overflow: hidden;">
					<c:if test="${!empty recentMatches}">
						<c:forEach items="${recentMatches}" var="match">
						<table class="table table-striped table-bordered table-condensed bs-item z-depth-1" style="margin-top: 5px;">
							<td style="width:10%"><fmt:formatDate value="${match.matchTime}" pattern="dd/MM/yyyy"/></td>
							<td style="width:10%; tex-align: right !important;" class="iM_Name" id="iM_${match.id}_1"><c:out value="${match.team1Name}" /></td>
							<td style="width:5%"><img id="img_${match.id}_1"class="animated" alt="${match.team1Name}" src="resources/img/flags/${match.team1Flag}.png"></td>
							<td style="width:2%; font-weight: bold;">${match.team1Score}</td>
							<td style="width:2%;">-</td>
							<td style="width:2%; font-weight: bold;">${match.team2Score}</td>
							<td style="width:5%"><img id="img_${match.id}_2"class="animated" alt="${match.team2Name}" src="resources/img/flags/${match.team2Flag}.png"></td>
							<td style="width:10%; tex-align: left !important;" class="iM_Name" id="iM_${match.id}_2"><c:out value="${match.team2Name}" /></td>
						</table>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<br />
		<div class="center-block card-header bgm-cyan z-depth-1" style="font-family: 'Lobster', cursive; font-size: 200%;">
			<h2>Groups</h2>
		</div>
			<div class="row">
					<c:forEach var="groupInfo" items="${groupInfoDetail}">
				<div class="col-sm-12 col-lg-4">
					<div class="card card-child groupA">
						<div class="card-header bgm-gray">
							<h2>${groupInfo.key}</h2>
						</div>
						<div class="card-body">
								<table 
									class="table table-striped table-bordered table-condensed bs-item ">
									<thead>
										<tr>
											<th style="width: 10%" title="Rank">#</th>
											<th style="width: 40%" title="Team Name">Team</th>
											<th style="width: 10%" title="Playeds">P</th>
											<th style="width: 10%" title="Wins">W</th>
											<th style="width: 10%" title="Draws">D</th>
											<th style="width: 10%" title="Losts">L</th>
											<th style="width: 10%" title="Total Points">Pts</th>
										</tr>
									</thead>
									<c:set var="count" value="0" scope="page" />
									<c:forEach var="teamInfo" items="${groupInfo.value}">
										<tbody>
												<c:set var="count" value="${count + 1}" scope="page"/>
												<td><c:out value="${count}" /></td>
												<td><c:out value="${teamInfo.teamName}" /></td>
												<td><c:out value="${teamInfo.played}" /></td>
												<td><c:out value="${teamInfo.wins}" /></td>
												<td><c:out value="${teamInfo.draws}" /></td>
												<td><c:out value="${teamInfo.losts}" /></td>
												<td><c:out value="${teamInfo.points}" /></td>
										</tbody>
									</c:forEach>
								</table>
						</div>
					</div>
				</div>
					</c:forEach>
			</div>
	</div>
	</section>
	<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h4 class="modal-title" id="myModalLabel">Time Up</h4>
            </div>
            <div class="modal-body">
                <h3>Sorry. Betting time is up. Please come back later.</h3>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-raised btn-primary waves-effect waves-button waves-float" data-dismiss="modal">OK</button>
        </div>
    </div>
  </div>
</div>
</body>
<script src="<spring:message code="domain"/>/resources/script/home.js"></script>
</html>