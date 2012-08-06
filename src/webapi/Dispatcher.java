package webapi;
import java.util.*;
import d.u.SimpleBool;
import d.u.SimpleString;
import net.arnx.jsonic.JSON;
import webapi.htmlelements.FileLoader;
import webapi.model.Command;

class Dispatcher {

	static String dispatch(Command cmd, String filePathAsServletContext){
		if(cmd.getMethod().name.matches("giveme_.*")){
			return loadHTMLElementAsString(cmd, filePathAsServletContext);
		}else{
			return doOtherOperation(cmd);
		}
	}

	static String loadHTMLElementAsString(Command cmd, String filePathAsServletContext){
		String fileName = cmd.getMethod().name.replaceFirst("giveme_", "");
		return JSON.encode(FileLoader.load(fileName + ".html", filePathAsServletContext));
	}
	
	static String doOtherOperation(Command cmd){
		String methodName = cmd.getMethod().name;
		if("getPerson".equals(methodName)){
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
