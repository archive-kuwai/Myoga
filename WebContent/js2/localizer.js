/**
 * localize.js by Naohiro OHTA, all right reserved.
 */

var localizer = function(){
	
	// --------------------------------------------
	// Private members
	// --------------------------------------------
	var tr = function(data){return "<tr>" + data + "</tr>";};
	var td1 = function(data){return "<td>" + data + "</td>";};
	var td2 = function(data){return "<td align='left' style='width:100%;word-break:break-all;'>" + data + "</td>";};

	var dig = function(s/*string*/,o/*object*/,parentkey){
		// dig function 's private functions.
		var li = function(data){return "<li style='list-style-type:none'>" + data + "</li>";};
		var spanForKey = function(k){return "<span class='key'>" +k+ "</span>";};
		var spanForValue = function(i){return "<span class='value'> " +i+ "</span>";};
		// dig function 's  main procedure.
		for(var k in o){
			var v = o[k];
			var keyInJPN = localize.toJPN(k);
			var dottedKey = (parentkey) ? parentkey+"."+keyInJPN : keyInJPN;
			if(typeof(v)=='object')
				s = dig(s,v,dottedKey);
			else
				s += li(spanForKey(dottedKey)+spanForValue(v));
		}
		return s;
	};
	
	// --------------------------------------------
	// Public members
	// --------------------------------------------
	return{
		toJpn: function(eng){
			switch(eng){
				case "who": return "ユーザ";
				case "tab": return "ブラウザのタブID";
				case "uid": return "ユーザID";
				case "srvIn": return "サーバに依頼した時刻";
				case "srvOut": return "サーバが返答した時刻";
				case "method": return "メソッド";
				case "name": return "名前";
				case "params": return "引数";
				case "filename": return "ファイル名";
				default: return eng;
			}
		}
	};
	
}();