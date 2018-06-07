function addCustomer() {
	var newCustomer = $("#customerName").val();
	var userName = $("#username").val();
	var nop = $("#nop").val();
	if (!validateUser(userName)) {
		return;
	}
	if (isNaN(nop)) {
		$('#errorMessage').html("Invalid Number of Players!");
		$('#errorMessage').slideDown();
		return;
	}
	$
			.ajax({
				url : DOMAIN + "/c/customer/add",
				type : 'POST',
				data : JSON.stringify({
					customer : newCustomer,
					user : userName,
					nop : nop
				}),
				contentType : 'application/json; charset=utf-8',
				success : function(data) {
					if (data[0] == "customer.emptyName") {
						$('#errorMessage').html(
								"Customer name must not be empty!");
						$('#errorMessage').slideDown();
						return false;
					} else if (data[0] == "user.duplicate") {
						$('#errorMessage').html("User name existed!");
						$('#errorMessage').slideDown();
						return false;
					}else if (data[0] == "user.badword") {
						$('#errorMessage').html("User name must be word and number!");
						$('#errorMessage').slideDown();
						return false;
					} else if (data[0] == 'done') {
						var customerTable = $("#customerTable");
						$(customerTable)
								.append(
										"<tr id='customer-"
												+ data[1]
												+ "'><td>"
												+ newCustomer
												+ "</td>"
												+ "<td><button type='button'"
												+ " class='btn btn-danger'onclick='del("
												+ data[1]
												+ ")'>"
												+ "<span class='md md-delete'></span></button></td></tr>");
						$("#customerName").val("");
						$("#username").val("");
						$("#nop").val("");
						notify("Update successfully!", "success");
					} else {
						$('#errorMessage').html(data[0]);
						$('#errorMessage').slideDown();
						return false;
					}

				},
				error : function() {
					alert('error');
				}
			})
}

function validateUser(user) {
	if (isEmptyString(user)) {
		$('#errorMessage').html("User name cannot empty!");
		$('#errorMessage').slideDown();
		return false;
	}
	var pattern = /^[0-9a-zA-Z]+$/;
	if (!user.match(pattern)) {
		$('#errorMessage').html("User name can contain text, number only!");
		$('#errorMessage').slideDown();
		return false;
	}
	return true;
}

function del(id) {
	swal({
		title : "Are you sure to delete?",
		text : "You will not be able to recover this Customer!",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "Yes, delete it!",
		closeOnConfirm : false
	}, function() {
		delCustomer(id);

	});
}

function delCustomer(id) {
	$.ajax({
		url : DOMAIN + "/c/customer/delete/" + id,
		type : 'POST',
		data : null,
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
			if (data == "done") {
				var row = $("#customer-" + id);
				$(row).remove();
				notify("Delete customer successfully!", "success");
				swal.close();
			} else {
				notify("Delete customer fail!", "error");
			}
		},
		error : function(message) {
			alert('error');
			console.log(message)
		}
	})
}