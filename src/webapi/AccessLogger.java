package webapi;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;
import webapi.ask.Command;
import com.couchbase.client.*;
import net.spy.memcached.internal.*;
public class AccessLogger {

	private static CouchbaseClient couch = initialConect();

	private static CouchbaseClient initialConect(){
		CouchbaseClient couch = null;
		List<URI> uris = new LinkedList<URI>();
		uris.add(URI.create("http://localhost:8091/pools"));
		try{
			couch = new CouchbaseClient(uris, "default", "");
		}catch(Exception e){
			Logger.getLogger("").info("Error connecting to Couchbase: "+e.getMessage());
			System.exit(0);
		}
		return couch;
	}
	
	public static void add(Command cmd, String json){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd.HHmmss.SSS");
		String key = String.format( 
				"%s[%s]", 
				cmd.getWho().getUid(),
				fmt.format(new Date()) // TODO 日付は先に取得すべきかな。You Should set Date earlier.
		);
		add(key,json);
	}

	private static void add(String key, Object o){
		int exp = 10;
		couch.add(key, exp, o);
	}
	
}
