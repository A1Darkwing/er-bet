<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>

<link href='https://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>

<!-- CSS -->
<link href="<spring:message code="domain"/>/resources/lib/bootstrap/css/bootstrap.min.css"	rel="stylesheet">
<link rel="stylesheet" href="<spring:message code="domain"/>/resources/lib/pnotify/pnotify.custom.min.css">
<link rel="stylesheet" href="<spring:message code="domain"/>/resources/lib/font-awesome-4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<spring:message code="domain"/>/resources/css/home/home.css">

<!-- Jquery -->
<script src="<spring:message code="domain"/>/resources/lib/jquery.min.js"></script>
<script src="<spring:message code="domain"/>/resources/lib/bootstrap/js/bootstrap.min.js"></script>

<!-- Boostrap Material -->
<link href="<spring:message code="domain"/>/resources/lib/bootstrap-material/css/material.min.css" rel="stylesheet">
<link href="<spring:message code="domain"/>/resources/lib/bootstrap-material/css/material-fullpalette.min.css" rel="stylesheet">
<link href="<spring:message code="domain"/>/resources/lib/bootstrap-material/css/roboto.min.css" rel="stylesheet">
<link href="<spring:message code="domain"/>/resources/lib/bootstrap-material/css/ripples.min.css" rel="stylesheet">
<script src="<spring:message code="domain"/>/resources/lib/bootstrap-material/js/ripples.min.js"></script>
<script src="<spring:message code="domain"/>/resources/lib/bootstrap-material/js/material.min.js"></script>
<script>
 $(function() {
	 $.material.init();
	 if (getParameterByName("error")) {
		 $('.loginError').show();
	 }
 });
 
 function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
 
</script>
<body>
	<div>
		<div style="margin-top: 5%"
			class="col-xs-8 col-xs-offset-2 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4">
			<div class="center-block" id="floating-action-button" align="center" style="font-family: 'Lobster', cursive; font-size: 50px">
			Bet Application
			</div>
			<div class="center-block" id="floating-action-button" align="center">
				<button class="btn btn-fab btn-raised btn-primary btn-material-light-blue"><i class="fa fa-twitter"></i></button>
				<button class="btn btn-fab btn-raised btn-primary btn-material-blue-900"><i class="fa fa-facebook"></i></button>
				<button class="btn btn-fab btn-raised btn-primary btn-material-red"><i class="fa fa-google"></i></button>
				
			</div>
			<div class="jumbotron panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" action="loginp" method="POST">
						<fieldset>
							<div class="form-group">
								<label class="col-lg-1 control-label loginError" style="display: none;"></label>
								<div class="alert alert-danger alert-dismissible pull-right col-lg-11 loginError"
									 role="alert"
									style="margin: 10px 0 !important; display: none;">Wrong
									Username or Password!</div>
								<label for="username" class="col-lg-2 control-label"><i
									class="fa fa-user fa-lg"></i></label>
								<div class="col-lg-10">
									<input class="form-control" placeholder="User Name"
										id="username" name="username" type="text" required autofocus>
								</div>
								<br /> <br/>
								 <label for="password" class="col-lg-2 control-label"><i
									class="fa fa-lock" style="font-size: 1.5em !important;"></i></label>
								<div class="col-lg-10">
									<input class="form-control" placeholder="Password"
										id="password" name="password" type="password" value=""
										required>
								</div>
								<label class="col-lg-2 control-label"></label>
								<div class="col-lg-10 checkbox checkbox-primary">
									<label style="color: #5e5e5e;"> <input name="rememberme" type="checkbox" />
										<span style="padding-left: 20px">Keep me signed in</span>
									</label>
								</div>
							</div>
						</fieldset>
						<div class="col-md-1"></div><div class="col-md-10 center-block">
							<button class="btn btn-raised btn-primary center-block" style="width: 100% !important;"
								type="submit">Login</button>
						</div><div class="col-md-1"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>