/*
 * NEED 
 * 	- Div element id='who_am_i'
 *  - Div element id='ajax_history_box'
 */

WHO = ""; // Global variable

function setWHO(uid,pw){
	WHO = {"uid":uid,"key":generateHashKey(uid,pw)};
	$("#who_am_i").text(uid);
}

function generateHashKey(uid,pw){
    return CryptoJS.SHA256(uid+"MyogaWebAppYay!!Woo!!Bow!!"+pw).toString(CryptoJS.enc.HEX);
}

/* -------------------------------- */
/* Request History */
function ajaxHistory_Req(ajax_id, method_obj){IMPL_ajaxHistory_Req(ajax_id, method_obj);}
/* -------------------------------- */


/* -------------------------------- */
/* log res */
function ajaxHistory_OK(ajax_id){IMPL_ajaxHistory_OK(ajax_id);}
function ajaxHistory_NoUse(ajax_id){IMPL_ajaxHistory_NoUse(ajax_id);}
/* -------------------------------- */

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
	
	ajaxHistory_Req(ajax_id, method_obj);
	
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
				ajaxHistory_OK(ajax_id);
			},
		error:function(error){
			console.log("/--- Ajax Error");
			console.log(error);
			console.log("---/");
		}
	});	
}