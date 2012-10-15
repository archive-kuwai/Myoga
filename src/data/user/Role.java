package data.user;
import java.util.Set;

public class Role extends data.Mongoo{
	public /*final*/ String name;
	public final Set<String> runnableMethods;
	
	public Role(String name, Set<String> runnableMethods) {
		super(name);
		this.name = name;
		this.runnableMethods = runnableMethods;
	}
}
