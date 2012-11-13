/*
 * NEED 
 * 	- Div element id='who_am_i'
 *  - Div element id='ajax_history'
 */

WHO = ""; // Global variable

function setWHO(uid,pw){
	WHO = {"uid":uid,"key":generateSHA256Hash_withHEXEncoding(pw)};
	$("#who_am_i").text(uid);
}

function generateSHA256Hash_withHEXEncoding(s){
    return CryptoJS.SHA256(s).toString(CryptoJS.enc.HEX);
}

function ajaxToMyogaAPI_with_NO_CACHE(method_obj, success_funciton){ // TODO
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

AJAX_ID = -1; // Global variable
AJAX_REQUEST_TIME = [];  // Global variable
function ajaxToMyogaAPI(method_obj, success_funciton){
	if(WHO == ""){
		console.log("WHO is zero length string. so I didnt do ajax call.");
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
	console.log(WHO);
	console.log("---/");

	var commandInJSON = {command: JSON.stringify({method:method_obj, who:WHO})};
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