package webapi.model;
import java.util.Map;

public class Method {
	public String name;
	public Map<String, String> params;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
