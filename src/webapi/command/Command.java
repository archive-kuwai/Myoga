package webapi.command;

import java.util.Date;

public class Command extends data.Mongoo{
	
	public Command() {
		super(null);
	}
	public Who who;
	public Method method;
	public String resultInJSON;
	public Date requestedTime;
	public Date responsedTime; 
}