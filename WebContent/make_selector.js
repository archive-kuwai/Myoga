function make_selector(jq, items, labels){
	jq.empty();
	jq.append('<select></select>');
	for(var i=0; i<items.length; i++){
		jq.find('select').append("<option value='" +items[i]+ "' id='" +items[i]+ "'>" + labels[i] + "</option>");
	}

}

