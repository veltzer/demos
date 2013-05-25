package exercise;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Mark Veltzer <mark.veltzer@gmail.com>
 * @version
 */
@SuppressWarnings("serial")
public class HelloUserTag extends TagSupport {

	/** Holds value of property username. */
	protected String user;

	/** Holds value of property loops. */
	protected int loops = 1;

	/**
	 * Enter your code here
	 */

	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * Enter your code here
	 */

}
