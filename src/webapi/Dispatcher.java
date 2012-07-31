package webapi;
import java.io.IOException;
import java.util.*;

import d.u.SimpleBool;
import d.u.SimpleString;
import net.arnx.jsonic.JSON;
import webapi.model.Command;
import webapi.pages.PageFileLoader;

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
		case "show_page1":
			return JSON.encode(new SimpleBool(true));
		case "show_page2":
			return JSON.encode(new SimpleBool(true));
		case "show_page3":
			return JSON.encode(new SimpleBool(true));
		case "load_page1":
			return JSON.encode(new SimpleString("<h1>どうでしょう？サーバ上のJavaEEです。Hello.</h1>"));
		case "load_page2":
			return JSON.encode(PageFileLoader.page2());
		default:
			return null;
		}
	}

}
