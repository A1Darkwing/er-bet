function editType(id) {
	$.ajax({
		url : DOMAIN + "/data/type/get/" + id,
		type : 'POST',
		data : null,
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		success : function(data) {
			loadType(data);
			console.log(data);
		},
		error : function(message) {
			alert('error');
			console.log(message);
		}
	})
}

function loadType(type) {
	$('#errorMessage').hide();
	$("#stake").val(type.stake);
	$("#typeId").val(type.id);
	$("#typeUpdate").show();
}

function save() {
	var stake = $("#stake").val();
	var id = $("#typeId").val();

	 if (isEmptyString(stake)) {
		$('#errorMessage').html("Stake cannot empty!");
		$('#errorMessage').slideDown();
		return;
	}
	if (!isInteger(parseFloat(stake))) {
		$('#errorMessage').html("Stake must be a number!");
		$('#errorMessage').slideDown();
		return;
	}

	$.ajax({
		url : DOMAIN + "/data/type/update/" + id,
		type : 'POST',
		data : JSON.stringify({
			stake : stake
		}),
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
			if (data == 'type.negative') {
				$('#errorMessage').html("Stake cannot be less then 0!");
				$('#errorMessage').slideDown();
				return;
			} else if (data == 'done') {
				notify("Update successfully!", "success");
				$("#typeUpdate").hide();
			} else {
				$('#errorMessage').html(data);
				$('#errorMessage').slideDown();
			}

		},
		error : function(message) {
			alert('error');
			console.log(message);
			$("#typeUpdate").hide();
		}
	})
}

function cancel() {
	$("#typeUpdate").hide();
}