package exercise;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class HelloUserTag extends TagSupport {

	/** Holds value of property username. */
	private String user;

	/** Holds value of property loops. */
	private int loops = 1;

	/**
	 * Enter your code here
	 */

	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	public int getLoops() {
		return loops;
	}

	public void setLoops(int iloops) {
		loops = iloops;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String iuser) {
		user = iuser;
	}

	/**
	 * Enter your code here
	 */

}
