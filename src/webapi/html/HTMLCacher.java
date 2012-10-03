package webapi.html;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import d.u.SimpleString;

public class HTMLCacher {
	
	private static String rootPath = null;
	private static Map<String, SimpleString> files;
	
	public static void initNORMAL(String filePathAsServletContext){
		initFORCE(filePathAsServletContext);
		//if(null == rootPath){initFORCE(filePathAsServletContext);}
	}

	/*　このメソッドはPublicである必要がある。と思う。キャッシュをクリアしたい時もあるでしょう。　*/
	public static void initFORCE(String filePathAsServletContext){
		Logger.getLogger("").info("HTMLCacher#initFORCE with arg:" + filePathAsServletContext);
		rootPath = filePathAsServletContext;
		files = new HashMap<String, SimpleString>();
	}

	public static SimpleString getHTML(String filenameWannaLoad) {
		if(files.containsKey(filenameWannaLoad)){
			return files.get(filenameWannaLoad);
		}else{
			SimpleString ss = readFile(filenameWannaLoad);
			files.put(filenameWannaLoad, ss);
			return ss;
		}
	}
	
	/* JavaSE7で提供されるjava.nio.fileライブラリを使用すると簡潔に記述できるが、
	 * AWSは現在（2012年08月）、JavaSE7に対応していない為、JavaSE6を使用して記述している */
	private static SimpleString readFile(String filenameWannaLoad) {
		Logger.getLogger("").info("[HTMLCacher#readFile]:" + filenameWannaLoad);
		StringBuffer sb = new StringBuffer();
		String fullPath = rootPath + "WEB-INF/classes/webapi/html/" + filenameWannaLoad;
		try {
			InputStream stream = new FileInputStream(fullPath); 
			InputStreamReader sReader = new InputStreamReader(stream, "UTF-8");
			BufferedReader bfdReader = new BufferedReader(sReader);
			String s;
			while(null != (s=bfdReader.readLine())) {
			    sb.append(s);
			}
			bfdReader.close();
			sReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SimpleString(sb.toString());
	}
}
/* JavaSE7を使用するとこんな感じで記述できると思います。
import java.nio.*;
import java.nio.file.*;
public class FileLoader {
	public static SimpleString load(String filenameWannaLoad) {
		StringBuffer sb = new StringBuffer();
		try {
			for(String s : Files.readAllLines(Paths.get(rootPath, "WEB-INF/classes/webapi/htmlelements/", filenameWannaLoad), Charset.forName("UTF-8"))){
			    sb.append(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return new SimpleString(sb.toString());
	}	
*/
