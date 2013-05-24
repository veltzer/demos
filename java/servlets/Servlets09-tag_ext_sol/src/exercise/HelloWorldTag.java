package exercise;



import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
/**
 *
 * @author  rank
 * @version 
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
