package webapi.command;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.arnx.jsonic.JSON;
import com.mongodb.*;

public class Command extends data.Mongoo{
	
	public Command() {
		super(null);
	}
	public Who who;
	public Method method;
	public String ans;
	public Date req;
	public Date res; 

	public List userActs(String uid){
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
		return cmds;
	}
	
}