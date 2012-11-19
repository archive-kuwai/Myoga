package webapi.command;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import data.JVMStore;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONHint;

public class Command extends JVMStore{
	
	public Command() throws IOException {
	}
	@JSONHint(ordinal=0) public Who who;
	@JSONHint(ordinal=1) public Date srvIn;
	@JSONHint(ordinal=2) public Date srvOut;
	@JSONHint(ordinal=3) public Method method;

	
}