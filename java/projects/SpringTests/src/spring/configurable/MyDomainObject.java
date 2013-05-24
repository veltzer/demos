package spring.configurable;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class MyDomainObject {
	private String msg;
	private int code;
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return msg + " - " + code;
	}
	
	
}
