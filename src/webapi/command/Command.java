package webapi.command;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import data.JVMoo;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONHint;

public class Command extends JVMoo{
	
	public Command() throws IOException {}
	@JSONHint(ordinal=1) public Date when_receive;
	@JSONHint(ordinal=2) public Date when_answer;
	@JSONHint(ordinal=3) public Method method;

	@JsonIgnore
	public String getUuid(){return uuid;}
	public void setUuid(String s){this.uuid=s;}
	private String uuid;
	
	@JSONHint(ordinal=0) 
	public Who who;
	public String getWhenReceive(){return fmt.format(when_receive);}
	public void setWhenReceive(String s) throws ParseException{when_receive=fmt.parse(s);}
	
	public String getUid(){return who.uid;}
	public void setUid(String s){
		if(who==null){who = new Who();}
		who.uid=s;
	} 

	public String getMethodName(){return method.name;}
	public void setMethodName(String s){
		if(method==null){method = new Method();}
		method.name=s;
	}
	
	public String getParams(){
		if(method.params==null){
			return "";
		}else{
			return JSON.encode(method.params);
		}
	}
	public void setParams(String s){method.params = JSON.decode(s, Map.class);}
	
	public String getWhenAnswer(){
		if(when_answer==null){
			return "";
		}
		return fmt.format(when_answer);
	}
	public void setWhenAnswer(String s){
		if(s==null){return;}
		try {
			when_answer = fmt.parse(s);
		} catch (ParseException e) {
			when_answer = null;
		}
	}
	
	
}