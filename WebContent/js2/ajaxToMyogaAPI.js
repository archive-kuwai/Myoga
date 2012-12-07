/**
 * NAAjax.js by Naohiro OHTA, All Rights Reserved.
 */

// NEED uuid
$(function(){
	console.log(uuid.v4());
});
var NAAjax = function(){
	var who = "";
	var uri = "./API";
	var browser_s_tab_id = "fuuo";// uuid.v4();
	var ajaxHistory_Req = function(ajax_id, method_obj){IMPL_ajaxHistory_Req(ajax_id, method_obj);};
	var ajaxHistory_OK = function(ajax_id){IMPL_ajaxHistory_OK(ajax_id);};
	var ajaxHistory_NoUse = function(ajax_id){IMPL_ajaxHistory_NoUse(ajax_id);};
	
	return{
		setWho: function(uid,pw,$disp){
			who = {
					"uid":uid,
					"tab":browser_s_tab_id,
					"key":CryptoJS.SHA256(uid+"MyogaWebAppYay!!Woo!!Bow!!"+pw).toString(CryptoJS.enc.HEX)
			};
			if($disp!=null) $disp.text(uid);
		},
		AJAX_ID: -1,
		AJAX_REQUEST_TIME: [],
		ajax: function(method_obj, success_funciton){
			if(who == ""){
				console.log("'who' is zero length string. so I didnt do ajax call.");
				return false;
			}

			/* ==================== */
			this.AJAX_ID++;
			var ajax_id = this.AJAX_ID;
			this.AJAX_REQUEST_TIME[ajax_id] = new Date();
			/* ==================== */
			
			ajaxHistory_Req(ajax_id, method_obj);
			console.log("/--- Ajax Request");console.log(method_obj);console.log(who);console.log("---/");

			var commandInJSON = {command: JSON.stringify({method:method_obj,who:who})};
			$.ajax({
				type:"POST",
				url:uri,
				data:commandInJSON,
				success:function(result){
						console.log("/--- Ajax Success");console.log(result);console.log("---/");
						success_funciton(result, ajax_id);
						ajaxHistory_OK(ajax_id);
				},
				error:function(error){
					console.log("/--- Ajax Error");console.log(error);console.log("---/");
				}
			});				
		}
	}
}();
