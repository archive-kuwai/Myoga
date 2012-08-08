package webapi.htmlelements;

import java.io.*;
import d.u.SimpleString;

public class FileLoader {

	public static SimpleString load(String filenameWannaLoad, String filePathAsServletContext) {
		StringBuffer sb = new StringBuffer();
		try {
			InputStream stream = new FileInputStream(filePathAsServletContext + "WEB-INF/classes/webapi/htmlelements/" + filenameWannaLoad); 
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

	//--------------------------------
	// Using Java SE7 (java.nio.File)
    /*--------------------------------
	public static SimpleString load(String filenameWannaLoad, String filePathAsServletContext) {
		StringBuffer sb = new StringBuffer();
		try {
			for(String s : Files.readAllLines(Paths.get(filePathAsServletContext, "WEB-INF/classes/webapi/htmlelements/", filenameWannaLoad), Charset.forName("UTF-8"))){
			    sb.append(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return new SimpleString(sb.toString());
	}	
	*/


}
