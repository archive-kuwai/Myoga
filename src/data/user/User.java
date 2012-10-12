package data.user;

public class User {
	private final String id;
	private final String key;
	private final String realname;
	private final Role role;
	private String ver;

	public User(String id, String key, String realname, Role role) {
		super();
		this.id = id;
		this.key = key;
		this.realname = realname;
		this.role = role;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public String getRealname() {
		return realname;
	}

	public Role getRole() {
		return role;
	}
}
