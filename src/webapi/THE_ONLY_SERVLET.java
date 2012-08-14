package webapi;
import webapi.ask.Command;
import webapi.html.HTMLCacher;
import net.arnx.jsonic.JSON;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.logging.*;

@WebServlet("/API")
public class THE_ONLY_SERVLET extends HttpServlet {
	private static final long serialVersionUID = 3383077259036476263L;

	protected void doPost(HttpServletRequest httpReq, HttpServletResponse httpRes) throws ServletException, IOException {

		//Couchbaseサンプルをrunさせる。
		couchbase_sample.Couchbase_Sample_Runner.run();
		
		//HTTPレスポンスにコンテントタイプを設定する
		httpRes.setContentType("application/json;charset=utf-8");
		
		//HTTPリクエストからcommandパラメータの内容を取り出す。
		String commandInJSONString = httpReq.getParameter("command");
		if(commandInJSONString == null){
			httpRes.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			String msg = "あなたが送信したHTTPリクエストの中に「command」パラメータがありませんでした。";
			httpRes.getWriter().println(msg);
			Logger.getLogger("").info(msg);
			return;
		}
		Logger.getLogger("").info("httpリクエストのcommandパラメータ(JSON文字列) ->" + commandInJSONString);
		
		
		//commandパラメータの内容（JSON文字列が期待されている）をCommand Javaオブジェクトにデコードする。
		Command cmd = JSON.decode(commandInJSONString, Command.class);
		Logger.getLogger("").info("Command(Javaオブジェクト) ->" + cmd.toString());


		//Validなユーザかどうかを検証する。
		if(!Validator.isValidUser(cmd.getWho())){
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
		
		//Command Javaオブジェクトの内容によってDispatchして、Executeする。
		HTMLCacher.initNORMAL(getServletContext().getRealPath("/"));
		String resultInJSONString = Dispatcher.dispatch(cmd);

		//HTTPレスポンスを作成する
		httpRes.getWriter().println(resultInJSONString);
		Logger.getLogger("").info("resultInJSONString is " + resultInJSONString);
	}
}