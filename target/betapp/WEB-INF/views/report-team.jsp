<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<c:import url="/WEB-INF/views/user-header.jsp" />
<style>

@media ( max-width : 500px) {
	#tableHeader {
		display: none;
	}
}

.table {
	min-width: 650px !important;
}

.table, .table th {
	text-align: center !important;
	vertical-align: middle !important;
}

.table td {
	padding-bottom: 0px !important;
	vertical-align: middle !important;
}

.table td img {
	width: 25px;
}

.table .btn {
	margin: 0px 0px 10px 0px !important;
	padding: 5px 30px !important;
}
</style>
<body>
	<c:import url="/WEB-INF/views/user-navbar.jsp" />
	<section id="content">
	<div class="container">
		<c:forEach var="record" items="${records}">
			<div class="center-block" id="floating-action-button"
				style="font-family: 'Lobster', cursive; font-size: 30px">
				${record.name}</div>
			<br />
			<!-- <table id="tableHeader"
				class="table table-striped table-bordered table-condensed bs-item z-depth-1">
				<thead>
					<tr>
						<th style="width: 10%">Date</th>
						<th style="width: 10%">Time</th>
						<th style="width: 5%"></th>
						<th style="width: 15%"></th>
						<th style="width: 5%">Match</th>
						<th style="width: 15%"></th>
						<th style="width: 5%"></th>
						<th style="width: 10%">Bet Rate</th>
						<th style="width: 10%">Bet</th>
						<th style="width: 15%"></th>
					</tr>
				</thead>
			</table> -->
			<c:forEach var="match" items="${record.lines}">
				<table
					class="table table-striped table-bordered table-condensed bs-item z-depth-1">
					<thead>
						<tr>
							<th style="width: 10%">Date</th>
							<th style="width: 10%">Time</th>
							<th style="width: 5%"></th>
							<th style="width: 15%"></th>
							<th style="width: 5%">Match</th>
							<th style="width: 15%"></th>
							<th style="width: 5%"></th>
							<th style="width: 10%">Bet Rate</th>
							<th style="width: 10%">Bet</th>
							<th style="width: 15%"></th>
						</tr>
					</thead>
					<tbody>
						<td style="width: 10%"><fmt:formatDate pattern="dd/MM/yyyy"
								value="${match.matchTime}" /></td>
						<td style="width: 10%"><fmt:formatDate pattern="hh:mm"
								value="${match.matchTime}" /></td>
						<td style="width: 5%"><img alt="${match.team1Name}"
							src="resources/img/flags/${match.team1Flag}.png"></td>
						<td style="width: 15%; text-align: right !important;"><c:out
								value="${match.team1Name}" /></td>
						<td style="width: 5%"><c:out value="${match.team1Score}" />:<c:out
								value="${match.team2Score}" /></td>
						<td style="width: 15%; text-align: left !important;"><c:out
								value="${match.team2Name}" /></td>
						<td style="width: 5%"><img alt="${match.team2Name}"
							src="resources/img/flags/${match.team2Flag}.png"></td>
						<td style="width: 10%"><c:out value="${match.team1Rate}" />
							: <c:out value="${match.team2Rate}" /></td>
						<td style="width: 10%"><c:out value="${match.team1Bet}" /> :
							<c:out value="${match.team2Bet}" /></td>
						<td style="width: 15%"><button
								class="btn bgm-lime waves-effect waves-button waves-float"
								id="betBnt"
								onClick="viewReportTeamDetail(${match.id}, '${match.team1Name}', '${match.team2Name}')">Detail</button></td>
					</tbody>
				</table>
			</c:forEach>
		</c:forEach>
	</div>
	<div id="userbet-dialog" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<div class="modal-title"
						style="font-family: 'Lobster', cursive; font-size: 20px">Betting
						Users</div>
				</div>
				<br />
				<div class="modal-body"></div>

				<div class="modal-footer">
					<button id="cancelChangePass"
						style="margin-right: 15px !important; margin-bottom: 15px !important;"
						class="btn btn-primary btn-material-grey-700" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</section>
</body>
</html>