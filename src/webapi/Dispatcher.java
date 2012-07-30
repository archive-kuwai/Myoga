package webapi;
import java.util.*;

import d.u.SimpleBool;
import d.u.SimpleString;

import net.arnx.jsonic.JSON;
import webapi.model.Command;

class Dispatcher {

	static String dispatch(Command cmd){
		switch(cmd.getMethod().name){
		case "getPerson":
			HashSet<String> s = new HashSet<String>();
			s.add("受注入力");
			s.add("出荷案内");
			d.u.Role r = new d.u.Role("オペレーター第2種", s);
			d.u.User u = new d.u.User("nao01", "a++b++C--qwert", "太田直宏", r);
    		return JSON.encode(u);
		case "showPage2":
			return JSON.encode(new SimpleBool(true));
		default:
			return null;
		}
		
		/*
		if("getPerson".equals(cmd.getMethod().name)){
			HashSet<String> s = new HashSet<String>();
			s.add("受注入力");
			s.add("出荷案内");
			d.u.Role r = new d.u.Role("オペレーター第2種", s);
			d.u.User u = new d.u.User("nao01", "a++b++C--qwert", "太田直宏", r);
    		return JSON.encode(u);
    	}else if(1==1){
    		
    	}else{
    		return null;
    	}
		*/
	}
}
