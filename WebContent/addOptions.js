function addOptions(sel, vals, texts){
	console.log("addOptions function (on addOption.js) is called.");
	sel.empty();
	if(vals==null)return;
	for(var i=0; i<vals.length; i++){
		sel.append("<option value='" +vals[i]+ "' id='" +vals[i]+ "'>" + texts[i] + "</option>");
	}

}

