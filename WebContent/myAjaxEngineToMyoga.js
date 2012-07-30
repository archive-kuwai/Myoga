var GlobalScopeVariable_WHO_AM_I = "";

function setGlobalScopeVariable_WHO_AM_I(uid,pw){
	GlobalScopeVariable_WHO_AM_I = {"uid":uid,"key":sHA256WithHEXEncoding(pw)};
	$("#who_am_i").text(uid);
}

function sHA256WithHEXEncoding(s){
    return CryptoJS.SHA256(s).toString(CryptoJS.enc.HEX);
}

function encordedCommandString(s){
	return "command=" + encodeURIComponent(JSON.stringify(s));
}

function ajaxToMyoga(prm_method_obj, success_funciton){
	if(GlobalScopeVariable_WHO_AM_I == ""){console.log("GlobalScopeVariable_WHO_AM_I is zero length string. so I didnt do ajax call. (I am WebBrowser's javascript)"); return false;}
	$.ajax({
		type:"POST",
		url:"./API",
		data:encordedCommandString({
			who:GlobalScopeVariable_WHO_AM_I,
			method:prm_method_obj
		}),
		success:success_funciton,
		error:function(error){console.log("ブラウザで感知したajaxエラーです。console.logにエラー内容を出力しました。"); console.log(error);}
	});	
}
