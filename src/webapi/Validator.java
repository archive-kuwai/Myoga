package webapi;

import webapi.command.Command;
import webapi.command.Who;


class Validator {
	static boolean isValidUser(Who u){
		return true;
		/*
		if("ootanao++**;;  @@".equals(u.getUid()) && "3fc9b689459d738f8c88a3a48aa9e33542016b7a4052e001aaa536fca74813cb".equals(u.getKey())){
			return true;
		}else{
			return false;
		}
		*/
	}

	static boolean isValidUserForMethod(Command cmd){
		return true;
	}


}
