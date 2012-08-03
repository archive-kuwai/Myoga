package webapi.htmlelements;

import java.io.IOException;
import java.nio.charset.*;
import java.nio.*;
import java.nio.file.*;
import d.u.SimpleString;

public class FileLoader {

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



}
