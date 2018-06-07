<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Match</title>
<script src="<spring:message code="domain"/>/resources/js/match.js"></script>
<script src="<spring:message code="domain"/>/resources/js/common.js"></script>
</head>

<style>
.card {
	background: #fff none repeat scroll 0 0 !important;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15) !important;
	margin-bottom: 30px !important;
	position: relative !important;
}

.table>tbody>tr>th {
	border-top: 1px solid #f0f0f0;
	line-height: 1.42857;
	padding: 10px;
	vertical-align: top;
}

.table body {
	color: #5e5e5e;
	font-family: roboto;
	font-size: 13px;
	line-height: 1.42857;
}
</style>

<c:import url="/WEB-INF/views/user-header.jsp" />
<body class="toggled sw-toggled">
	<c:import url="/WEB-INF/views/admin-navbar.jsp" />
	<c:import url="/WEB-INF/views/admin//menu.jsp" />

	<section id="content">
	<div class="container">
		<div class="col-md-6">
			<div id="matchUpdate" class="row form-horizontal"
				style="display: none;">

				<div class="alert alert-danger alert-dismissible col-lg-12"
					id="errorMessage" role="alert"
					style="margin: 10px 0 !important; display: none;"></div>

				<div class="card">
					<div class="card-header bgm-gray">
						<h4>MATCH RATE</h4>
					</div>
					<div class="card-body card-padding">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								Team:</label>
							<div class="col-sm-10">
								<div class="fg-line select">
									<select id="teamSelected" class="form-control">
										<option id="teamName1"></option>
										<option id="teamName2"></option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								Rate:</label>
							<div class="col-sm-10">
								<div class="fg-line">
									<input type="text" class="form-control input-sm" id="rate"
										placeholder="Rate">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header bgm-deeppurple">
						<h4>SCORE</h4>
					</div>
					<div class="card-body card-padding row">
						<div class="form-group">
							<label id="teamLabel1" class="col-sm-4 control-label">Team
								1</label>
							<div class="col-sm-8">
								<div class="fg-line">
									<input type="text" class="form-control input-sm"
										id="scoreTeam1" placeholder="">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label id="teamLabel2" class="col-sm-4 control-label">Team
								2</label>
							<div class="col-sm-8">
								<div class="fg-line">
									<input type="text" class="form-control input-sm"
										id="scoreTeam2" placeholder="">
								</div>
							</div>
						</div>

					</div>
				</div>

				<div class="">
					<input type="email" class="form-control input-sm" id="matchId"
						placeholder="" style="display: none;">
					<button onclick="save()" class="btn bgm-blue">Save</button>
					<button onclick="cancel()" class="btn btn-danger">Cancel</button>
				</div>

			</div>
		</div>


		<div class="col-md-6">
			<div class="card">
				<div class="card-header">
					<h3>Matches</h3>
				</div>
				<div class="card-body m-t-0">
					<table id="userTable" class="table table-inner table-vmiddle">
						<tbody>
							<c:forEach var="match" items="${matches}">
								<tr>
									<td>
										<button type="button" class="btn bgm-blue"
											onclick="editMatch(${match.id})">
											<span class="md md-edit"></span>
										</button>
									</td>
									<td>
										</button> ${match.team1Name} - ${match.team2Name}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>