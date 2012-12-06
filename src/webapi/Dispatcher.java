package webapi;
import java.util.*;

import data.user.SimpleString;
import net.arnx.jsonic.JSON;
import webapi.command.Command;
import webapi.html.HTMLCacher;

class Dispatcher {
	static public String dispatch(Command cmd, JSON json){
		String methodName = cmd.method.name;
		if("getHTML".equals(methodName)){
			if(cmd.method.params.get("filename").equals("page3")){
				System.out.println("page3 waiting...");
				try{Thread.sleep(5000);}catch(Exception e){}
				System.out.println("page3 waiting... Finished.");
			}
			String filename = cmd.method.params.get("filename") + ".html";
			return json.format(HTMLCacher.getHTML(filename));
		}else if("getPerson".equals(methodName)){
			HashSet<String> s = new HashSet<String>();
			s.add("Order");
			s.add("SayShipIt");
			data.user.Role r = new data.user.Role("the1st_operator", s);
			/*
			r.save();
			r.save();
			r.save();
			r.name = "the2ndOperator";
			r.save();
			r.save();
			*/
			data.user.User u = new data.user.User("nao01", "a++b++C--qwert", "太田直一郎", r);
    		return json.format(u);
		}else if("getListAsTest".equals(methodName)){
			List<String> ls = new ArrayList<String>();
			ls.add("test1"); ls.add("test2"); ls.add("test3"); ls.add("test4");
			return json.format(ls);
		}else if("getLoginUsers".equals(methodName)){
			//List<String> ls = new webapi.command.Command().distinctUniqueNames();
			//return json.format(Command.loginUsers());
			List<String> ls = new ArrayList<String>();
			ls.add("User1");
			ls.add("User2");
			ls.add("User3");
			return json.format(ls);
		}else if("getUserActs".equals(methodName)){
			String uid = cmd.method.params.get("uid");
			//List<webapi.command.Command> ls = null;
			//List ls = new webapi.command.Command().userActs(uid);
			//
			//return json.format(Command.userActs(uid));
			
			Collection coll;
			if(uid.equals("User2")){
				coll = new ArrayList<String>();
				coll.add("yahoooo");
				coll.add("mm...");
				coll.add("wooowwowww");
				coll.add("こんにちはー");
			}else{
				coll = (Collection)cmd.map.values();
			}
			return json.format(coll);
		}else if("getLocalizeMap".equals(methodName)){
			Map<String,String> m = new HashMap<String,String>();
			m.put("who", "ユーザ");
			m.put("tab", "ブラウザのタブID");
			m.put("uid", "ユーザID");
			m.put("srvIn", "サーバに依頼した時刻");
			m.put("srvOut", "サーバが返答した時刻");
			m.put("method", "メソッド");
			m.put("name", "名前");
			m.put("params", "引数");
			m.put("filename", "ファイル名");
			return json.format(m);
		}else{
			return null;
		}
	}
	
}
/*
class Dispatcher {

	static public String dispatch(Command cmd){
		if(cmd.getMethod().name.matches("giveme_.*")){
			return loadHTMLElementAsString(cmd);
		}else{
			return doOtherOperation(cmd);
		}
	}

	static private String loadHTMLElementAsString(Command cmd){
		String fileName = cmd.getMethod().name.replaceFirst("giveme_", "");
		return JSON.encode(HTMLCacher.giveMeFileAsSimpleString(fileName + ".html"));
	}
	
	static private String doOtherOperation(Command cmd){
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

*/
