function isEmptyString(prop) {
	if (prop == null || prop == "") {
		return true;
	} else {
		return false;
	}
}

function isInteger(x) {
	return (typeof x === 'number') && (x % 1 === 0);
}

function notify(message, type) {
	$.growl({
		message : message
	}, {
		type : type,
		allow_dismiss : false,
		label : 'Cancel',
		className : 'btn-xs btn-inverse',
		placement : {
			from : 'top',
			align : 'right'
		},
		delay : 2500,
		animate : {
			enter : 'animated bounceIn',
			exit : 'animated bounceOut'
		},
		offset : {
			x : 20,
			y : 85
		}
	});
};