//----------------------------
$(function(){

	setGlobalScopeVariable_WHO_AM_I("dummy","dummy_PW");
	
	// Initialize cookie
	$.cookie.json = true;
	
	// Make user selector
	INPUT_USER_NAME_OPTION = '(入力する)';
	var users = logged_in_users();
	for(var i=0; i<users.length; i++){
		$('#user_selector').append("<option value='" +users[i]+ "'>" +users[i]+ "</option>");
	}
	
	// Hide URL bar
	hideURLBar();
});

//----------------------------
function login(){
	var user = $('#user_selector').val();
	if(user == INPUT_USER_NAME_OPTION){
		$('#page_selector').empty();
		jump("login");
		return;
	}
	var password = prompt('myoga パスワード ','myoga');
	setGlobalScopeVariable_WHO_AM_I(user, password);
	ajaxToMyogaAPI({name:"getPerson",params:{a:5,b:6,c:7}}, function(result){console.log(result);});

	reset_page_selector();
	reset_main_content();
}

function jump_with_page_selector(){
	jump($('#page_selector').val());
}

function jump(filename_without_extension){
	ajaxToMyogaAPI(
			{name:"getHTML", params:{"filename":filename_without_extension} },  
			function(result){
				console.log("result");
				console.log(result);
				console.log("result.string");
				console.log(result.string);
				$("#main_content").html(result.string);
				hideURLBar();
			}
	);
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


function reset_page_selector(){ // TODO RENAME!!!!!!
	$('nav').empty();
	var pages = ["page0","page1","page2","page3","page4_map","admin","test_addOptions"];
	var alias = pages; alias[0]="ページ";
	$('nav').append('<select id="page_selector" onChange="jump_with_page_selector()"></select>');
	for(var i=0; i<pages.length; i++){
		$('#page_selector').append("<option value='" +pages[i]+ "' id='" +pages[i]+ "'>" + alias[i] + "</option>");
	}
}

function reset_main_content(){
	$("#main_content").empty();
}

function hideURLBar(){
	setTimeout(function(){window.scrollTo(0, 1);},200);
	setTimeout(function(){window.scrollTo(0, 1);},1000);
}
