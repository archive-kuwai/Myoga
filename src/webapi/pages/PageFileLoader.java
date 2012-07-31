package webapi.pages;

import java.io.IOException;
import java.nio.charset.*;
import java.nio.*;
import java.nio.file.*;
import d.u.SimpleString;

public class PageFileLoader {

	public static SimpleString page2() {
		StringBuffer sb = new StringBuffer();

		try {
			// TODO 相対パスで読みたい "webapi/pages/page2.html"
			for(String s : Files.readAllLines(Paths.get("C:/Documents and Settings/esl09002039/git_repos/wkspace_juno_myoga/myoga/build/classes/webapi/pages/page2.html"), Charset.forName("UTF-8"))){
			    sb.append(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return new SimpleString(sb.toString());
	}	
	
}
