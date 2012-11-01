package webapi.command;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONHint;

public class Command {
	
	public Command() throws IOException {}
	@JSONHint(ordinal=0) public Who who;
	@JSONHint(ordinal=1) public Date when_receive;
	@JSONHint(ordinal=2) public Date when_answer;
	@JSONHint(ordinal=3) public Method method;

	public String getUuid(){return uuid;}
	public void setUuid(String s){this.uuid=s;}
	private String uuid;
	
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
	
	public void save(){
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		mapper.save(this);
	}


	public static List<String> loginUsers(){
		/*
		List<String> ls = new ArrayList<String>();
		ls.add("John");
		ls.add("Paul");
		ls.add("Richard");
		ls.add("George");
		return ls;
		*/
		DynamoDBScanExpression scan = new DynamoDBScanExpression();
		scan.addFilterCondition("user_name", new Condition()
				.withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withS("太田直宏")));
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		List<Command> cmds = mapper.scan(Command.class, scan);
		if(cmds==null){
			try {
				cmds.add(new Command());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		List<String> uids = new ArrayList<String>();
		for(Command c: cmds){
			String uid = c.getUid();
			if(!uids.contains(uid)){
				uids.add(uid);
			}
		}
		return uids;
	}
	
	public static List<Command> userActs(String uid){
		DynamoDBScanExpression scan = new DynamoDBScanExpression();
		scan.addFilterCondition("user_name", new Condition()
				.withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withS(uid)));
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		List<Command> ls = mapper.scan(Command.class, scan);
		if(ls==null){
			try {
				ls.add(new Command());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ls;
	}
	
}