function make_selector(jq, target_function, items, labels){
	jq.empty();
	jq.append('<select onChange="' +target_function+ '"></select>');
	for(var i=0; i<items.length; i++){
		jq.find('select').append("<option value='" +items[i]+ "' id='" +items[i]+ "'>" + labels[i] + "</option>");
	}
}


function make_selector2(jq, items, labels){
	jq.empty();
	jq.append('<select></select>');
	for(var i=0; i<items.length; i++){
		jq.find('select').append("<option value='" +items[i]+ "' id='" +items[i]+ "'>" + labels[i] + "</option>");
	}

}

