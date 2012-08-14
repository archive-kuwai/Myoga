var GlobalScopeVariable_WHO_AM_I = "";

function setGlobalScopeVariable_WHO_AM_I(uid,pw){
	GlobalScopeVariable_WHO_AM_I = {"uid":uid,"key":sHA256WithHEXEncoding(pw)};
	$("#who_am_i").text(uid);
}

function sHA256WithHEXEncoding(s){
    return CryptoJS.SHA256(s).toString(CryptoJS.enc.HEX);
}

function ajaxToMyoga(method_obj, success_funciton){
	if(GlobalScopeVariable_WHO_AM_I == ""){
		console.log("GlobalScopeVariable_WHO_AM_I is zero length string. so I didnt do ajax call. (I am WebBrowser's javascript)");
		return false;
	}
	$.ajax({
		type:"POST",
		url:"./API",
		data:"command=" + encodeURIComponent(JSON.stringify({
			method: method_obj,
			who: GlobalScopeVariable_WHO_AM_I
		})),
		success:success_funciton,
		error:function(error){
			console.log("[myAjaxEnginToMyoga.js#ajaxToMyoga]function. Error, I did ajax call, but server returned Error object. I'll log it here.");
			console.log(error);
		}
	});	
}
