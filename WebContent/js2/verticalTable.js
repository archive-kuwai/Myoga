/**
 * verticalTable.js by Naohiro OHTA, all right reserved.
 */

var verticalTable = function(){
	
	// --------------------------------------------
	// Private members
	// --------------------------------------------
	var tr = function(data){return "<tr>" + data + "</tr>";};
	var td1 = function(data){return "<td>" + data + "</td>";};
	var td2 = function(data){return "<td align='left' style='width:100%;word-break:break-all;'>" + data + "</td>";};

	var dig = function(s/*string*/,o/*object*/,parentkey){
		// dig function 's private function.
		var li = function(data){return "<li style='list-style-type:none'>" + data + "</li>";};
		var spanForKey = function(k){return "<span class='key'>" +k+ "</span>";};
		var spanForValue = function(i){return "<span class='value'> " +i+ "</span>";};
		// dig function 's  main procedure.
		for(var key in o){
			if(key=='resultInJSON'){console.log("resultInJSON !!");continue;} // TODO delete this line.
			var val = o[key];
			var k = (parentkey) ? parentkey+"."+key : key;
			if(typeof(val)=='object')
				s = dig(s,val,k);
			else
				s += li(spanForKey(k)+spanForValue(val));
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
				$tbl.append(tr(td1(i+1)+td2(dig("",records[i]))));
			}
		}
	};
	
}();