package webapi;
import webapi.command.Command;
import webapi.html.HTMLCacher;
import net.arnx.jsonic.JSON;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import data.user.SimpleString;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.*;

@WebServlet("/API")
public class THE_ONLY_SERVLET extends HttpServlet {
	private static final long serialVersionUID = 3383077259036476263L;
	
	protected void doGet(HttpServletRequest httpReq, HttpServletResponse httpRes) throws ServletException, IOException {
		doPost(httpReq, httpRes);
	}
	
	protected void doPost(HttpServletRequest httpReq, HttpServletResponse httpRes) throws ServletException, IOException {
		//HTTPレスポンスにコンテントタイプを設定する
		httpRes.setContentType("application/json;charset=utf-8");
		
		// TODO ajaxToMyogaAPI_with_NO_CACHEファンクションをクライアント側で使用することにより、この処理はなくす事。iOS6でキャッシュさせたい。
		//HTTPレスポンスのヘッダにキャッシュコントロールを設定する
		//httpRes.setHeader("cache-control","no-cache"); // TODO  // TODO // TODO

		//HTTPリクエストからcommandパラメータの内容を取り出す。
		String commandInJSON = httpReq.getParameter("command");
		if(commandInJSON == null){
			httpRes.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			String msg = "あなたが送信したHTTPリクエストの中に「command」パラメータがありませんでした。";
			httpRes.getWriter().println(msg);
			Logger.getLogger("").info(msg);
			return;
		}
		Logger.getLogger("").info("httpリクエストのcommandパラメータ(JSON文字列) ->" + commandInJSON);
		
		// 調査
		Cookie[] cookies = httpReq.getCookies();
		String s1 = httpReq.getLocalAddr();
		//java.util.Locale locale = httpReq.getLocale();
		String s2 = httpReq.getLocalName();
		String s3 = httpReq.getRemoteAddr();
		String s4 = httpReq.getRemoteHost();
		String s5 = httpReq.getRemoteUser();
		String s6 = httpReq.getServerName();
		
		//commandパラメータの内容（JSON文字列が期待されている）をCommand Javaオブジェクトにデコードする。
		Command cmd = JSON.decode(commandInJSON, Command.class);
		cmd.srvIn = new Date();
		cmd.save();
		Logger.getLogger("").info("Command(Javaオブジェクト) ->" + cmd.toString());


		//Validなユーザかどうかを検証する。
		if(!Validator.isValidUser(cmd.who)){
			String msg = "ユーザIDもしくはパスワードが間違っています。";
			httpRes.getWriter().println(msg);
			Logger.getLogger("").info(msg);
			return;
		}
		
		//メソッドに対しValidなユーザかどうかを検証する。 //TODO これ(Validか否かの確認）はDispacherでおこなうのが良いのでは？
		if(!Validator.isValidUserForMethod(cmd)){
			String msg = "この処理は行えません。権限が不足しています。";
			httpRes.getWriter().println(msg);
			Logger.getLogger("").info(msg);
			return;
		}
		
		//このCommandのみ、Dispatcherではなく、このサーブレットが直接Dispatchする。 // TODO クライアントブラウザへの戻り値がおかしい
		if(cmd.method.name.equals("clearHTMLCache")){
			HTMLCacher.initFORCE(getServletContext().getRealPath("/"));
		}
		
		//Command Javaオブジェクトの内容によってDispatchして、Executeする。
		HTMLCacher.initNORMAL(getServletContext().getRealPath("/"));
		JSON json = new JSON(); // TODO 毎回インスタンス化する？
		json.setDateFormat("yyyy/MM/dd HH:mm(ss.SSS)");
		String resultInJSONString = dispatch(cmd, json);
		
		//HTTPレスポンスを作成する
		httpRes.getWriter().println(resultInJSONString);
		Logger.getLogger("").info("resultInJSONString is " + resultInJSONString);
		/*
		if( ! cmd.method.name.equals("getHTML")){
			cmd.resultInJSON = resultInJSONString;
		}
		*/
		cmd.srvOut = new Date();
		cmd.save();
	}
	
	private String dispatch(Command cmd, JSON json){
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
		}else if("mashup1".equals(methodName)){
			System.out.println("mashup");
			URL url;
			String resultString = null;
			try {
				url = new URL("http://myoga6.elasticbeanstalk.com/index.html");
				HttpURLConnection cnct = (HttpURLConnection)url.openConnection();
				cnct.setRequestMethod("GET");
				byte[] buf = new byte[1024]; int bufSize; ByteArrayOutputStream outStrm = new ByteArrayOutputStream();

				/*START*/System.out.println("START");
				cnct.connect();
				InputStream inStrm = cnct.getInputStream();
				while((bufSize=inStrm.read(buf)) >= 0) outStrm.write(buf,0,bufSize);
				inStrm.close();
				/*END*/System.out.println("END");

				byte[] resultByteArray = outStrm.toByteArray();
				resultString = new String(resultByteArray,"utf-8");
			} catch (Exception e) {e.printStackTrace();}
			return json.format(new SimpleString(resultString));
		}else if("mashup2".equals(methodName)){
			System.out.println("mashup2_post");
			URL url;
			String resultString = null;
			try {
				url = new URL("http://myoga6.elasticbeanstalk.com/API");
				HttpURLConnection cnct = (HttpURLConnection)url.openConnection();
				cnct.setRequestMethod("POST");
				cnct.setDoOutput(true);
				String body = "command={\"method\":{name:\"getPerson\"}}";

				/*START*/System.out.println("START");
				cnct.connect();
				OutputStream outStrm = cnct.getOutputStream();
				PrintStream prStrm = new PrintStream(outStrm);
				prStrm.print(body);
				prStrm.close(); outStrm.close();
				InputStream inStrm = cnct.getInputStream();
				BufferedReader r = new BufferedReader(new InputStreamReader(inStrm, "utf-8"));
				String s; StringBuffer sb = new StringBuffer();
				while((s=r.readLine())!=null) sb.append(s);
				r.close(); inStrm.close();
				/*END*/System.out.println("END");

				resultString = sb.toString();
			} catch (Exception e) {e.printStackTrace();}
			return json.format(new SimpleString(resultString));
		}else if("mashup2b".equals(methodName)){
			System.out.println("mashup2b_post");
			URL url;
			String resultString = null;
			try {
				url = new URL("myoga6.elasticbeanstalk.com/API");
				HttpURLConnection cnct = (HttpURLConnection)url.openConnection();
				cnct.setRequestMethod("POST");
				cnct.setDoOutput(true);
				//cnct.setRequestProperty("", value)
				String body = "command={\"method\":{name:\"getPerson\"}}";

				/*START*/System.out.println("START");
				cnct.connect();
				OutputStream outStrm = cnct.getOutputStream();
				PrintStream prStrm = new PrintStream(outStrm);
				prStrm.print(body);
				prStrm.close(); outStrm.close();
				InputStream inStrm = cnct.getInputStream();
				BufferedReader r = new BufferedReader(new InputStreamReader(inStrm, "utf-8"));
				String s; StringBuffer sb = new StringBuffer();
				while((s=r.readLine())!=null) sb.append(s);
				r.close(); inStrm.close();
				/*END*/System.out.println("END");

				resultString = sb.toString();
			} catch (Exception e) {e.printStackTrace();}
			return json.format(new SimpleString(resultString));
		}else if("mashup3".equals(methodName)){
			System.out.println("mashup3_post");
			URL url;
			String resultString = null;
			try {
				url = new URL("http://dynamodb.us-east-1.amazonaws.com");
				HttpURLConnection cnct = (HttpURLConnection)url.openConnection();
				cnct.setRequestMethod("POST");
				cnct.setDoOutput(true);
				cnct.setRequestProperty("x-amz-target", "DynamoDB_20111205.GetItem");
				cnct.setRequestProperty("Authorization", "AWS4-HMAC-SHA256 Credential=AccessKeyID/20120116/us-east-1/dynamodb/aws4_request,SignedHeaders=host;x-amz-date;x-amz-target,Signature=ccb4ee48bcb506aaa7e412a7f2f5dceef338666e2478b34acf6631623d377d51");
				String body = "{\"TableName\":\"my-table\",\"Keys\":[{\"HashKeyElement\":{\"S\":\"Bill & Ted's Excellent Adventure\"},\"RangeKeyElement\":{\"S\":1989}}]}";

				/*START*/System.out.println("START");
				cnct.connect();
				OutputStream outStrm = cnct.getOutputStream();
				PrintStream prStrm = new PrintStream(outStrm);
				prStrm.print(body);
				prStrm.close(); outStrm.close();
				InputStream inStrm = cnct.getInputStream();
				BufferedReader r = new BufferedReader(new InputStreamReader(inStrm, "utf-8"));
				String s; StringBuffer sb = new StringBuffer();
				while((s=r.readLine())!=null) sb.append(s);
				r.close(); inStrm.close();
				/*END*/System.out.println("END");

				resultString = sb.toString();
			} catch (Exception e) {e.printStackTrace();}
			return json.format(new SimpleString(resultString));
		}else{
			return null;
		}
	}
	
}