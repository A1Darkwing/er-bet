<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Type</title>
<script src="<spring:message code="domain"/>/resources/js/type.js"></script>
<script src="<spring:message code="domain"/>/resources/js/common.js"></script>
</head>
<c:import url="/WEB-INF/views/user-header.jsp" />
<body class="toggled sw-toggled">
	<c:import url="/WEB-INF/views/admin-navbar.jsp" />
	<c:import url="/WEB-INF/views/admin//menu.jsp" />

	<section id="content">
	<div class="container">
		<div class="col-md-6">
			<div id="typeUpdate" class="row form-horizontal"
				style="display: none;">

				<div class="alert alert-danger alert-dismissible col-lg-12"
					id="errorMessage" role="alert"
					style="margin: 10px 0 !important; display: none;"></div>

				<div class="card">
					<div class="card-header bgm-green">
						<h4>STAKE</h4>
					</div>
					<div class="card-body card-padding">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								Stake:</label>
							<div class="col-sm-10">
								<div class="fg-line">
									<input type="text" class="form-control input-sm" id="stake"
										placeholder="Rate">
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="">
					<input type="text" class="form-control input-sm" id="typeId"
						placeholder="" style="display: none;">
					<button onclick="save()" class="btn btn-primary">Save</button>
					<button onclick="cancel()" class="btn btn-danger">Cancel</button>
				</div>

			</div>
		</div>


		<div class="col-md-6">
			<table id="userTable"
				class="table table-striped table-vmiddle bootgrid-table card ">
				<thead>
					<tr>
						<th>Types</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="type" items="${types}">
						<tr>
							<td>
								<button type="button" class="btn btn-icon command-edit"
									onclick="editType(${type.id})">
									<span class="md md-edit"></span>
								</button> ${type.name}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</section>
</body>
</html>