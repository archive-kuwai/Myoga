package data;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import net.arnx.jsonic.*;

public class JVMStore {
	protected static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm (ss.SSS)");
    public static Map map = new HashMap();
    protected static JSON json = new JSON();
    
	@JSONHint(ordinal=0) public String uuid;

    
    public JVMStore(){
    	json.setDateFormat("yyyy-MM-dd HH:mm (ss.SSS)");
		this.uuid = UUID.randomUUID().toString();
    }

    public void save(){
    	map.put(this.uuid, this);
    	Logger.getLogger("").info(json.format(this));
    }
    
}
