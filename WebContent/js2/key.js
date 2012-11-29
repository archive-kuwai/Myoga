var key = function(){

	// Private function
	var prevent = function(code){
		console.log("keyCode[" + code + "] is prevented.");
		if(document.all/*IE*/){window.event.keyCode = 0;}
		return false;
	};
	
	// Private function
	var isInputType = function(type){
		if(type == 'text') return true;
		if(type == 'password') return true;
		if(type == 'textarea') return true;
		if(type == 'select') return true;
		return false;
	};
	
	// Private variable
	var tabstops = []; 
	
	// Private function
	var moveToNextTabStop = function(e){
		var lastIdx = tabstops.length-1;
		var hit = false;
		$(tabstops).each(function(idx){
			if(this[0] == e.target){
				var direction = (e.shiftKey) ? -1 : +1; 
				var idxWillFocus = idx + direction;
				if(idxWillFocus < 0) idxWillFocus = lastIdx;
				if(lastIdx < idxWillFocus) idxWillFocus = 0;
				tabstops[idxWillFocus][0].focus();
				hit = true;
				return false; // like 'break'.
			}
		});
		if(!hit && tabstops[0]){tabstops[0][0].focus();} 
		return prevent(e.keyCode);
	};
	
	// Once call function - Prevent F1 Help on IE
	window.onhelp = function(){return false;};
	
	// Once call function - Bind the keys
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
			case 9: /*Tab*/
				return moveToNextTabStop(e);
			case 13: /*Enter*/
				if(type == 'textarea') return true;
				return moveToNextTabStop(e);
		}
	});

	var listTabstopsOnConsole = function(){
		console.log("/* listTabstopsOnConsole---------------------------------------");
		console.log("tabstops.length is [" + tabstops.length + "].");
		$(tabstops).each(function(){console.log(this[0]);});
		console.log("---------------------------------------listTabstopsOnConsole */");
	};

	return { 
		// Public function
		setTabstops: function(root_id){
			tabstops = [];
			$('#'+root_id).find("input,textarea,select").each(function(){
				/*if(isInputType($(this).type)) */tabstops.push($(this));
			});
			listTabstopsOnConsole();
		}
	};
	
}();