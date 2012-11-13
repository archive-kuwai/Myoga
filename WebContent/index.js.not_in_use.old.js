//----------------------------
function login(){
	var user = $('#user_selector').val();
	if(user == INPUT_USER_NAME_OPTION){
		$('#page_selector').empty();
		jump("login");
		return;
	}
	var password = prompt('myoga パスワード ','myoga');
	setWHO(user, password);
	ajaxToMyogaAPI({name:"getPerson",params:{a:5,b:6,c:7}}, function(result){console.log(result);});

	reset_page_selector();
	empty_main_content();
}


//----------------------------
function jump_to_login_page(){
	var user = prompt('ユーザ','myoga');
	login_with_password(user);
	var users = $.cookie('logged_in_users');
	users.push(user);
	$.cookie('logged_in_users',users);
}

function logged_in_users(){
	var users = $.cookie('logged_in_users');
	if(users == null){
		users = [INPUT_USER_NAME_OPTION];
		$.cookie('logged_in_users',users);
	}
	return users;
}