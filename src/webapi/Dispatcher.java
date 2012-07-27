package webapi;
import java.util.*;

import net.arnx.jsonic.JSON;
import webapi.model.Command;

class Dispatcher {

	static String dispatch(Command cmd){

		if("getPerson".equals(cmd.getMethod().name)){
			HashSet<String> s = new HashSet<String>();
			s.add("受注入力");
			s.add("出荷案内");
			d.u.Role r = new d.u.Role("オペレーター第2種", s);
			d.u.User u = new d.u.User("nao01", "a++b++C--qwert", "太田直宏", r);
    		return JSON.encode(u);
    	}else{
    		return null;
    	}
	}
	
}
