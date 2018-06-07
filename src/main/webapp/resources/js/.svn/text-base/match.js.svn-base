function editMatch(id) {
	$.ajax({
		url : DOMAIN + "/data/match/get/" + id,
		type : 'POST',
		data : null,
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		success : function(data) {
			loadMatch(data);
			console.log(data);
		},
		error : function(message) {
			alert('error');
			console.log(message);
		}
	})
}

function loadMatch(match) {
	$('#errorMessage').hide();
	$("#rate").val(match.matchRate);
	$("#teamName1").html(match.team1Name);
	$("#teamName2").html(match.team2Name);
	$("#teamLabel1").html(match.team1Name);
	$("#teamLabel2").html(match.team2Name);
	$("#scoreTeam1").val(match.scoreTeam1);
	$("#scoreTeam2").val(match.scoreTeam2);
	$("#matchId").val(match.id);

	$("#teamSelected option")[match.teamSelected].selected = true;
	$("#matchUpdate").show();
}

function save() {
	var rate = $("#rate").val()
	var scoreTeam1 = $("#scoreTeam1").val();
	var scoreTeam2 = $("#scoreTeam2").val();
	var id = $("#matchId").val();

	if (isEmptyString(rate)) {
		$('#errorMessage').html("Rate cannot empty!");
		$('#errorMessage').slideDown();
		return;
	}
	if (isNaN(parseFloat(rate))) {
		$('#errorMessage').html("Rate must be a number!");
		$('#errorMessage').slideDown();
		return;
	}
	if (!isEmptyString(scoreTeam1) || !isEmptyString(scoreTeam2)) {
		if (isNaN(parseInt(scoreTeam1))) {
			$('#errorMessage').html("Team 1 score must be integer Number!");
			$('#errorMessage').slideDown();
			return;
		}
		if (isNaN(parseInt(scoreTeam2))) {
			$('#errorMessage').html("Team 2 score must be integer Number!");
			$('#errorMessage').slideDown();
			return;
		}
	}

	$.ajax({
		url : DOMAIN + "/data/match/update/" + id,
		type : 'POST',
		data : JSON.stringify({
			rate : rate,
			scoreTeam1 : scoreTeam1,
			scoreTeam2 : scoreTeam2,
			teamSelected : $("#teamSelected option:selected").index()
		}),
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
			if (data == 'rate.negative') {
				$('#errorMessage').html("Rate cannot be less then 0!");
				$('#errorMessage').slideDown();
				return;
			} else if (data == 'scoreTeam1.negative') {
				$('#errorMessage').html(
						"Score of Team 1 cannot be less then 0!");
				$('#errorMessage').slideDown();
				return;
			} else if (data == 'scoreTeam2.negative') {
				$('#errorMessage').html(
						"Score of Team 2 cannot be less then 0!");
				$('#errorMessage').slideDown();
				return;
			} else if (data == 'done') {
				notify("Update successfully!", "success");
				$("#matchUpdate").hide();
			} else {
				$('#errorMessage').html(data);
				$('#errorMessage').slideDown();
			}
		},
		error : function(message) {
			alert('error');
			console.log(message);
			$("#matchUpdate").hide();
		}
	})
}

function cancel() {
	$("#matchUpdate").hide();
}
