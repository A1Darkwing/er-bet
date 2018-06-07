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
<script>
function addCommas(str) {
    var parts = (str + "").split("."),
        main = parts[0],
        len = main.length,
        output = "",
        i = len - 1;

    while(i >= 0) {
        output = main.charAt(i) + output;
        if ((len - i) % 3 === 0 && i > 0) {
            output = "," + output;
        }
        --i;
    }
    // put decimal part back
    if (parts.length > 1) {
        output += "." + parts[1];
    }
    return output;
}
$(document).ready(function(){
	var total = 0;
	$('.money').each(function() {
		total += parseInt($(this).html().replace(' VND','').replace(',', ''));
	});
	$('#totalMoney').html($('#totalMoney').html().replace('@@@', addCommas(total)));
});
</script>
<body>
	<c:import url="/WEB-INF/views/user-navbar.jsp" />
	<section id="content">
	<div class="container">
		<table class="table table-striped table-bordered bs-item z-depth-1">
			<thead>
				<tr>
					<th style="width: 20%">User Name</th>
					<th style="width: 15%">Win</th>
					<th style="width: 15%">Draw</th>
					<th style="width: 15%">Lose</th>
					<th style="width: 15%">Lost Money</th>
					<th style="width: 15%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${records}">
					<tr>
						<td><c:out value="${record.username}" /></td>
						<td><c:out value="${record.countWin}" /></td>
						<td><c:out value="${record.countDraw}" /></td>
						<td><c:out value="${record.countLose}" /></td>
						<td class="money"><fmt:formatNumber type="number"
								value="${record.lostMoney}" /> VND</td>
						<td><button
								class="btn bgm-lime waves-effect waves-button waves-float"
								id="betBnt" onClick="viewReportUserDetail(${record.id})">Detail</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="totalMoney" class="bgm-gray pull-right z-depth-1"
			style="color: white; font-family: 'Lobster', cursive; font-size: 20px; padding: 10px; width: initial; float: right;">
			Total: @@@ VND</div>
	</div>
	<div id="userdetail-dialog" class="modal fade" tabindex="-1">
		<div class="modal-dialog" style="width: 70% !important;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<div class="modal-title"
						style="font-family: 'Lobster', cursive; font-size: 20px">User
						Report Detail</div>
				</div>
				<br />
				<div class="modal-body" style="overflow: auto">
					<table id="tableHeader"
						class="table table-striped table-bordered table-condensed bs-item z-depth-1">
						<thead>
							<tr>
								<th style="width: 10%">Date</th>
								<th style="width: 10%">Time</th>
								<th style="width: 5%"></th>
								<th style="width: 10%"></th>
								<th style="width: 10%">Match</th>
								<th style="width: 10%"></th>
								<th style="width: 5%"></th>
								<th style="width: 10%">Bet Team</th>
								<th style="width: 10%">Bet Time</th>
								<th style="width: 10%">Result</th>
								<th style="width: 10%">Lost Money</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button id="cancelChangePass"
						style="margin-right: 15px !important; margin-bottom: 15px !important;"
						class="btn btn-primary btn-material-grey-700" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>