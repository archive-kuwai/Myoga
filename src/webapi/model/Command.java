package webapi.model;

public class Command {
	Who who;
	Method method;

	public Who getWho() {
		return who;
	}
	public void setWho(Who who) {
		this.who = who;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
}