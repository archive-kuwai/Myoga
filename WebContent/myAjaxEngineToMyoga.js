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
		console.log("GlobalScopeVariable_WHO_AM_I is zero length string. so I didnt do ajax call.");
		return false;
	}

	console.log("-------- Ajax request");
	console.log("method_obj:");
	console.log(method_obj);
	console.log("GlobalScopeVariable_WHO_AM_I:");
	console.log(GlobalScopeVariable_WHO_AM_I);

	$.ajax({
		type:"POST",
		url:"./API",
		data:"command=" + encodeURIComponent(JSON.stringify({
			method: method_obj,
			who: GlobalScopeVariable_WHO_AM_I
		})),
		success:function(result){
				console.log("-------- Ajax response, Success");
				console.log("result response:");
				console.log(result);
				success_funciton(result);
			},
		error:function(error){
			console.log("-------- Ajax response, Error");
			console.log("error response:");
			console.log(error);
		}
	});	
}
