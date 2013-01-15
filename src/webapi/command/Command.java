package webapi.command;
import java.io.IOException;
import java.util.*;
import data.JVMStore;
import net.arnx.jsonic.JSONHint;

public class Command extends JVMStore{
	
	public Command() throws IOException {
	}
	@JSONHint(ordinal=0) public Who who;
	@JSONHint(ordinal=1) public Date srvIn;
	@JSONHint(ordinal=2) public Date srvOut;
	@JSONHint(ordinal=3) public Method method;
}