package webapi.command;
import java.io.IOException;
import java.util.*;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONHint;
import com.amazonaws.services.dynamodb.model.*;
import com.mongodb.*;

public class Command extends data.Dynamoo{
	
	public Command() throws IOException {
		super();
	}
	@JSONHint(ordinal=0) public Who who;
	@JSONHint(ordinal=1) public Date req;
	@JSONHint(ordinal=2) public Date res; 
	@JSONHint(ordinal=3) public Method method;
	@JSONHint(ordinal=4) public String ans;

	public void save(){
		Map<String,AttributeValue> item = new HashMap<String,AttributeValue>();
		JSON json = new JSON();
		json.setDateFormat("yyyy/MM/dd HH:mm(ss.sss)");
		item.put("123", new AttributeValue().withS(json.format(this)));
		String tableName ="cmd";
		//PutItemRequest req = new PutItemRequest(tableName, item);
		PutItemRequest req = new Pu
		
		client.putItem(req);
	}
	public List userActs(String uid){
		/*
		String n = this.getClass().getName();
		DBCollection coll = db.getCollection(n);
		BasicDBObject query = new BasicDBObject();
		query.put("who.uid", uid);
		DBCursor cur = coll.find(query);
		List<DBObject> ls = new ArrayList<DBObject>();
		JSON json = new JSON();
		json.setDateFormat("yyyy/MM/dd_HH:mm:ss.SSS");
		List cmds = new ArrayList<Command>();
		try{
			while(cur.hasNext()){
				DBObject o = cur.next();
				String s = com.mongodb.util.JSON.serialize(o);
				ls.add(o); // TODO we didnot use ls?
				Command cmd = json.parse(s, Command.class);
				cmd.uniqueName = null;
				cmd.who.key = null;
				cmds.add(cmd);
			}
		}finally{
			cur.close();
		}
		*/
		return null;
	}
	
}