$(document).ready(function(){
	var todayMatches = $(".todayMatch");
	for(var i = 0; i < todayMatches.length; i++){
		var todayMatchId =  $(todayMatches[i]).attr("id");
		var temp = todayMatchId.split("_");
		var mTime = Date.parse(temp[1].split(".")[0]);
		var current = new Date();
		if(mTime <= current){
			$(todayMatches[i]).addClass("mBlur");
		}
	}
	$('a').click(function(){
		$link = $(this);
		var linkId = $link.attr("id");
		var data = linkId.split("_");
		comingBet(data[0], data[1], data[2]); 
		return false; 
	});
	//First time loading
	$.ajax({
		url: DOMAIN + "/getCurrentBets",
		type:'POST',
		data: null,
		success: function(data){
			if(data.length > 0){
				for(var i =0; i < data.length; i++){
					var bi = data[i];
					var $iM = $("" +'#iM_'+ bi.matchId + '_' + bi.selection + "");
					if( $iM.length != 0){
						var another = '';
						if(bi.selection == '1'){
							another = '2';
						} else {
							another = '1';
						}
						$("" +'#iM_'+ bi.matchId + '_' + bi.selection + "").css("color", "red");
						$("" +'#img_'+ bi.matchId + '_' + another + "").addClass("imgBlur");
					} else {
						$("input:checkbox[id='" + bi.matchId + '_' + bi.selection + "']").prop('checked', true);
						$("input:checkbox[id='" + bi.matchId + '_' + bi.selection + "']").prop('disabled', true);
					}
					
				}
			}
		},
		error : function(message) {
			alert('error');
			console.log(message);
		}
	});
	
	$("input:checkbox").on('click', function() {
		  // in the handler, 'this' refers to the box clicked on
		  var $box = $(this);
		  var matchId = $box.attr("name").split("_")[1];
		  if ($box.is(":checked")) {
		    betAction(matchId, $box);
		   
		  } else {
		    $box.prop("checked", false);
		  }
		});
});
function betAction(matchId, $checkedBox){
	var temp = $("" +'#mTime_'+ matchId + "").text().split(".");
	var mTime = Date.parse(temp[0]);
	var current = new Date();
	if(mTime <= current){
		$("#basicModal").modal('show');
		$("input:checkbox[name='" +'choosen_'+ matchId + "']").prop('disabled', true);
		$("input:checkbox[name='" +'choosen_'+ matchId + "']").prop('checked', false);
	} else {
		var choosenTeam =$checkedBox.val();
		var datas = choosenTeam.split("_");
		var json = {"matchId": datas[0], "team": datas[1]};
		$.ajax({
			url: DOMAIN + "/betting",
			type:'POST',
			data: json,
			success: function(data){
				if(data == "Input Error"){
					notify("Please choose your team.", "error");
				} else if (data == "Invalid Date"){
					$("#basicModal").modal('show');
					$("input:checkbox[name='" +'choosen_'+ matchId + "']").prop('disabled', true);
					$("input:checkbox[name='" +'choosen_'+ matchId + "']").prop('checked', false);
				} else {
					$("input:checkbox[name='" +'choosen_'+ matchId + "']").prop('disabled', false);
					$("input:checkbox[name='" +'choosen_'+ matchId + "']").prop('checked', false);
					$checkedBox.prop('disabled', true);
					$checkedBox.prop('checked', true);
					var text = "You have choosen team "+ datas[2]  + " for this bet. Good luck !";
					notify(text,"success");
				}
			},
			error : function(message) {
				alert('error');
				console.log(message);
			}
		});
	}
}
function comingBet(matchId, teamName, teamNum){
	var json = {"matchId": matchId, "team": teamNum};
	$.ajax({
		url: DOMAIN + "/betting",
		type:'POST',
		data: json,
		success: function(data){
				var another = '';
				if(teamNum == '1'){
					another= '2';
				} else {
					another = '1';
				}
				$("" +'#img_'+ matchId + '_' + teamNum + "").removeClass("imgBlur");
				$(".animated").css("pointer-events", "yes");
				$("" +'#iM_'+ matchId + '_' + another + "").css("color", "black");
				$("" +'#img_'+ matchId + '_' + another + "").addClass("imgBlur");
				$('.table img').removeClass("bounce");
				$("" +'#img_'+ matchId + '_' + teamNum + "").addClass("bounce");
				$("" +'#img_'+ matchId + '_' + teamNum + "").css("pointer-events", "none");
				$("" +'#iM_'+ matchId + '_' + teamNum + "").css("color", "red");
				var text = "You have choosen team "+ teamName  + " for this bet. Good luck !";
				notify(text,"success");
		},
		error : function(message) {
			alert('error');
			console.log(message);
		}
	});
}