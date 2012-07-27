$(function(){
	makeMyID('ootanao++**;;  @@','something');
	$.ajax({
			type:"POST",
			url:_global_scope_myURL,
			data:myCommand({
				who:_global_scope_myID,
				method:{name:"getPerson", params:{a:1,b:2,c:3}},
			}),
			success:function(result){console.log(result);},
			error:function(error){console.log("ajaxエラー。"); console.log(error);}
	});
});
