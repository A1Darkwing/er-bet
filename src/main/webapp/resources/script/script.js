$(document).ready(function(){
    //Welcome Message (not for login page)
    if (!$('.login-content')[0]) {
        //notify('Welcome back', 'inverse');
    }
    $.material.init();
});

function showChangePassword() {
	 $('#hiddenclick').click();
}

function saveChangePass() {
	var oldPass = $("#oldPassword").val();
	var newPass = $("#newPassword").val();
	var confirmPass = $("#confirmPassword").val();

	if (isEmptyString(oldPass)) {
		$('#changePassError').html("Old Password cannot empty!");
		$('#changePassError').slideDown();
	} else if (isEmptyString(newPass)) {
		$('#changePassError').html("New Password cannot empty!");
		$('#changePassError').slideDown();
	} else if (isEmptyString(confirmPass)) {
		$('#changePassError').html("Confirm Password cannot empty!");
		$('#changePassError').slideDown();
	} else if (newPass != confirmPass) {
		$('#changePassError').html("Confirm Password does not match!");
		$('#changePassError').slideDown();
	} else {
		$('#changePassError').hide();
		$.post( DOMAIN + "/p/changepass", $( "#changePassForm" ).serialize())
		  .done(function( data ) {
		    if (data == 'oldPassword.empty') {
		    	$('#changePassError').html("Old Password cannot empty!");
				$('#changePassError').slideDown();
		    } else if (data == 'password.empty') {
		    	$('#changePassError').html("New Password cannot empty!");
				$('#changePassError').slideDown();
		    } else if (data == 'oldPassword.false') {
		    	$('#changePassError').html("Old Password does not correct!");
				$('#changePassError').slideDown();
		    } else if (data =='done') {
		    	notify("Password change successfully!", "success");
		    	$('#cancelChangePass').click();
		    } else {
		    	alert(data);
		    	notify("Unknown Error!", "danger");
		    	$('#cancelChangePass').click();
		    }
		  });
	}
}

function viewReportTeamDetail(matchId, team1, team2) {
	var str = "User bet for <b>@Team1:</b> @@ <br/><br/>User bet for <b>@Team2</b>: !! <br/><br/> User <b>did not bet:</b> ##";
	$.post( DOMAIN + "/p/user-bet", {'matchId' : matchId})
	  .done(function( data ) {
		  str = str.replace("@Team1",team1);
		  str = str.replace("@Team2",team2);
		  data0 ='';
		  for (var i = 0; i <data[0].length; i++) {
			  data0 += data[0][i];
			  if (i + 1 != data[0].length) {
				  data0 += ", ";
			  }
		  }
		  str = str.replace("@@", data0);
		  data1 ='';
		  for (var i = 0; i <data[1].length; i++) {
			  data1 += data[1][i];
			  if (i + 1 != data[1].length) {
				  data1 += ", ";
			  }
		  }
		  str = str.replace("!!",data1);
		  data2 ='';
		  for (var i = 0; i <data[2].length; i++) {
			  data2 += data[2][i];
			  if (i + 1 != data[2].length) {
				  data2 += ", ";
			  }
		  }
		  str = str.replace("##",data2);
		  $('#userbet-dialog .modal-body').html(str);
		  $('#userbet-dialog').modal();
	  });
}

function viewReportUserDetail(userId) {
	$.post( DOMAIN + "/p/user-record", {'userId' : userId})
	  .done(function( data ) {
		  console.log(data);
		  var str="";
		  $(data).each(function(key, value) {
			  //alert(value.matchDate);
			  var date = $.format.date(new Date(value.matchDate), "dd/MM/yyyy");
			  var time = $.format.date(new Date(value.matchDate), "HH:mm");
			  var trClass = "success";
			  var selection = "";
			  var result = "Win";
			  if (value.resultType =="D") {
				  trClass = "warning";
				  result = "Draw";
			  } else if (value.resultType == "L") {
				  trClass = "danger";
				  result = "Lose";
			  }
			  if (isEmptyString(value.teamFlag) || isEmptyString(value.teamName)) {
				  selection = "<td></td>";
			  } else {
				  selection = "<td><img src=\"resources/img/flags/"+ value.teamFlag +".png\"><br/>"+ value.teamName +"</td>";
			  }
			  str += "<tr class=\"" + trClass + "\">" +
						"<td>"+ date +"</td>" +
						"<td>"+ time +"</td>" +
						"<td><img src=\"resources/img/flags/"+ value.team1Flag +".png\"></td>" +
						"<td style=\"text-align: right !important;\">"+ value.team1Name +"</td>" +
						"<td>"+ value.team1Score +" : "+ value.team2Score +"</td>" +
						"<td style=\"text-align: left !important;\">"+ value.team2Name +"</td>" +
						"<td><img src=\"resources/img/flags/"+ value.team2Flag +".png\"></td>" +
						selection +
						"<td>"+ $.format.date(new Date(value.betTime), "dd/MM/yyyy hh:mm:ss") +"</td>" +
						"<td>"+ result +"</td>" +
						"<td>"+ value.losingMoney +"</td>" +
					"</tr>";
		  });
		  $('#userdetail-dialog .modal-body table tbody').html(str);
		  $('#userdetail-dialog').modal();
	  });
}

function notify(message, type){
    $.growl({
        message: message
    },{
        type: type,
        allow_dismiss: false,
        label: 'Cancel',
        className: 'btn-xs btn-inverse',
        placement: {
            from: 'top',
            align: 'right'
        },
        delay: 2500,
        animate: {
                enter: 'animated bounceIn',
                exit: 'animated bounceOut'
        },
        offset: {
            x: 20,
            y: 85
        }
    });
};

function isEmptyString(prop) {
	if (prop == null || prop == "") {
		return true;
	} else {
		return false;
	}
}