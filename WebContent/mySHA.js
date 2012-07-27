function mySHA(s){
    return CryptoJS.SHA256(s).toString(CryptoJS.enc.HEX);
}