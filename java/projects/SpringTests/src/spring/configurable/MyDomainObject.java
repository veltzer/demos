package spring.configurable;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class MyDomainObject {
	private String msg;
	private int code;

	public void setMsg(String imsg) {
		msg = imsg;
	}

	public void setCode(int icode) {
		code = icode;
	}

	@Override
	public String toString() {
		return msg + " - " + code;
	}

}
