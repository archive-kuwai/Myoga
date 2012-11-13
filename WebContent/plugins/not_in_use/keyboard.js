/*
 * Allow backspace key 
 */
$(':password, :text, textarea').keydown(function(e){
	if(e.keyCode==8){
	  e.stopPropagation();
	  return true;
	}
});


/*
 * Prevent backspace key & function keys (F1-F12)
 */
$(window).keydown(function(e){

	/* Log all key-input */
	console.log("keyCode[" + e.keyCode + "] detected by $(window).");
	
	if(		
			e.keyCode == 8 /*backspace*/
			|| (112<=e.keyCode && e.keyCode<=123) /*F1-F12*/ 
	){
		console.log("keyCode[" + e.keyCode + "] is prevented.");
		return false;
	}
});