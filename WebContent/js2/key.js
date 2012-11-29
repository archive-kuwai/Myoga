var key = function(){
	// Prevent F1 Help on IE
	window.onhelp = function(){return false;};
	
	var prevent = function(code){
		console.log("keyCode[" + code + "] is prevented.");
		if(document.all/*IE*/){window.event.keyCode = 0;}
		return false;
	};
	
	var isInputType = function(type){
		if(type == 'text') return true;
		if(type == 'password') return true;
		if(type == 'textarea') return true;
		if(type == 'select') return true;
		return false;
	};
	
	var tabstops = []; 
	
	var moveToNextTabStop = function(){
		
	};
	
	$(document).keydown(function(e){
		var type = e.target.type;
		var code = e.keyCode;
		switch(code){
			case 8: /*backspace*/
				if(isInputType(type)) return true;
				return prevent(code);
			case 112: /*F1*/
				alert("F1 key down!");
				return prevent(code);
			case 13: /*Enter*/
				if(type == 'textarea') return true;
				/* GOTO case 9 */
			case 9: /*Tab*/
				var lastIdx = tabstops.length - 1;
				$(tabstops).each(function(idx){
					if(this[0] == e.target){
						if(idx == lastIdx){
							tabstops[0][0].focus();
						}else{
							tabstops[idx+1][0].focus();
						}
						return false; // break for jQuery's each().
					}
				});
				return prevent(code);
		}
	});
	
	return { 
		registTabStopArea: function(id){ // Public function
			tabstops = [];
			$('#'+id).find("input,textarea").each(function(){
				console.log($(this));
				/*if(isInputType($(this).type)) */tabstops.push($(this));
			});
			console.log("key->");
			console.log(tabstops);
			console.log("<-key");
		}
	};
	
}();