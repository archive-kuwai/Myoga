package data;
import com.mongodb.*;
import java.util.*;
import net.arnx.jsonic.*;

public class Mongoo {

	/* ********************************************* */
	//static String mongoURI = "ec2-176-32-67-236.ap-northeast-1.compute.amazonaws.com";
	static String mongoURI = "localhost";
	/* ********************************************* */
	static String dbname = "test";
	static Mongo mo = null;
	static DB db = null;
	public String uniqueName;
	
	public Mongoo(String uniqueName){
		if(uniqueName == null){
			this.uniqueName = UUID.randomUUID().toString();
		}else{
			this.uniqueName = uniqueName;
		}

		if(mo==null){
			try{
				mo = new Mongo(mongoURI);
				db = mo.getDB(dbname);
				System.out.println("Class 'Mongoo' is ready, now. ################");
				Set<String> colls = db.getCollectionNames();
				for(String s: colls){
					System.out.println(s);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void save(){
		// Make DBObject
		String json = JSON.encode(this);
		DBObject obj = (DBObject)com.mongodb.util.JSON.parse(json);

		// Get collection
		String n = this.getClass().getName();
		DBCollection coll = db.getCollection(n);
		
		// 
		BasicDBObject queryObj = new BasicDBObject("uniqueName", this.uniqueName); 
		DBObject returnedObj = coll.findOne(queryObj);
		System.out.println(returnedObj);
		if(returnedObj==null){
			System.out.println("■■■■■■■Insert■");
			coll.insert(obj);
		}else{
			System.out.println("■■■■■■■Update■");
			DBCollection history = db.getCollection("history_" + n);
			history.insert(returnedObj);
			coll.update(queryObj, obj);
		}
	}
	
}
