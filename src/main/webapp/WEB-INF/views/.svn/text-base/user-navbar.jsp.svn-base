<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<header id="header" class="">
	<ul class="header-inner">
		<li id="home" class="">
			<ul class="top-menu">
				<li id="home"><a class="tm-notification" href="home"></a></li>
			</ul>
		</li>

		<li class="logo hidden-xs"><a href="home">Bet
				Application</a></li>

		<li class="pull-right">
			<ul class="top-menu">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li id="admin"><a class="tm-admin" href="admin-user"></a></li>
				</sec:authorize>
				<li class="dropdown"><a data-toggle="dropdown"
					class="tm-message" href=""></a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li><a href="report-user"><i class="md md-now-widgets"></i>
								User</a></li>
						<li><a href="report-team"><i class="md md-view-list"></i>
								Match</a></li>
					</ul></li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="tm-settings" href=""></a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li><a onclick="showChangePassword()" href="#"><i
								class="md md-settings"></i> Change Password</a></li>
						<li><a href="logout"><i class="md md-history"></i> Log
								out</a></li>
					</ul></li>
			</ul>
		</li>
	</ul>
</header>
<button style="display: none;" id="hiddenclick" class="btn btn-primary"
	data-toggle="modal" data-target="#complete-dialog">Open dialog</button>
<div id="complete-dialog" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title">Change Password</h4>
			</div>
			<div class="modal-body">
				<form id="changePassForm" class="form-horizontal"
					 method="POST">
					<fieldset>
						<div class="form-group">
							<div class="alert alert-danger alert-dismissible col-lg-12"
								id="changePassError" role="alert"
								style="margin: 10px 0 !important; display: none;"></div>
							<label for="oldPassword" class="col-lg-4 control-label">Old
								Password</label>
							<div class="col-lg-8">
								<input class="form-control" placeholder="Old Password"
									id="oldPassword" name="oldPassword" type="password" required
									autofocus>
							</div>
							<br /> <br /> <label for="newPassword"
								class="col-lg-4 control-label">New Password</label>
							<div class="col-lg-8">
								<input class="form-control" placeholder="New Password"
									id="newPassword" name="newPassword" type="password" required>
							</div>
							<br /> <br /> <label for="confirmPassword"
								class="col-lg-4 control-label">Confirm Password</label>
							<div class="col-lg-8">
								<input class="form-control" placeholder="Confirm Password"
									id="confirmPassword" name="confirmPassword" type="password"
									required>
							</div>
						</div>
					</fieldset>
					<div class="modal-footer">
						<button class="btn btn-raised btn-primary" type="button"
							onclick="saveChangePass()">Save</button>
						<button id="cancelChangePass"
							class="btn btn-primary btn-material-grey-700"
							data-dismiss="modal">Cancel</button>
					</div>
				</form>
			</div>

		</div>
	</div>
</div>