package couchbase_sample;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import com.couchbase.client.CouchbaseClient;
import net.spy.memcached.internal.GetFuture;
import net.spy.memcached.internal.OperationFuture;
public class Couchbase_Sample_Runner {
	
	//---------------------------------------------
	//http://www.couchbase.com/develop/java/current
	//---------------------------------------------
	
	public static void run(){
		int EXP_TIME = 10;
		String KEY = "spoon";
		String VALUE = "Hello World!";
	    Boolean do_delete = true;
	    
		List<URI> uris = new LinkedList<URI>();
		uris.add(URI.create("http://localhost:8091/pools"));
		CouchbaseClient client = null;
		try{
			client = new CouchbaseClient(uris, "default", "");
		}catch(Exception e){
			Logger.getLogger("").info("Error connecting to Couchbase: "+e.getMessage());
			System.exit(0);
		}
		
	    // Do a synchrononous get
	    Object getObject = client.get(KEY);
	    // Do an asynchronous set
	    OperationFuture<Boolean> setOp = client.set(KEY, EXP_TIME, VALUE);
	    // Do an asynchronous get
	    GetFuture getOp = client.asyncGet(KEY);
	    // Do an asynchronous delete
	    OperationFuture<Boolean> delOp = null;
	    if (do_delete) {
	      delOp = client.delete(KEY);
	    }
	    
	    // Shutdown the client
	    client.shutdown(3, TimeUnit.SECONDS);
	    
	    // Now we want to see what happened with our data
	    // Check to see if our set succeeded
	    try {
	      if (setOp.get().booleanValue()) {
	        System.out.println("Set Succeeded");
	      } else {
	        System.err.println("Set failed: "
	            + setOp.getStatus().getMessage());
	      }
	    } catch (Exception e) {
	      System.err.println("Exception while doing set: "
	          + e.getMessage());
	    }
	    // Print the value from synchronous get
	    if (getObject != null) {
	      System.out.println("Synchronous Get Suceeded: "
	          + (String) getObject);
	    } else {
	      System.err.println("Synchronous Get failed");
	    }
	    // Check to see if ayncGet succeeded
	    try {
	      if ((getObject = getOp.get()) != null) {
	        System.out.println("Asynchronous Get Succeeded: "
	            + getObject);
	      } else {
	        System.err.println("Asynchronous Get failed: "
	            + getOp.getStatus().getMessage());
	      }
	    } catch (Exception e) {
	      System.err.println("Exception while doing Aynchronous Get: "
	          + e.getMessage());
	    }
	    // Check to see if our delete succeeded
	    if (do_delete) {
	      try {
	        if (delOp.get().booleanValue()) {
	          System.out.println("Delete Succeeded");
	        } else {
	          System.err.println("Delete failed: " + 
	              delOp.getStatus().getMessage());
	        }
	      } catch (Exception e) {
	        System.err.println("Exception while doing delete: "
	            + e.getMessage());
	      }
	    }	    
	    
	    
	    
	    
	}
	
}
