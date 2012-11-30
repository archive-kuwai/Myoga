/**
 * verticalTable.js by Naohiro OHTA, all right reserved.
 */

var verticalTable = function(){
	
	// --------------------------------------------
	// Private members
	// --------------------------------------------
	var td_head = function(data){return "<td>" + data + "</td>";};
	var td = function(data){return "<td align='left' style='width:100%;word-break:break-all;'>" + data + "</td>";};

	var dig = function(s/*string*/,o/*object*/,parentkey){
		// dig function 's private function.
		var li = function(data){return "<li style='list-style-type:none'>" + data + "</li>";};
		var spanForKey = function(k){return "<span class='key'>" +k+ "</span>";};
		var spanForValue = function(i){return "<span class='value'> " +i+ "</span>";};
		// dig function 's  main procedure.
		for(var key in o){
			if(key=='resultInJSON'){console.log("resultInJSON !!");continue;} // TODO delete this line.
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
	};
	
	// --------------------------------------------
	// Public members
	// --------------------------------------------
	return{
		build: function($tbl, records){
			$tbl.empty();
			for(var i=0; i<records.length; i++){
				$tbl.append("<tr>" + td_head(i+1) + td(dig("",records[i])) + "</tr>");
			}
		}
	};
	
}();