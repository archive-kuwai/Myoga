package webapi.htmlelements;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import d.u.SimpleString;

public class FileReader {
	private String rootPath;
	private Map<String, SimpleString> files;
	
	public FileReader(String filePathAsServletContext){
		this.rootPath = filePathAsServletContext;
		this.files = new HashMap<String, SimpleString>();
	}

	public SimpleString read(String filenameWannaLoad) {
		if(files.containsKey(filenameWannaLoad)){
			return files.get(filenameWannaLoad);
		}else{
			SimpleString ss = readFromFile(filenameWannaLoad);
			files.put(filenameWannaLoad, ss);
			return ss;
		}
	}
	
	public SimpleString readFromFile(String filenameWannaLoad) {
		Logger.getLogger("").info("now I'm in FileReader#readFromFile(String)");
		StringBuffer sb = new StringBuffer();
		String fullPath = this.rootPath + "WEB-INF/classes/webapi/htmlelements/" + filenameWannaLoad;
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
