$(document).ready(function() {
	$('#matchTime').datetimepicker();
});

function save() {
	var team1 = $("#team1Selected option:selected").attr("id");
	var team2 = $("#team2Selected option:selected").attr("id");
	var type = $("#typeSelected option:selected").attr("id");
	var datetime = $("#matchTime").val();

	if (team1 == team2) {
		$('#errorMessage').html("Team 2 must not be the same with Team 1");
		$('#errorMessage').slideDown();
		return;
	}

	$.ajax({
		url : DOMAIN + "/data/match/create/",
		type : 'POST',
		data : JSON.stringify({
			team1 : team1,
			team2 : team2,
			type : type,
			datetime : datetime
		}),
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
			if (data == 'scoreTeam1.negative') {
				$('#errorMessage').html(
						"Score of Team 1 cannot be less then 0!");
				$('#errorMessage').slideDown();
				return;
			} else if (data == "done") {
				notify("Match created successfully!", "success")
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
