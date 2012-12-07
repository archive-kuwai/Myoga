/**
 * NALocalizer.js by Naohiro OHTA, All Rights Reserved.
 */

var NALocalizer = function(){
	
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
	
	NAAjax.setWho("noone","this_is_not_secret_data",null);
	var map = [];
	var loadMap = function(){
		NAAjax.ajax({"name":"getLocalizeMap"},function(result){
			if(result!=null) map = result;
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