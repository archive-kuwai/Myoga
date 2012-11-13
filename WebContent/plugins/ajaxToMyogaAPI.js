var GlobalScopeVariable_WHO_AM_I = "";

function setGlobalScopeVariable_WHO_AM_I(uid,pw){
	GlobalScopeVariable_WHO_AM_I = {"uid":uid,"key":sHA256WithHEXEncoding(pw)};
	$("#who_am_i").text(uid);
}

function sHA256WithHEXEncoding(s){
    return CryptoJS.SHA256(s).toString(CryptoJS.enc.HEX);
}

function ajaxToMyogaAPI_with_NO_CACHE(method_obj, success_funciton){
}

function write_response_time(ajax_id){
	var now = new Date();
	$('#ajax_history_'+ajax_id+' .response_time').text(shortTime(now));
	$('#ajax_history_'+ajax_id+' .response_interval').text((now - AJAX_REQUEST_TIME[ajax_id]) +"ms" );
}

function success_ajax_history(ajax_id){
	write_response_time(ajax_id);
	$('#ajax_history_'+ajax_id).removeClass('ajax_history_not_received');
	$('#ajax_history_'+ajax_id).addClass('ajax_history_received');
}

function success_but_not_use_ajax_history(ajax_id){
	write_response_time(ajax_id);
	$('#ajax_history_'+ajax_id).removeClass('ajax_history_not_received');
	$('#ajax_history_'+ajax_id).addClass('success_but_not_use_ajax_history');
}


function li(s){
	return "<li style='text-align:left'>" +s+ "</li>";
}
function li_with_class(s,cls){
	return "<li style='text-align:left' class='" +cls+ "'>" +s+ "</li>";
}
function shortTime(d){
	var h = d.getHours();
	var m = d.getMinutes();
	if(h<10){h="0"+h;}
	if(m<10){m="0"+m;}
	return h + ":" + m + " ." +d.getSeconds();
}

AJAX_ID = -1;
AJAX_REQUEST_TIME = [];
function ajaxToMyogaAPI(method_obj, success_funciton){
	if(GlobalScopeVariable_WHO_AM_I == ""){
		console.log("GlobalScopeVariable_WHO_AM_I is zero length string. so I didnt do ajax call.");
		return false;
	}

	/* ==================== */
	AJAX_ID++;
	var ajax_id = AJAX_ID;
	AJAX_REQUEST_TIME[ajax_id] = new Date();
	/* ==================== */
	
	var ajax_history_html = "<ul style='float:left' class='ajax_history_not_received' id='ajax_history_" +ajax_id+ "'>" 
		+ li_with_class(shortTime(AJAX_REQUEST_TIME[ajax_id]),'request_time')
		+ li(method_obj.name)
		+ li(JSON.stringify(method_obj.params))
		+ li_with_class('---','response_time')
		+ li_with_class('---','response_interval');
		+ "</ul>";
	
	console.log(ajax_history_html);
	$('#ajax_history_box').append(ajax_history_html);
	
	console.log("/--- Ajax Request");
	console.log(method_obj);
	console.log(GlobalScopeVariable_WHO_AM_I);
	console.log("---/");

	var commandInJSON = {command: JSON.stringify({method:method_obj, who:GlobalScopeVariable_WHO_AM_I})};
	$.ajax({
		type:"POST",
		url:"./API",
		data:commandInJSON,
		success:function(result){
				console.log("/--- Ajax Success");
				console.log(result);
				console.log("---/");
				success_funciton(result, ajax_id);
				success_ajax_history(ajax_id);
			},
		error:function(error){
			console.log("/--- Ajax Error");
			console.log(error);
			console.log("---/");
		}
	});	
}