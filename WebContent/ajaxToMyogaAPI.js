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

function ajaxToMyogaAPI(method_obj, success_funciton){
	if(GlobalScopeVariable_WHO_AM_I == ""){
		console.log("GlobalScopeVariable_WHO_AM_I is zero length string. so I didnt do ajax call.");
		return false;
	}

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
				success_funciton(result);
			},
		error:function(error){
			console.log("/--- Ajax Error");
			console.log(error);
			console.log("---/");
		}
	});	
}