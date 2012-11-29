// Prevent F1 Help on IE
window.onhelp=function(){return false;};

// Prevent backspace key & function keys (F1-F12)
$(document).keydown(function(e){
	var type = e.target.type;
	if(type == 'text'){return true;}
	if(type == 'password'){return true;}
	if(type == 'textarea'){return true;}
	
	var code = e.keyCode;
	if(code==8 /*backspace*/ || (112<=code && code<=123)/*F1-F12*/){
		alert(code);
		console.log("keyCode[" + code + "] is prevented.");
		if(document.all/*IE*/){window.event.keyCode = 0;}
		return false;
	}
});
