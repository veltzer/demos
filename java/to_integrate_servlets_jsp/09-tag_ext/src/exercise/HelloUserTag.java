package exercise;



import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
/**
 *
 * @author  rank
 * @version
 */
public class HelloUserTag extends TagSupport {
    
    /** Holds value of property username. */
    private String user;
    
    /** Holds value of property loops. */
    private int loops=1;
    
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
