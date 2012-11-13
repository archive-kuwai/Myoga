function cutStringByLength(str, too_long_letters_to_disp){
	if (str.length >= too_long_letters_to_disp){
		str = str.substring(0, too_long_letters_to_disp - 2) + "…";
	}
	return str;
}