package data;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import net.arnx.jsonic.*;

public class JVMStore {
	protected static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm (ss.SSS)");
    public static List list = new LinkedList();
    protected static JSON json = new JSON();
    
    public JVMStore(){
    	json.setDateFormat("yyyy-MM-dd HH:mm (ss.SSS)");
    }
    public void save(){
    	list.add(this);
    	Logger.getLogger("").info(json.format(this));
    }
    
}
