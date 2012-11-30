/**
 * key.js by Naohiro OHTA, all right reserved.
 */
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
	
	// Private variable
	var tabstops = []; 
	var tabstopForEscKey = null; /*jQuery object*/
	var enterkeyMode = 0;
	
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
				if(enterkeyMode == 0){
					return true;
				}else if(enterkeyMode == 1){
					if(e.ctrlKey && type=='textarea') {
						if($.support.opacity){ // not IE 
							var val = e.target.value;
							var st = e.target.selectionStart;
							var ed = e.target.selectionEnd;
							e.target.value = val.substr(0,st) + "\n" + val.substr(ed);
							e.target.setSelectionRange(st+1,st+1);
							return prevent(code);
						}else{ // IE
							var rng = document.selection.createRange();
							rng.text = "\n";
							rng.select();
							return prevent(code);
						}
					}
					return moveToNextTabStop(e);
/*
				}else if(enterkeyMode == 1){
					if(e.ctrlKey && type=='textarea') {
						var $obj = $(e.target);
						var val = $obj.val();
						var st = $obj.attr('selStart');
						var ed = $obj.attr('selEnd');
						$obj.val(val.substr(0,st) + "\n" + val.substr(ed));
						$obj.attr('selectionRange',st+1,st+1);
						return prevent(code);
					}
					return moveToNextTabStop(e);
*/					
				}else if(enterkeyMode == 2){
					if(type == 'textarea') return true;
					if(type == 'submit') return true;
					if(type == 'button') return true;
					return moveToNextTabStop(e);
				}else{
					return true;
				}
			case 27: /*ESC*/
				if(tabstopForEscKey) tabstopForEscKey[0].focus();
		}
	});

	// Once call function - Prevent F1 Help on IE
	window.onhelp = function(){return false;};
	
	return { 
		// Public function
		setTabstops: function(root/*jqObject*/){
			tabstops = [];
			root.find("input,textarea,select,button").each(function(){
				/*if(isInputType($(this).type)) */tabstops.push($(this));
			});
			console.log("tabstops.length is [" + tabstops.length + "].");
		},
		setTabstopForEscKey: function(jq){
			tabstopForEscKey = jq;
		},
		setEnterkeyMode: function(num){
			enterkeyMode = num;
		}
	
	};
	
}();