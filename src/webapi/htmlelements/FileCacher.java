package webapi.htmlelements;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import d.u.SimpleString;

public class FileCacher {
	static private String rootPath = null;
	static private Map<String, SimpleString> files;
	
	static public void initNormal(String filePathAsServletContext){
		if(null == rootPath){initForce(filePathAsServletContext);}
	}

	static public void initForce(String filePathAsServletContext){
		Logger.getLogger("").info("----[FileCacher#initForce]Method with arg -> " + filePathAsServletContext);
		rootPath = filePathAsServletContext;
		files = new HashMap<String, SimpleString>();
	}

	static public SimpleString giveme(String filenameWannaLoad) {
		Logger.getLogger("").info("■■■■■■now I'm here. - " + filenameWannaLoad);
		if(files.containsKey(filenameWannaLoad)){
			Logger.getLogger("").info("There is cached one. - " + filenameWannaLoad);
			return files.get(filenameWannaLoad);
		}else{
			Logger.getLogger("").info("No cached one. - " + filenameWannaLoad);
			SimpleString ss = readFromFile(filenameWannaLoad);
			files.put(filenameWannaLoad, ss);
			return ss;
		}
	}
	
	/* JavaSE7で提供されるjava.nio.fileライブラリを使用すると簡潔に記述できるが、
	 * AWSは現在（2012年08月）、JavaSE7に対応していない為、JavaSE6を使用して記述している */
	static public SimpleString readFromFile(String filenameWannaLoad) {
		StringBuffer sb = new StringBuffer();
		String fullPath = rootPath + "WEB-INF/classes/webapi/htmlelements/" + filenameWannaLoad;
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
