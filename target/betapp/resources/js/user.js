function addUser() {
	var newUsers = $("#username").val().split(",");
	if (!validateUser(newUsers)) {
		return;
	}

	$
			.ajax({
				url : DOMAIN + "/data/user/add",
				type : 'POST',
				data : JSON.stringify({
					users : newUsers
				}),
				contentType : 'application/json; charset=utf-8',
				success : function(data) {
					if (data[0] == "user.empty") {
						$('#errorMessage').html("User name must not be empty!");
						$('#errorMessage').slideDown();
						return false;
					} else if (data[0] == "user.duplicate") {
						$('#errorMessage').html("User name existed!");
						$('#errorMessage').slideDown();
						return false;
					} else if (data[0] == "user.badword") {
						$('#errorMessage').html("User name must be word and number!");
						$('#errorMessage').slideDown();
						return false;
					} else if (data[0] == "user.limit") {
						$('#errorMessage').html("You have reached the limit of Users!");
						$('#errorMessage').slideDown();
						return false;
					} else if (data[0] == 'done') {
						var userTable = $("#userTable");
						for (var i = 0; i < newUsers.length; i++) {
							var user = newUsers[i];
							$(userTable)
									.append(
											"<tr id='user-"
													+ data[1][i]
													+ "'><td>"
													+ user
													+ "</td>"
													+ "<td><button type='button'"
													+ " class='btn btn-danger'onclick='del("
													+ data[1][i]
													+ ")'>"
													+ "<span class='md md-delete'></span></button></td></tr>");
						}
						$("#username").val("");
						notify("Update successfully!", "success");
					} else {
						$('#errorMessage').html(data);
						$('#errorMessage').slideDown();
						return false;
					}

				},
				error : function() {
					alert('error');
				}
			})
}

function validateUser(users) {
	for ( var i in users) {
		user = users[i];
		if (isEmptyString(user)) {
			$('#errorMessage').html("User cannot empty!");
			$('#errorMessage').slideDown();
			return false;
		}
		var pattern = /^[0-9a-zA-Z]+$/;
		if (!user.match(pattern)) {
			$('#errorMessage').html("User can contain text, number only!");
			$('#errorMessage').slideDown();
			return false;
		}
	}
	return true;
}

function del(id) {
	swal({
		title : "Are you sure to delete?",
		text : "You will not be able to recover this user!",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "Yes, delete it!",
		closeOnConfirm : false
	}, function() {
		deleteUser(id);

	});
}

function deleteUser(id) {
	$.ajax({
		url : DOMAIN + "/data/user/delete/" + id,
		type : 'POST',
		data : null,
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
			if (data == "done") {
				var row = $("#user-" + id);
				$(row).remove();
				notify("Delete user successfully!", "success");
				swal.close();
			} else {
				notify("Delete user fail!", "error");
			}
		},
		error : function(message) {
			alert('error');
			console.log(message)
		}
	})
}