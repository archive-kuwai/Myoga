function optionTag(val, text){
	return "<option value='" +val+ "' id='" +val+ "'>" + text + "</option>";
}

function addOptions(sel, vals, texts){
	var NO_VALUE_TEXT = '-';
	console.log("addOptions function (on addOption.js) is called.");
	sel.empty();
	if(vals==null)return;
	sel.append(optionTag(NO_VALUE_TEXT, NO_VALUE_TEXT));
	for(var i=0; i<vals.length; i++){
		sel.append(optionTag(vals[i],texts[i]));
	}
	sel.change(function(){
		var opt1st = sel.find('option:first');
		if(opt1st.val() == NO_VALUE_TEXT){
			opt1st.remove();
		}
	});
}

