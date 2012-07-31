package d.u;
import java.util.Set;

public class Role {
	public final String name;
	public final Set<String> runnableMethods;
	
	public Role(String name, Set<String> runnableMethods) {
		super();
		this.name = name;
		this.runnableMethods = runnableMethods;
	}
}
