function buildVerticalTable(tbl, rows){
	tbl.empty();
	for(var i=0; i<rows.length; i++){
		tbl.append("<tr>" + td_head(i+1) + td(dig("",rows[i])) + "</tr>");
	}
};

/* ------------------------------------ */
function li(data){
	return "<li style='list-style-type:none'>" + data + "</li>";
}

function td_head(data){
	return "<td>" + data + "</td>";
}

function td(data){
	return "<td align='left' style='width:100%;word-break:break-all;'>" + data + "</td>";
}

function spanForKey(k){
	return "<span class='key'>" +k+ "</span>";
}

function spanForValue(i){
	return "<span class='value'> " +i+ "</span>";
}

function dig(s/*string*/,o/*object*/,parentkey){
	for(var key in o){
		if(key=='resultInJSON'){console.log("resultInJSON !!");continue;}
		var value = o[key];
		var valueType = typeof(value);
		if(valueType == 'object'){
			var k;
			if(parentkey==null){
				k = key;
			}else{
				k = parentkey + "." + key;
			}
			s = dig(s,value,k); /*Recursive call*/
		}else{
			var keyForDisp;
			if(parentkey==null){
				keyForDisp = key;
			}else{
				keyForDisp = parentkey + "." + key;
			}
			s += li( spanForKey(keyForDisp) + spanForValue(value) );
		}
	}
	return s;
}