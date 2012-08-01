package webapi;
import java.util.*;
import d.u.SimpleBool;
import d.u.SimpleString;
import net.arnx.jsonic.JSON;
import webapi.model.Command;
import webapi.pages.PagefileLoader;

class Dispatcher {

	static String dispatch(Command cmd, String filePathAsServletContext){
		if(cmd.getMethod().name.matches("load_.*")){
			return loadPage(cmd, filePathAsServletContext);
		}else{
			return doOtherOperation(cmd);
		}
	}

	static String loadPage(Command cmd, String filePathAsServletContext){
		String pageName = cmd.getMethod().name.replaceFirst("load_", "");
		return JSON.encode(PagefileLoader.loadedOne(pageName + ".html", filePathAsServletContext));
	}
	
	static String doOtherOperation(Command cmd){
		switch(cmd.getMethod().name){
		case "getPerson":
			HashSet<String> s = new HashSet<String>();
			s.add("受注入力");
			s.add("出荷案内");
			d.u.Role r = new d.u.Role("オペレーター第2種", s);
			d.u.User u = new d.u.User("nao01", "a++b++C--qwert", "太田直宏", r);
    		return JSON.encode(u);
		default:
			return null;
		}
	}
	
}
