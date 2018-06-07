<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
	<c:import url="/WEB-INF/views/user-header.jsp" />
<body>
	<c:import url="/WEB-INF/views/user-navbar.jsp" />
	<section id="main"> <aside id="sidebar" class="">
	<div class="sidebar-inner">
		<div class="si-inner">
			<div class="profile-menu">
				<a href="">
				</a>
			</div>

			<ul class="main-menu">
				<li class="active"><a
					href="#"><i
						class="md md-home"></i> Home</a></li>
				<li><a
					href="#"><i
						class="md md-view-list"></i> Betting</a></li>
				<li class="sub-menu"><a href=""><i
						class="md md-layers"></i> Report</a>
					<ul>
						<li><a
							href="#">Personal</a></li>
						<li><a
							href="#">Team</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</aside> </section>
	<section id="content">
                <div class="container">
                    <div class="block-header">
                        <h2>Dashboard</h2>
                        <div class="card-body">
                        <div class="card">
                            <div class="chart-edge">
                                <div id="curved-line-chart" class="flot-chart " style="padding: 0px; position: relative;"><canvas class="flot-base" width="1158" height="200" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1158px; height: 200px;"></canvas><canvas class="flot-overlay" width="1158" height="200" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1158px; height: 200px;"></canvas></div>
                            </div>
                            </div>
                            <div class="mini-charts">
                        <div class="row">
                            <div class="col-sm-6 col-md-3">
                                <div class="mini-charts-item bgm-cyan">
                                    <div class="clearfix">
                                        <div class="chart stats-bar"><canvas width="83" height="45" style="display: inline-block; width: 83px; height: 45px; vertical-align: top;"></canvas></div>
                                        <div class="count">
                                            <small>Website Traffics</small>
                                            <h2>987,459</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-6 col-md-3">
                                <div class="mini-charts-item bgm-lightgreen">
                                    <div class="clearfix">
                                        <div class="chart stats-bar-2"><canvas width="83" height="45" style="display: inline-block; width: 83px; height: 45px; vertical-align: top;"></canvas></div>
                                        <div class="count">
                                            <small>Website Impressions</small>
                                            <h2>356,785K</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-6 col-md-3">
                                <div class="mini-charts-item bgm-orange">
                                    <div class="clearfix">
                                        <div class="chart stats-line"><canvas width="85" height="45" style="display: inline-block; width: 85px; height: 45px; vertical-align: top;"></canvas></div>
                                        <div class="count">
                                            <small>Total Sales</small>
                                            <h2>$ 458,778</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-6 col-md-3">
                                <div class="mini-charts-item bgm-bluegray">
                                    <div class="clearfix">
                                        <div class="chart stats-line-2"><canvas width="85" height="45" style="display: inline-block; width: 85px; height: 45px; vertical-align: top;"></canvas></div>
                                        <div class="count">
                                            <small>Support Tickets</small>
                                            <h2>23,856</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        </div>
                        </div></div></section>
</body>
</html>