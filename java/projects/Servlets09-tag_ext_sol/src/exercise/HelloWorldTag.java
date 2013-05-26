package exercise;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class HelloWorldTag extends TagSupport {

	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().println("Hello world from Tag!");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;

	}

}
