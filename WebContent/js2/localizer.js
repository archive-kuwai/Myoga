/**
 * localize.js by Naohiro OHTA, all right reserved.
 */

var localizer = function(){
	
	// --------------------------------------------
	// Private members
	// --------------------------------------------
	var map_OLD = {
		"who": "ユーザ",
		"tab": "ブラウザのタブID",
		"uid": "ユーザID",
		"srvIn": "サーバに依頼した時刻",
		"srvOut": "サーバが返答した時刻",
		"method": "メソッド",
		"name": "名前",
		"params": "引数",
		"filename": "ファイル名"
	};
	
	setWHO("noone","this_is_not_secret_data");
	var map = [];
	var loadMap = function(){
		ajaxToMyogaAPI({"name":"gerLocalizeMap"},function(result){
			console.log(result);
			if(result) map = result;
			else map = [];
		});
	};
	
	// --------------------------------------------
	// Public members
	// --------------------------------------------
	return{
		reLoadMap: loadMap(),
		toJpn: function(eng){
			if(eng in map){
				return map[eng];
			}else{
				return eng;
			}
		}
	};
}();