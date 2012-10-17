function addOptions(selector, vals, texts){
	console.log("addOptions function (on addOption.js) is called.");
	selector.empty();
	if(vals==null)return;
	for(var i=0; i<vals.length; i++){
		selector.append("<option value='" +vals[i]+ "' id='" +vals[i]+ "'>" + texts[i] + "</option>");
	}

}

