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

latest_request_page = "";
latest_received_page = "";
function jump(filename_without_extension){
	latest_request_page = filename_without_extension;
	ajaxToMyogaAPI(
			{name:"getHTML", params:{"filename":filename_without_extension} },  
			function(result,ajax_id){
				if(latest_request_page != filename_without_extension){
					console.log("★this page data comes just now as Ajax;");
					console.log(filename_without_extension);
					console.log("☆but latest_request_page is this one;");
					console.log(latest_request_page);
					console.log("★So we ignore page data that we receive just now.");
					success_but_not_use_ajax_history(ajax_id);
					return;
				}
				if(latest_received_page == filename_without_extension && filename_without_extension != 'login'){
					console.log("★this page data comes just now as Ajax;");
					console.log(filename_without_extension);
					console.log("☆but latest_received_page is this one;");
					console.log(latest_received_page);
					console.log("★Both same one. So we ignore page data that we receive just now.");
					success_but_not_use_ajax_history(ajax_id);
					return;
				}
				latest_received_page = filename_without_extension;
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
