
function IMPL_ajaxHistory_Req(ajax_id, method_obj){
	/*
	var ajax_history_html = "<ul style='float:left' class='ajax_history_not_received' id='ajax_history_" +ajax_id+ "'>" 
		+ li_with_class(shortTime(AJAX_REQUEST_TIME[ajax_id]),'request_time')
		+ li(method_obj.name)
		+ li(JSON.stringify(method_obj.params))
		+ li_with_class('---','response_time')
		+ li_with_class('---','response_interval');
		+ "</ul>";
	$('#ajax_history_box').append(ajax_history_html);
	*/

	addOptionToSelect($('#ajax_list'),method_obj.name+","+JSON.stringify(method_obj.params));
	$('#ajax_status_img').attr('style','visibility:visible');
}
function IMPL_ajaxHistory_OK(ajax_id){
	/*
	writeTime(ajax_id);
	$('#ajax_history_'+ajax_id).removeClass('ajax_history_not_received');
	$('#ajax_history_'+ajax_id).addClass('ajax_history_received');
	*/
	$('#ajax_status_img').attr('style','visibility:hidden');
}
function IMPL_ajaxHistory_NoUse(ajax_id){
	/*
	writeTime(ajax_id);
	$('#ajax_history_'+ajax_id).removeClass('ajax_history_not_received');
	$('#ajax_history_'+ajax_id).addClass('success_but_not_use_ajax_history');
	*/
	$('#ajax_status_img').attr('style','visibility:hidden');
}
function writeTime(ajax_id){
	/*
	var now = new Date();
	$('#ajax_history_'+ajax_id+' .response_time').text(shortTime(now));
	$('#ajax_history_'+ajax_id+' .response_interval').text((now - AJAX_REQUEST_TIME[ajax_id]) +"ms" );
	*/
}
