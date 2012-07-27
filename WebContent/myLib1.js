function myCommand(s){
	return "command=" + encodeURIComponent(JSON.stringify(s));
}

function mySHA(s){
    return CryptoJS.SHA256(s).toString(CryptoJS.enc.HEX);
}